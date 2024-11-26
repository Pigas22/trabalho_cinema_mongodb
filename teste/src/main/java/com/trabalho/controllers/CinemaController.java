package com.trabalho.controllers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClients;
import org.bson.Document;

import com.trabalho.connection.DatabaseMongoDb;
import com.trabalho.models.*;
import com.trabalho.utils.*;

import java.util.LinkedList;
import java.util.List;

public class CinemaController implements ControllerBase<Cinema> {
    private static MongoCollection<Document> cinemaCollection = DatabaseMongoDb.conectar().getCollection("cinemas");

    @Override
    public static boolean inserirRegistro(Cinema cinema) {
        try {
            MongoCollection<Document> collection = getCollection();
           
            // Prepare the document for insertion
            Document doc = new Document("id_cinema", getMaiorId() + 1)
                    .append("nome_cinema", cinema.getNomeCinema())
                    .append("id_endereco", cinema.getEndereco().getIdEndereco());

            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao inserir cinema: " + e.getMessage());
            return false;
        }
    }

    @Override
    public static boolean excluirRegistro(int idCinema) {
        try {
            MongoCollection<Document> collection = getCollection();
            Document filter = new Document("id_cinema", idCinema);
            long deletedCount = collection.deleteOne(filter).getDeletedCount();

            if (deletedCount > 0) {
                return true;
            } else {
                MenuFormatter.msgTerminalERROR("Cinema não encontrado no Banco de Dados.");
                return false;
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    @Override
    public static boolean atualizarRegistro(int idCinema, String nome, int idEndereco) {
        try {
            MongoCollection<Document> collection = getCollection();
            Document filter = new Document("id_cinema", idCinema);
            Document update = new Document("$set", new Document("nome_cinema", nome).append("id_endereco", idEndereco));

            long updatedCount = collection.updateOne(filter, update).getModifiedCount();
            return updatedCount > 0;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    @Override
    public static boolean atualizarRegistro(Cinema cinema) {
        return atualizarRegistro(cinema.getIdCinema(), cinema.getNomeCinema(), cinema.getEndereco().getIdEndereco());
    }

    @Override
    public static Cinema buscarRegistroPorId(int idCinemaPesquisa) {
        try {
            MongoCollection<Document> collection = getCollection();
            Document filter = new Document("id_cinema", idCinemaPesquisa);
            Document doc = collection.find(filter).first();

            if (doc != null) {
                int idCinema = doc.getInteger("id_cinema");
                String nome = doc.getString("nome_cinema");
                int idEndereco = doc.getInteger("id_endereco");
                return new Cinema(idCinema, nome, EnderecoController.buscarRegistroPorId(idEndereco));
            } else {
                MenuFormatter.msgTerminalERROR("Não encontrado nenhum registro com o ID informado.");
                return null;
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    @Override
    public static LinkedList<Cinema> listarTodosRegistros() {
        LinkedList<Cinema> listaCinemas = new LinkedList<>();
        try {
            MongoCollection<Document> collection = getCollection();
            for (Document doc : collection.find()) {
                int idCinema = doc.getInteger("id_cinema");
                String nome = doc.getString("nome_cinema");
                int idEndereco = doc.getInteger("id_endereco");
                listaCinemas.add(new Cinema(idCinema, nome, EnderecoController.buscarRegistroPorId(idEndereco)));
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
        }
        return listaCinemas;
    }

    @Override
    public static boolean existeRegistro(int idCinema) {
        try {
            MongoCollection<Document> collection = getCollection();
            Document filter = new Document("id_cinema", idCinema);
            long count = collection.countDocuments(filter);

            return count > 0;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    @Override
    private static int getMaiorId() {
        try {
            MongoCollection<Document> collection = getCollection();
            Document maxIdDoc = collection.aggregate(
                    List.of(new Document("$sort", new Document("id_cinema", -1)),
                            new Document("$limit", 1),
                            new Document("$project", new Document("id_cinema", 1)))
            ).first();
           
            return maxIdDoc != null ? maxIdDoc.getInteger("id_cinema") : 0;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return -999;
        }
    }

    @Override
    public static int contarRegistros() {
        try {
            MongoCollection<Document> collection = getCollection();
            return (int) collection.countDocuments();
        } catch (Exception e) {
            return -999;
        }
    }
}

