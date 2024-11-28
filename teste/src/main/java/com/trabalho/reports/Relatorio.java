package com.trabalho.reports;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Date;

import org.bson.conversions.Bson;
import org.bson.Document;

import com.trabalho.reports.dados.*;
import com.trabalho.controllers.*;
import com.trabalho.connection.*;
import com.trabalho.models.*;
import com.trabalho.utils.*;

public class Relatorio {
    private static LinkedList<DadosCinemaEndereco> listaCinemaEndereco = null;
    private static LinkedList<DadosInformacaoSessoes> listaInfoSessoes = null;
    private static LinkedList<DadosSomaIngressos> listaSomaIngresso = null;

    // Relatório CinemaEndereco
    private static void atualizaListaCinemaEndereco() {
        LinkedList<DadosCinemaEndereco> relatCinemaEndereco = new LinkedList<DadosCinemaEndereco>();
        EnderecoController enderecoController = new EnderecoController();

        try {
            MongoCollection<Document> cinemas = DatabaseMongoDb.conectar().getCollection("cinema");

            // Projeção para nome do cinema e rua do endereço
            FindIterable<Document> resultados = cinemas.find()
                .projection(new Document("id_cinema", 1).append("nome_cinema", 1).append("id_endereco", 1));

            for (Document doc : resultados) {
                int idCinema = doc.getInteger("id_cinema");
                String nomeCinema = doc.getString("nome_cinema");
                Endereco endereco = enderecoController.buscarRegistroPorId(doc.getInteger("id_endereco"));
                
                String enderecoRua = endereco.getRua();
                String enderecoCidade = endereco.getCidade();

                relatCinemaEndereco.add(new DadosCinemaEndereco(idCinema, nomeCinema, enderecoRua, enderecoCidade));
            }

            Relatorio.listaCinemaEndereco = relatCinemaEndereco;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            MenuFormatter.delay(1);
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
            MenuFormatter.delay(2);
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
        FilmeController filmeController = new FilmeController();

        try {
            MongoCollection<Document> sessoes = DatabaseMongoDb.conectar().getCollection("sessao");

            // Projeção para dados necessários
            FindIterable<Document> resultados = sessoes.find()
                .projection(new Document("id_sessao", 1).append("horario", 1).append("id_filme", 1).append("qtd_assentos", 1));

            for (Document doc : resultados) {
                int idSessao = doc.getInteger("id_sessao");
                Date horario = doc.getDate("horario");
                LocalDateTime horarioFormatado = horario.toInstant()
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDateTime();


                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                String horarioExibicao = horarioFormatado.format(formatter);

                int qtdAssentos = doc.getInteger("qtd_assentos");

                Filme filme = filmeController.buscarRegistroPorId(doc.getInteger("id_filme"));

                String nomeFilme = filme.getNomeFilme();
                double preco = filme.getPreco();

                relatInfoSessoes.add(new DadosInformacaoSessoes (idSessao, horarioExibicao, nomeFilme, qtdAssentos, preco));
            }

            Relatorio.listaInfoSessoes = relatInfoSessoes;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            MenuFormatter.delay(1);
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
            MenuFormatter.delay(1);
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
        LinkedList<DadosSomaIngressos> relatSomaIngresso = new LinkedList<>();
        try {
            MongoCollection<Document> vendaCollection = DatabaseMongoDb.conectar().getCollection("venda");

            List<Bson> pipeline = List.of(
                Aggregates.lookup("sessao", "id_sessao", "id_sessao", "sessao_detalhes"),
                
                Aggregates.unwind("$sessao_detalhes"),
                
                Aggregates.lookup("filme", "sessao_detalhes.id_filme", "id_filme", "filme_detalhes"),
                
                Aggregates.unwind("$filme_detalhes"),
                
                Aggregates.group(
                    new Document("id_sessao", "$id_sessao").append("nome_filme", "$filme_detalhes.nome_filme"),
                    Accumulators.sum("valor_final", "$filme_detalhes.preco")
                ),
                
                Aggregates.sort(Sorts.descending("valor_final"))
            );

            AggregateIterable<Document> resultados = vendaCollection.aggregate(pipeline);

            for (Document doc : resultados) {
                Document idDoc = doc.get("_id", Document.class);
                int sessaoID = idDoc.getInteger("id_sessao");
                String nomeFilme = idDoc.getString("nome_filme");
                double valorTotal = doc.getDouble("valor_final");
                String valorFormatado = String.format("%.2f", valorTotal);

                relatSomaIngresso.add(new DadosSomaIngressos(sessaoID, nomeFilme, valorFormatado));
            }

            Relatorio.listaSomaIngresso = relatSomaIngresso;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            MenuFormatter.delay(1);
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
            MenuFormatter.delay(1);
            return "Sem dados";
        }
        
        int tamanho = MenuFormatter.getNumEspacamentoUni()+2;
        String[] cabecalho = {"Sessão ID", "Filme", "Valor Total (R$)"};
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
