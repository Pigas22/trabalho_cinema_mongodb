package com.trabalho.controllers;

import com.mongodb.client.*;
import org.bson.Document;
import com.trabalho.utils.MenuFormatter;
import com.trabalho.models.*;

import java.sql.Timestamp;
import java.util.LinkedList;

public class SecaoController {

    private static MongoDatabase database = Database.getDatabase();

    public static boolean inserirRegistro(Secao secao) {
        MongoCollection<Document> collection = database.getCollection("secao");

        int idSecao = getMaiorId();
        if (idSecao == -500) {
            idSecao = 0;
        } else if (idSecao == -999) {
            return false;
        }

        Document document = new Document("id_secao", idSecao + 1)
                .append("horario", secao.getHorario())
                .append("id_cinema", secao.getCinema().getIdCinema())
                .append("id_filme", secao.getFilme().getIdFilme())
                .append("qtd_assentos", secao.getQtdAssentos());

        try {
            collection.insertOne(document);
            return true;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    public static boolean excluirRegistro(int idSecao) {
        MongoCollection<Document> collection = database.getCollection("secao");

        if (existeRegistro(idSecao)) {
            try {
                collection.deleteOne(new Document("id_secao", idSecao));
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
        MongoCollection<Document> collection = database.getCollection("secao");

        try {
            collection.deleteMany(new Document());
            return true;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    public static boolean atualizarRegistro(int idSecao, Timestamp horario, int idCinema, int idFilme, int qtdAssentos) {
        MongoCollection<Document> collection = database.getCollection("secao");

        if (FilmeController.existeRegistro(idFilme)) {
            try {
                Document query = new Document("id_secao", idSecao);
                Document update = new Document("$set", new Document("horario", horario)
                        .append("id_cinema", idCinema)
                        .append("id_filme", idFilme)
                        .append("qtd_assentos", qtdAssentos));
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

    public static boolean atualizarRegistro(Secao secao) {
        MongoCollection<Document> collection = database.getCollection("secao");

        if (FilmeController.existeRegistro(secao.getIdSecao())) {
            try {
                Document query = new Document("id_secao", secao.getIdSecao());
                Document update = new Document("$set", new Document("horario", secao.getHorario())
                        .append("id_cinema", secao.getCinema().getIdCinema())
                        .append("id_filme", secao.getFilme().getIdFilme())
                        .append("qtd_assentos", secao.getQtdAssentos()));
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

    public static Secao buscarRegistroPorId(int idSecaoPesquisa) {
        MongoCollection<Document> collection = database.getCollection("secao");

        if (existeRegistro(idSecaoPesquisa)) {
            try {
                Document document = collection.find(new Document("id_secao", idSecaoPesquisa)).first();
                if (document != null) {
                    Cinema cinema = CinemaController.buscarRegistroPorId(document.getInteger("id_cinema"));
                    Filme filme = FilmeController.buscarRegistroPorId(document.getInteger("id_filme"));
                    return new Secao(document.getInteger("id_secao"),
                            document.getTimestamp("horario"),
                            cinema,
                            filme,
                            document.getInteger("qtd_assentos"));
                }
                return null;
            } catch (Exception e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return null;
            }
        } else {
            MenuFormatter.msgTerminalERROR("Não encontrado nenhum registro com o ID informado.");
            return null;
        }
    }

    public static LinkedList<Secao> listarTodosRegistros() {
        MongoCollection<Document> collection = database.getCollection("secao");
        LinkedList<Secao> listaResgistros = new LinkedList<>();

        try {
            FindIterable<Document> documents = collection.find().sort(new Document("id_secao", 1));
            for (Document document : documents) {
                Cinema cinema = CinemaController.buscarRegistroPorId(document.getInteger("id_cinema"));
                Filme filme = FilmeController.buscarRegistroPorId(document.getInteger("id_filme"));
                listaResgistros.add(new Secao(document.getInteger("id_secao"),
                        document.getTimestamp("horario"),
                        cinema,
                        filme,
                        document.getInteger("qtd_assentos")));
            }
            return listaResgistros;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    public static int contarRegistros() {
        MongoCollection<Document> collection = database.getCollection("secao");

        try {
            long count = collection.countDocuments();
            return (int) count;
        } catch (Exception e) {
            return -999;
        }
    }

    public static boolean existeRegistro(int idSecao) {
        MongoCollection<Document> collection = database.getCollection("secao");

        try {
            long count = collection.countDocuments(new Document("id_secao", idSecao));
            return count > 0;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    private static int getMaiorId() {
        MongoCollection<Document> collection = database.getCollection("secao");

        try {
            Document document = collection.find().sort(new Document("id_secao", -1)).first();
            if (document != null) {
                return document.getInteger("id_secao");
            } else {
                return -500;
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return -999;
        }
    }
}
