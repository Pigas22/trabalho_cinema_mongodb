package com.trabalho.reports;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;

import java.util.LinkedList;
import java.util.List;

import org.bson.conversions.Bson;
import org.bson.Document;

import com.trabalho.reports.dados.*;
import com.trabalho.connection.*;
import com.trabalho.utils.*;

public class Relatorio {
    private static LinkedList<DadosCinemaEndereco> listaCinemaEndereco = null;
    private static LinkedList<DadosInformacaoSessoes> listaInfoSessoes = null;
    private static LinkedList<DadosSomaIngressos> listaSomaIngresso = null;

    // Relatório CinemaEndereco
    private static void atualizaListaCinemaEndereco() {
        LinkedList<DadosCinemaEndereco> relatCinemaEndereco = new LinkedList<DadosCinemaEndereco>();

        try {
            MongoCollection<Document> cinemas = DatabaseMongoDb.conectar().getCollection("cinema");

            // Projeção para nome do cinema e rua do endereço
            FindIterable<Document> resultados = cinemas.find()
                .projection(new Document("id_cinema", 1).append("nome_cinema", 1).append("endereco.rua", 1).append("endereco.cidade", 1));

            for (Document doc : resultados) {
                int idCinema = doc.getInteger("id_cinema");
                String nomeCinema = doc.getString("nome_cinema");
                String enderecoRua = doc.getEmbedded(List.of("endereco", "rua"), String.class);
                String enderecoCidade = doc.getEmbedded(List.of("endereco", "cidade"), String.class);

                
                relatCinemaEndereco.add(new DadosCinemaEndereco(idCinema, nomeCinema, enderecoRua, enderecoCidade));
            }

            Relatorio.listaCinemaEndereco = relatCinemaEndereco;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            Relatorio.listaCinemaEndereco = null;
        }
    }

    public static LinkedList<DadosCinemaEndereco> getListaCinemaEndereco() {
        Relatorio.atualizaListaCinemaEndereco();
        return Relatorio.listaCinemaEndereco;
    }

    public static String tabelaDadosCinemaEndereco() {
        String msg = "";
        Relatorio.atualizaListaCinemaEndereco();

        if (Relatorio.listaCinemaEndereco.isEmpty()) {
            MenuFormatter.msgTerminalERROR("Nenhum dado disponível para o Relatório.");
            return "Sem dados";
        }
        
        // idCinema, nomeCinema, enderecoRua, enderecoCidade
        int tamanho = MenuFormatter.getNumEspacamentoUni()+2;
        String[] cabecalho = {"ID Cinema", "Cinema", "Rua", "Cidade"};
        String[] linhas = new String[Relatorio.listaCinemaEndereco.size()];
        int cont = 0;
        
        for (DadosCinemaEndereco dado : Relatorio.listaCinemaEndereco) {
            String[] linha = {dado.getIdCinema()+"", dado.getNomeCinema(), 
                        dado.getRuaEndereco()+"", dado.getCidadeEndereco()+""};
            linhas[cont] = MenuFormatter.criarLinhaTabela(linha, tamanho);
            cont++;
        }
        
        msg = MenuFormatter.criaTabelaCompleta(cabecalho, linhas, tamanho)
            + "\n" + MenuFormatter.getLinha("-=");

        return msg;
    }

    // Relatório InfoSessoes
    private static void atualizaListaInfoSessoes() {
        LinkedList<DadosInformacaoSessoes> relatInfoSessoes = new LinkedList<DadosInformacaoSessoes>();
        try {
            MongoCollection<Document> sessoes = DatabaseMongoDb.conectar().getCollection("sessao");

            // Projeção para dados necessários
            FindIterable<Document> resultados = sessoes.find()
                .projection(new Document("id_sesssao", 1).append("horario", 1).append("filme", 1).append("qtd_assentos", 1).append("preco", 1));

            for (Document doc : resultados) {
                int idSessao = doc.getInteger("id_sessao");
                String horario = doc.getString("horario");
                String nomeFilme = doc.getString("filme");
                int qtdAssentos = doc.getInteger("qtd_assentos");
                double preco = doc.getDouble("preco");

                relatInfoSessoes.add(new DadosInformacaoSessoes (idSessao, horario, nomeFilme, qtdAssentos, preco));
            }

            Relatorio.listaInfoSessoes = relatInfoSessoes;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            Relatorio.listaInfoSessoes =  null;
        }
    }

    public static LinkedList<DadosInformacaoSessoes> getListaInfoSessoes() {
        Relatorio.atualizaListaInfoSessoes();
        return Relatorio.listaInfoSessoes;
    }

    public static String tabelaDadosInfoSessoes() {
        String msg = "";
        Relatorio.atualizaListaInfoSessoes();

        if (Relatorio.listaInfoSessoes.isEmpty()) {
            MenuFormatter.msgTerminalERROR("Nenhum dado disponível para o Relatório.");
            return "Sem dados";
        }
        
        // idSessao, horario, nomeFilme, qtdAssentos, preco
        int tamanho = MenuFormatter.getNumEspacamentoUni()+2;
        String[] cabecalho = {"ID Sessão", "Horário", "Filme", "Total Assentos", "Valor Ingresso (R$)"};
        String[] linhas = new String[Relatorio.listaInfoSessoes.size()];
        int cont = 0;
        
        for (DadosInformacaoSessoes dado : Relatorio.listaInfoSessoes) {
            String[] linha = {dado.getIdSessao()+"", dado.getHorario(), dado.getNomeFilme()+"",
                        dado.getQtdAssentos()+"", dado.getPreco()+""};
            linhas[cont] = MenuFormatter.criarLinhaTabela(linha, tamanho);
            cont++;
        }
        
        msg = MenuFormatter.criaTabelaCompleta(cabecalho, linhas, tamanho)
            + "\n" + MenuFormatter.getLinha("-=");

        return msg;
    }

    // Relatório SomaIngresso
    private static void atualizaListaSomaIngresso() {
        LinkedList<DadosSomaIngressos> relatSomaIngresso = new LinkedList<DadosSomaIngressos>();
        try {
            MongoCollection<Document> sessoes = DatabaseMongoDb.conectar().getCollection("sessao");

            // Agregação para calcular valor total por seção
            List<Bson> pipeline = List.of(
                Aggregates.group(
                    "$id_sessao",
                    Accumulators.sum("valor_total", "$preco"),
                    Accumulators.first("filme", "$filme")
                )
            );

            AggregateIterable<Document> resultados = sessoes.aggregate(pipeline);

            for (Document doc : resultados) {
                int sessaoID = doc.getInteger("_id");
                String nomeFilme = doc.getString("filme");
                double valorTotal = doc.getDouble("valor_total");

                relatSomaIngresso.add(new DadosSomaIngressos(sessaoID, nomeFilme, valorTotal));   
            }

            Relatorio.listaSomaIngresso = relatSomaIngresso;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            Relatorio.listaSomaIngresso = null;
        }
    }

    public static LinkedList<DadosSomaIngressos> getListaDadosSomaIngressos() {
        Relatorio.atualizaListaSomaIngresso();
        return Relatorio.listaSomaIngresso;
    }

    public static String tabelaDadosSomaIngressos() {
        String msg = "";
        Relatorio.atualizaListaSomaIngresso();

        if (Relatorio.listaSomaIngresso.isEmpty()) {
            MenuFormatter.msgTerminalERROR("Nenhum dado disponível para o Relatório.");
            return "Sem dados";
        }
        
        int tamanho = MenuFormatter.getNumEspacamentoUni()+2;
        String[] cabecalho = {"Sessão ID", "Filme", "Valor Total"};
        String[] linhas = new String[Relatorio.listaSomaIngresso.size()];
        int cont = 0;
        
        for (DadosSomaIngressos dado : Relatorio.listaSomaIngresso) {
            String[] linha = {dado.getSessaoID()+"", dado.getNomeFilme(), dado.getValorTotal()+""};
            linhas[cont] = MenuFormatter.criarLinhaTabela(linha, tamanho);
            cont++;
        }
        
        msg = MenuFormatter.criaTabelaCompleta(cabecalho, linhas, tamanho)
            + "\n" + MenuFormatter.getLinha("-=");

        return msg;
    }
}
