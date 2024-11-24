package com.trabalho.reports;

import com.mongodb.client.*;
import org.bson.Document;
import com.trabalho.utils.*;

public class Relatorio {
    private static final MongoCollection<Document> cinemaCollection = Database.getMongoDatabase().getCollection("cinema");
    private static final MongoCollection<Document> informacaoCollection = Database.getMongoDatabase().getCollection("informacao");
    private static final MongoCollection<Document> ingressosCollection = Database.getMongoDatabase().getCollection("ingressos");

    public static String listarCinemaEndereco() {
        String[] cabecalho = {"Cinema", "Endereço (Rua)"};

        int qtdResultado = contarRegistros(cinemaCollection);

        String[] linhas = new String[qtdResultado];
        int tamanho = MenuFormatter.getNumEspacamentoUni() + 2;

        try {
            FindIterable<Document> docs = cinemaCollection.find();
            int cont = 0;
            for (Document doc : docs) {
                String nomeCinema = doc.getString("nome_cinema");
                String enderecoRua = doc.getString("rua");

                String[] linha = {nomeCinema, enderecoRua};
                linhas[cont] = MenuFormatter.criarLinhaTabela(linha, tamanho);
                cont++;
            }

            return MenuFormatter.criaTabelaCompleta(cabecalho, linhas, tamanho);

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    public static String listarInformacoes() {
        String[] cabecalho = {"Horário", "Filme", "Qtd Assentos", "Preço (R$)"};

        int qtdResultado = contarRegistros(informacaoCollection);

        String[] linhas = new String[qtdResultado];
        int tamanho = MenuFormatter.getNumEspacamentoUni() + 2;

        try {
            FindIterable<Document> docs = informacaoCollection.find();
            int cont = 0;
            for (Document doc : docs) {
                String horario = doc.getString("horario");
                int qtdAssentos = doc.getInteger("qtd_assentos");
                String nomeFilme = doc.getString("nome_filme");
                double preco = doc.getDouble("preco");

                String[] linha = {horario, nomeFilme, String.valueOf(qtdAssentos), String.valueOf(preco)};
                linhas[cont] = MenuFormatter.criarLinhaTabela(linha, tamanho);
                cont++;
            }

            return MenuFormatter.criaTabelaCompleta(cabecalho, linhas, tamanho);

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    public static String listarSomaIngressos() {
        String[] cabecalho = {"ID Seção", "Nome Filme", "Valor Total"};

        int qtdResultado = contarRegistros(ingressosCollection);

        String[] linhas = new String[qtdResultado];
        int tamanho = MenuFormatter.getNumEspacamentoUni() + 2;

        try {
            FindIterable<Document> docs = ingressosCollection.find();
            int cont = 0;
            for (Document doc : docs) {
                int secaoID = doc.getInteger("id_secao");
                String nomeFilme = doc.getString("nome_filme");
                String valorFinal = doc.getString("valor_final");

                String[] linha = {String.valueOf(secaoID), nomeFilme, valorFinal};
                linhas[cont] = MenuFormatter.criarLinhaTabela(linha, tamanho);
                cont++;
            }

            return MenuFormatter.criaTabelaCompleta(cabecalho, linhas, tamanho);

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    public static int contarRegistros(MongoCollection<Document> collection) {
        try {
            return (int) collection.countDocuments();
        } catch (Exception e) {
            return -999;
        }
    }
}
