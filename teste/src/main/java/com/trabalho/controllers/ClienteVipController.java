package com.trabalho.controllers;

import java.util.LinkedList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import com.trabalho.controllers.base.*;
import com.trabalho.models.*;
import com.trabalho.utils.*;

public class ClienteVipController extends ControllerBase implements IControllerBase<ClienteVip> {
    private MongoCollection<Document> clienteVipCollection = null;

    public ClienteVipController() {
        super("cliente_vip");
        this.clienteVipCollection = super.getColecao();
    }

    @Override
    public boolean inserirRegistro(ClienteVip clienteVip) {
        try {
            int id = super.getMaiorId();

            if (id != -999 && id != -500) {
                Document doc = new Document("id_cliente", id+1)
                        .append("desconto", clienteVip.getDesconto())
                        .append("acesso_prioritario", clienteVip.isAcessoPrioritario());
    
                clienteVipCollection.insertOne(doc);
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
    public boolean atualizarRegistro(int idRegistro, ClienteVip clienteVip) {
        try {
            if (this.existeRegistro(idRegistro)) {
                Document filtroId = new Document("id_cliente", idRegistro);
                Document atualizacao = new Document("$set", new Document("id_cliente", clienteVip.getIdCliente())
                            .append("desconto", clienteVip.getDesconto())
                            .append("acesso_prioritario", clienteVip.isAcessoPrioritario()));
                
                clienteVipCollection.updateOne(filtroId, atualizacao);
                return true;

            }
            return false;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    @Override
    public ClienteVip buscarRegistroPorId(int idPesquisa) {
        ClienteController clienteController = new ClienteController();

        try {
            Document result = clienteVipCollection.find(Filters.eq("id_cliente", idPesquisa))
                    .first();

            if (result != null) {
                return new ClienteVip(clienteController.buscarRegistroPorId(idPesquisa),
                        result.getDouble("desconto"),
                        result.getBoolean("acesso_prioritario"));
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
    public LinkedList<ClienteVip> listarTodosRegistros() {
        ClienteController clienteController = new ClienteController();
        LinkedList<ClienteVip> listaRegistros = new LinkedList<>();

        try {
            MongoCursor<Document> cursor = clienteVipCollection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                listaRegistros.add(new ClienteVip(clienteController.buscarRegistroPorId(doc.getInteger("id_cliente")),
                        doc.getDouble("desconto"),
                        doc.getBoolean("acesso_prioritario")));
            }
            return listaRegistros;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }
}
