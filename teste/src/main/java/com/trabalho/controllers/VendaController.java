package com.trabalho.controllers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import com.trabalho.controllers.base.*;
import com.trabalho.utils.*;
import com.trabalho.models.*;

import java.util.LinkedList;
import org.bson.Document;

public class VendaController extends ControllerBase implements IControllerBase<Venda> {
    private MongoCollection<Document> vendaCollection = null;

    public VendaController() {
        super("venda");
        this.vendaCollection = super.getColecao();
    }

    @Override
    public boolean inserirRegistro(Venda venda) {
        try {
            int id = super.getMaiorId();

            if (id != -999 && id != -500) {
                Document doc = new Document("id_venda", id+1)
                        .append("id_cliente", venda.getCliente().getIdCliente())
                        .append("assento", venda.getAssento())
                        .append("forma_pagamento", venda.getFormaPagamento())
                        .append("id_sessao", venda.getSessao().getIdSessao());
    
                vendaCollection.insertOne(doc);
                return true;

            } else {
                return false;
            }

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean atualizarRegistro(int idRegistro, Venda venda) {
        try {
            if (this.existeRegistro(idRegistro)) {
                Document filtroId = new Document("id_venda", idRegistro);
                Document atualizacao = new Document("$set", new Document("id_cliente", venda.getCliente().getIdCliente())
                            .append("assento", venda.getAssento())
                            .append("forma_pagamento", venda.getFormaPagamento())
                            .append("id_sessao", venda.getSessao().getIdSessao()));
                
                vendaCollection.updateOne(filtroId, atualizacao);
                return true;

            }
            return false;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    @Override
    public Venda buscarRegistroPorId(int idPesquisa) {
        ClienteController clienteController = new ClienteController();
        SessaoController sessaoController = new SessaoController();

        try {
            Document result = vendaCollection.find(Filters.eq("id_venda", idPesquisa))
                    .first();

            if (result != null) {
                return new Venda(idPesquisa,
                        clienteController.buscarRegistroPorId(result.getInteger("id_cliente")),
                        result.getInteger("assento"),
                        result.getString("forma_pagamento"),
                        sessaoController.buscarRegistroPorId(result.getInteger("id_sessao")));
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
    public LinkedList<Venda> listarTodosRegistros() {
        ClienteController clienteController = new ClienteController();
        SessaoController sessaoController = new SessaoController();
        LinkedList<Venda> listaRegistros = new LinkedList<>();

        try {
            MongoCursor<Document> cursor = vendaCollection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                listaRegistros.add(new Venda(doc.getInteger("id_venda"),
                        clienteController.buscarRegistroPorId(doc.getInteger("id_cliente")),
                        doc.getInteger("assento"),
                        doc.getString("forma_pagamento"),
                        sessaoController.buscarRegistroPorId(doc.getInteger("id_sessao"))));
            }
            return listaRegistros;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        VendaController vendaController = new VendaController();
        ClienteController clienteController = new ClienteController();
        SessaoController sessaoController = new SessaoController();

        vendaController.inserirRegistro(new Venda(clienteController.buscarRegistroPorId(1), 
                                23, 
                                "Pagamento Teste", 
                                sessaoController.buscarRegistroPorId(3)));
        
        MenuFormatter.linha();
        System.out.println(vendaController.buscarRegistroPorId(1));
        MenuFormatter.linha();
        vendaController.atualizarRegistro(1, new Venda(2, 
                                clienteController.buscarRegistroPorId(1), 
                                23, 
                                "Pagamento Teste", 
                                sessaoController.buscarRegistroPorId(3)));
       
        MenuFormatter.linha();
        for (Venda testVenda : vendaController.listarTodosRegistros()) {
            System.out.println(testVenda);
            MenuFormatter.linha();
        }
    
        System.out.println("Número de Registros: " + vendaController.contarRegistros());
        System.out.println("Maior Id: " + vendaController.getMaiorId());
        System.out.println("Registro 7 existe: " + vendaController.existeRegistro(2));
        System.out.println("Registro 7 foi excluído: " +vendaController.excluirRegistro(2));
        System.out.println("Registro 7 existe: " +vendaController.existeRegistro(2));
        System.out.println("Todos os registros foram excluídos: " +vendaController.excluirTodosRegistros());
        
    }
}
