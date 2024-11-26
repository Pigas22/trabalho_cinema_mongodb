package com.trabalho.connection;

import com.mongodb.client.*;
import org.bson.Document;

import com.trabalho.models.*;
import com.trabalho.utils.*;

public class DatabaseMongoDb {
    private static final String NOME_DATABASE = "cinema_mongo_db";
    private static final String URL_MONGODB = "mongodb://localhost:27017/";
    
    private static final String[] COLLETIONS_NAMES = { "enderecos", "cinemas", "filmes", "sessoes", "vendas" }; 
    private static final String ARQ_INSERT = "insert_documents.json";
    private static final String NOME_PASTA_JSON = "json";
    private static final String CAMINHO_PASTA_JSON = Arquivo.procuraPasta(NOME_PASTA_JSON);

    public static MongoDatabase conectar() {
        try {
            MongoClient mongoClient = MongoClients.create(URL_MONGODB);
            return mongoClient.getDatabase(NOME_DATABASE);
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR("Erro ao conectar ao MongoDB: " + e.getMessage());
            return null;
        }
    }

    public static boolean criarDatabase() {
        try {
            MongoDatabase database = conectar();

            for (String nomeColecao : COLLETIONS_NAMES) {
                database.createCollection(nomeColecao);
            }

            MenuFormatter.msgTerminalINFO("Banco de dados '" + NOME_DATABASE + "' e suas coleções criados com sucesso.");
            return true;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR("Erro ao criar o banco de dados: " + e.getMessage());
            return false;
        }
    }

    public static void droparDatabase() {
        try {
            MongoDatabase database = conectar();
            if (database != null) {
                database.drop();
                MenuFormatter.msgTerminalINFO("Banco de dados '" + NOME_DATABASE + "' dropado com sucesso.");

            } else {
                MenuFormatter.msgTerminalINFO("Banco de dados não encontrado.");

            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR("Erro ao dropar o banco de dados: " + e.getMessage());
        }
    }

    public static void inicializarDatabase() {
        try {
            MongoDatabase database = conectar();
            if (database == null) {
                return;
            }

            Dados dados = Arquivo.lerJSON(CAMINHO_PASTA_JSON + "/" + ARQ_INSERT);
            if (dados == null) {
                MenuFormatter.msgTerminalERROR("Não foi possível carregar os dados do JSON.");
                return;
            }

            // Insere enderecos
            MongoCollection<Document> enderecos = database.getCollection("enderecos");
            if (((int) enderecos.countDocuments()) <= 0) {
                for (Endereco endereco : dados.getEnderecos()) {
                    Document doc = new Document("id_endereco", endereco.getIdEndereco())
                            .append("numero", endereco.getNumero())
                            .append("rua", endereco.getRua())
                            .append("bairro", endereco.getBairro())
                            .append("cidade", endereco.getCidade())
                            .append("uf", endereco.getUf());
                    enderecos.insertOne(doc);
                }
            }

            // Insere cinemas
            MongoCollection<Document> cinemas = database.getCollection("cinemas");
            if (((int) cinemas.countDocuments()) <= 0) {
                for (Cinema cinema : dados.getCinemas()) {
                    Document doc = new Document("id_cinema", cinema.getIdCinema())
                            .append("nome_cinema", cinema.getNomeCinema())
                            .append("id_endereco", cinema.getEndereco().getIdEndereco()); 
                    cinemas.insertOne(doc);
                }
            }

            // Insere filmes
            MongoCollection<Document> filmes = database.getCollection("filmes");
            if (((int) filmes.countDocuments()) <= 0) {
                for (Filme filme : dados.getFilmes()) {
                    Document doc = new Document("id_filme", filme.getIdFilme())
                            .append("nome_filme", filme.getNomeFilme())
                            .append("preco", filme.getPreco());
                    filmes.insertOne(doc);
                }
            }

            // Insere sessões
            MongoCollection<Document> sessoes = database.getCollection("sessoes");
            if (((int) sessoes.countDocuments()) <= 0) {
                for (Sessao sessao : dados.getSessoes()) {
                    Document doc = new Document("id_sessao", sessao.getIdSessao()) 
                            .append("horario", sessao.getHorario())               
                            .append("id_cinema", sessao.getCinema().getIdCinema())               
                            .append("id_filme", sessao.getFilme().getIdFilme())               
                            .append("qtd_assentos", sessao.getQtdAssentos());
                    sessoes.insertOne(doc);
                }
            }

            // Insere vendas
            MongoCollection<Document> vendas = database.getCollection("vendas");
            if (((int) cinemas.countDocuments()) <= 0) {
                for (Venda venda : dados.getVendas()) {
                    Document doc = new Document("id_venda", venda.getIdVenda())
                            .append("nome_cliente", venda.getNomeCliente())
                            .append("assento", venda.getAssento())
                            .append("forma_pagamento", venda.getFormaPagamento())
                            .append("id_sessao", venda.getSessao().getIdSessao());
                    vendas.insertOne(doc);
                }
            }

            MenuFormatter.msgTerminalINFO("Dados inseridos no banco de dados '" + NOME_DATABASE + "'.");

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR("Erro ao inicializar o banco de dados: " + e.getMessage());
        }
    }

    public static void excluirColecoes() {
        try {
            MongoDatabase database = conectar();
            if (database == null) {
                return;
            }

            for (String nomeColecao : COLLETIONS_NAMES) {
                database.getCollection(nomeColecao).drop();
            }

            MenuFormatter.msgTerminalINFO("Coleções excluídas do banco de dados '" + NOME_DATABASE + "'.");

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR("Erro ao excluir coleções: " + e.getMessage());
        }
    }

    public static int contarColecoes() {
        try {
            MongoDatabase database = conectar();
            if (database == null) {
                return -999;
            }
            return database.listCollections().into(new java.util.ArrayList<>()).size();

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR("Erro ao contar as coleções: " + e.getMessage());
            return -999;
        }
    }

    public static void main(String[] args) {
        droparDatabase();
        criarDatabase();
        inicializarDatabase();
    }
}

