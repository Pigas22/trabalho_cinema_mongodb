package com.trabalho.connection;

import com.mongodb.client.*;
import org.bson.Document;

import com.trabalho.models.*;
import com.trabalho.utils.*;

public class DatabaseMongoDb {
    private static final String NOME_DATABASE = "cinema_mongo_db";
    private static final String URL_MONGODB = "mongodb://localhost:27017/";
    
    private static final String[] COLLETIONS_NAMES = { 
        "endereco", "cinema", 
        "filme", "sessao", 
        "venda", "cliente", 
        "cliente_vip" 
    }; 
    
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

    public static boolean criarDatabaseCollections() {
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
            MongoCollection<Document> enderecos = database.getCollection(COLLETIONS_NAMES[0]);
            if (((int) enderecos.countDocuments()) <= 0) {
                for (Endereco endereco : dados.getEndereco()) {
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
            MongoCollection<Document> cinemas = database.getCollection(COLLETIONS_NAMES[1]);
            if (((int) cinemas.countDocuments()) <= 0) {
                for (Cinema cinema : dados.getCinema()) {
                    Document doc = new Document("id_cinema", cinema.getIdCinema())
                            .append("nome_cinema", cinema.getNomeCinema())
                            .append("id_endereco", cinema.getEndereco().getIdEndereco()); 
                    cinemas.insertOne(doc);
                }
            }

            // Insere filmes
            MongoCollection<Document> filmes = database.getCollection(COLLETIONS_NAMES[2]);
            if (((int) filmes.countDocuments()) <= 0) {
                for (Filme filme : dados.getFilme()) {
                    Document doc = new Document("id_filme", filme.getIdFilme())
                            .append("nome_filme", filme.getNomeFilme())
                            .append("preco", filme.getPreco());
                    filmes.insertOne(doc);
                }
            }

            // Insere sessões
            MongoCollection<Document> sessoes = database.getCollection(COLLETIONS_NAMES[3]);
            if (((int) sessoes.countDocuments()) <= 0) {
                for (Sessao sessao : dados.getSessao()) {
                    Document doc = new Document("id_sessao", sessao.getIdSessao()) 
                            .append("horario", sessao.getHorario())               
                            .append("id_cinema", sessao.getCinema().getIdCinema())               
                            .append("id_filme", sessao.getFilme().getIdFilme())               
                            .append("qtd_assentos", sessao.getQtdAssentos());
                    sessoes.insertOne(doc);
                }
            }

            // Insere vendas
            MongoCollection<Document> vendas = database.getCollection(COLLETIONS_NAMES[4]);
            if (((int) vendas.countDocuments()) <= 0) {
                for (Venda venda : dados.getVenda()) {
                    Document doc = new Document("id_venda", venda.getIdVenda())
                            .append("id_cliente", venda.getCliente().getIdCliente())
                            .append("assento", venda.getAssento())
                            .append("forma_pagamento", venda.getFormaPagamento())
                            .append("id_sessao", venda.getSessao().getIdSessao());
                    vendas.insertOne(doc);
                }
            }

            // Insere cliente
            MongoCollection<Document> clientes = database.getCollection(COLLETIONS_NAMES[5]);
            if (((int) clientes.countDocuments()) <= 0) {
                for (Cliente cliente : dados.getCliente()) {
                    Document doc = new Document("id_cliente", cliente.getIdCliente())
                            .append("nome_cliente", cliente.getNomeCliente())
                            .append("cpf", cliente.getCpf())
                            .append("email", cliente.getEmail());
                    clientes.insertOne(doc);
                }
            }

            // Insere ClientesVip
            MongoCollection<Document> clientesVip = database.getCollection(COLLETIONS_NAMES[6]);
            if (((int) clientesVip.countDocuments()) <= 0) {
                for (ClienteVip clienteVip : dados.getClienteVip()) {
                    Document doc = new Document("id_cliente", clienteVip.getIdCliente())
                            .append("desconto", clienteVip.getDesconto())
                            .append("acesso_prioritario", clienteVip.isAcessoPrioritario());
                    clientesVip.insertOne(doc);
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
        criarDatabaseCollections();
        inicializarDatabase();
    }
}

