package com.trabalho.controllers;

import com.mongodb.client.*;
import org.bson.Document;
import com.trabalho.utils.MenuFormatter;
import com.trabalho.models.*;

import java.util.LinkedList;

public class FilmeController {

    private static MongoDatabase database = Database.getDatabase();

    public static boolean inserirRegistro(Filme filme) {
        MongoCollection<Document> collection = database.getCollection("filme");

        int idFilme = getMaiorId();
        if (idFilme == -500) {
            idFilme = 0;
        } else if (idFilme == -999) {
            return false;
        }

        Document document = new Document("id_filme", idFilme + 1)
                .append("nome_filme", filme.getNomeFilme())
                .append("preco", filme.getPreco());

        try {
            collection.insertOne(document);
            return true;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    public static boolean excluirRegistro(int idFilme) {
        MongoCollection<Document> collection = database.getCollection("filme");

        if (existeRegistro(idFilme)) {
            try {
                collection.deleteOne(new Document("id_filme", idFilme));
                return true;
            } catch (Exception e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return false;
            }
        } else {
            MenuFormatter.msgTerminalERROR("Endereço não encontrado no Banco de Dados.");
            return false;
        }
    }

    public static boolean excluirTodosRegistros() {
        MongoCollection<Document> collection = database.getCollection("filme");

        try {
            collection.deleteMany(new Document());
            return true;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    public static boolean atualizarRegistro(int idFilme, String nomeFilme, double preco) {
        MongoCollection<Document> collection = database.getCollection("filme");

        if (existeRegistro(idFilme)) {
            try {
                Document query = new Document("id_filme", idFilme);
                Document update = new Document("$set", new Document("nome_filme", nomeFilme)
                        .append("preco", preco));
                collection.updateOne(query, update);
                return true;
            } catch (Exception e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return false;
            }
        } else {
            MenuFormatter.msgTerminalERROR("Endereço não encontrado no Banco de Dados.");
            return false;
        }
    }

    public static boolean atualizarRegistro(Filme filme) {
        MongoCollection<Document> collection = database.getCollection("filme");

        if (existeRegistro(filme.getIdFilme())) {
            try {
                Document query = new Document("id_filme", filme.getIdFilme());
                Document update = new Document("$set", new Document("nome_filme", filme.getNomeFilme())
                        .append("preco", filme.getPreco()));
                collection.updateOne(query, update);
                return true;
            } catch (Exception e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return false;
            }
        } else {
            MenuFormatter.msgTerminalERROR("Endereço não encontrado no Banco de Dados.");
            return false;
        }
    }

    public static Filme buscarRegistroPorId(int idFilmePesquisa) {
        MongoCollection<Document> collection = database.getCollection("filme");

        if (existeRegistro(idFilmePesquisa)) {
            try {
                Document document = collection.find(new Document("id_filme", idFilmePesquisa)).first();
                if (document != null) {
                    return new Filme(document.getInteger("id_filme"), document.getString("nome_filme"), document.getDouble("preco"));
                } else {
                    return null;
                }
            } catch (Exception e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return null;
            }
        } else {
            MenuFormatter.msgTerminalERROR("Não encontrado nenhum registro com o ID informado.");
            return null;
        }
    }

    public static LinkedList<Filme> listarTodosRegistros() {
        MongoCollection<Document> collection = database.getCollection("filme");
        LinkedList<Filme> listaResgistros = new LinkedList<>();

        try {
            FindIterable<Document> documents = collection.find().sort(new Document("id_filme", 1));
            for (Document document : documents) {
                listaResgistros.add(new Filme(document.getInteger("id_filme"), document.getString("nome_filme"), document.getDouble("preco")));
            }
            return listaResgistros;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    public static int contarRegistros() {
        MongoCollection<Document> collection = database.getCollection("filme");

        try {
            long count = collection.countDocuments();
            return (int) count;
        } catch (Exception e) {
            return -999;
        }
    }

    public static boolean existeRegistro(int idFilme) {
        MongoCollection<Document> collection = database.getCollection("filme");

        try {
            long count = collection.countDocuments(new Document("id_filme", idFilme));
            return count > 0;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    private static int getMaiorId() {
        MongoCollection<Document> collection = database.getCollection("filme");

        try {
            Document document = collection.find().sort(new Document("id_filme", -1)).first();
            if (document != null) {
                return document.getInteger("id_filme");
            } else {
                return -500;
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return -999;
        }
    }
}
