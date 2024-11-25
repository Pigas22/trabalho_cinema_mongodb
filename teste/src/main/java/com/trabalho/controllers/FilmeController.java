package com.trabalho.controllers;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import com.trabalho.utils.MenuFormatter;
import com.trabalho.conexion.DatabaseMongoDb;
import com.trabalho.models.*;

import java.util.LinkedList;

public class FilmeController {

    private static final MongoCollection<Document> filmesCollection = DatabaseMongoDb.conectar().getCollection("filme");

    public static boolean inserirRegistro(Filme filme) {
        try {
            Document document = new Document("nome_filme", filme.getNomeFilme())
                    .append("preco", filme.getPreco());
            filmesCollection.insertOne(document);
            return true;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    public static boolean excluirRegistro(int idFilme) {
        try {
            Document result = filmesCollection.findOneAndDelete(Filters.eq("id_filme", idFilme));
            if (result != null) {
                return true;
            } else {
                MenuFormatter.msgTerminalERROR("Endereço não encontrado no Banco de Dados.");
                return false;
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    public static boolean excluirTodosRegistros() {
        try {
            filmesCollection.deleteMany(new Document());
            return true;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    public static boolean atualizarRegistro(int idFilme, String nomeFilme, double preco) {
        try {
            Document updateDoc = new Document("nome_filme", nomeFilme)
                    .append("preco", preco);
            Document result = filmesCollection.findOneAndUpdate(
                    Filters.eq("id_filme", idFilme),
                    new Document("$set", updateDoc));
            if (result != null) {
                return true;
            } else {
                MenuFormatter.msgTerminalERROR("Endereço não encontrado no Banco de Dados.");
                return false;
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    public static boolean atualizarRegistro(Filme filme) {
        try {
            Document updateDoc = new Document("nome_filme", filme.getNomeFilme())
                    .append("preco", filme.getPreco());
            Document result = filmesCollection.findOneAndUpdate(
                    Filters.eq("id_filme", filme.getIdFilme()),
                    new Document("$set", updateDoc));
            if (result != null) {
                return true;
            } else {
                MenuFormatter.msgTerminalERROR("Endereço não encontrado no Banco de Dados.");
                return false;
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    public static Filme buscarRegistroPorId(int idFilmePesquisa) {
        try {
            Document doc = filmesCollection.find(Filters.eq("id_filme", idFilmePesquisa)).first();
            if (doc != null) {
                return new Filme(doc.getInteger("id_filme"), doc.getString("nome_filme"), doc.getDouble("preco"));
            } else {
                MenuFormatter.msgTerminalERROR("Não encontrado nenhum registro com o ID informado.");
                return null;
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    public static LinkedList<Filme> listarTodosRegistros() {
        LinkedList<Filme> listaResgistros = new LinkedList<>();
        try {
            MongoCursor<Document> cursor = filmesCollection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                listaResgistros.add(new Filme(doc.getInteger("id_filme"), doc.getString("nome_filme"), doc.getDouble("preco")));
            }
            return listaResgistros;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    public static int contarRegistros() {
        try {
            return (int) filmesCollection.countDocuments();
        } catch (Exception e) {
            return -999;
        }
    }

    public static boolean existeRegistro(int idFilme) {
        try {
            Document doc = filmesCollection.find(Filters.eq("id_filme", idFilme)).first();
            return doc != null;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    private static int getMaiorId() {
        try {
            Document doc = filmesCollection.find().sort(new Document("id_filme", -1)).first();
            if (doc != null) {
                return doc.getInteger("id_filme");
            } else {
                return -500;
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return -999;
        }
    }
}
