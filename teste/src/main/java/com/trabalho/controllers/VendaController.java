package com.trabalho.controllers;

import com.mongodb.client.*;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.trabalho.utils.MenuFormatter;
import com.trabalho.models.*;

import java.util.LinkedList;

public class VendaController {

    private static final MongoClient client = MongoClients.create("mongodb://localhost:27017");
    private static final MongoDatabase database = client.getDatabase("cinema");
    private static final MongoCollection<Document> vendasCollection = database.getCollection("venda");

    // Insert a new Venda (sale)
    public static boolean inserirVenda(Venda venda) {
        try {
            Document document = new Document()
                .append("nome_cliente", venda.getNomeCliente())
                .append("assento", venda.getAssento())
                .append("forma_pagamento", venda.getFormaPagamento())
                .append("id_secao", venda.getSecao().getIdSecao());

            vendasCollection.insertOne(document);
            return true;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    // Delete a Venda by ID
    public static boolean excluirVenda(int idVenda) {
        try {
            long deletedCount = vendasCollection.deleteOne(Filters.eq("id_venda", idVenda)).getDeletedCount();
            return deletedCount > 0;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    // Delete all Vendas
    public static boolean excluirTodosRegistros() {
        try {
            vendasCollection.deleteMany(new Document());
            return true;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    // Update a Venda by ID
    public static boolean atualizarVenda(int idVenda, String nomeCliente, int assento, String formaPagamento, Secao secao) {
        try {
            Document updatedVenda = new Document()
                .append("nome_cliente", nomeCliente)
                .append("assento", assento)
                .append("forma_pagamento", formaPagamento)
                .append("id_secao", secao.getIdSecao());

            vendasCollection.updateOne(Filters.eq("id_venda", idVenda), new Document("$set", updatedVenda));
            return true;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    // Update a Venda using a Venda object
    public static boolean atualizarVenda(Venda venda) {
        return atualizarVenda(venda.getIdVenda(), venda.getNomeCliente(), venda.getAssento(), venda.getFormaPagamento(), venda.getSecao());
    }

    // Find a Venda by ID
    public static Venda buscarVendaPorId(int idVendaPesquisa) {
        try {
            Document doc = vendasCollection.find(Filters.eq("id_venda", idVendaPesquisa)).first();
            if (doc != null) {
                String nomeCliente = doc.getString("nome_cliente");
                int assento = doc.getInteger("assento");
                String formaPagamento = doc.getString("forma_pagamento");
                int idSecao = doc.getInteger("id_secao");

                Secao secao = SecaoController.buscarRegistroPorId(idSecao);
                return new Venda(idVendaPesquisa, nomeCliente, assento, formaPagamento, secao);
            } else {
                MenuFormatter.msgTerminalERROR("Venda não encontrada.");
                return null;
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    // List all Vendas
    public static LinkedList<Venda> listarTodosRegistros() {
        LinkedList<Venda> listaVendas = new LinkedList<>();
        try {
            FindIterable<Document> vendas = vendasCollection.find();
            for (Document doc : vendas) {
                int idVenda = doc.getInteger("id_venda");
                String nomeCliente = doc.getString("nome_cliente");
                int assento = doc.getInteger("assento");
                String formaPagamento = doc.getString("forma_pagamento");
                int idSecao = doc.getInteger("id_secao");

                Secao secao = SecaoController.buscarRegistroPorId(idSecao);
                listaVendas.add(new Venda(idVenda, nomeCliente, assento, formaPagamento, secao));
            }
            return listaVendas;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    // Count the number of Vendas
    public static int contarRegistros() {
        try {
            return (int) vendasCollection.countDocuments();
        } catch (Exception e) {
            return -999;
        }
    }

    // Check if a Venda exists by ID
    public static boolean existeVenda(int idVenda) {
        try {
            Document doc = vendasCollection.find(Filters.eq("id_venda", idVenda)).first();
            return doc != null;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    // Get the largest id of Venda (you can handle ObjectId if needed)
    private static int getMaiorId() {
        try {
            Document doc = vendasCollection.find().sort(new Document("id_venda", -1)).first();
            if (doc != null) {
                return doc.getInteger("id_venda");
            } else {
                return -500; // Default value if no documents found
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return -999;
        }
    }
}

