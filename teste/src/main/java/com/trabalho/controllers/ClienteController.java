package com.trabalho.controllers;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import com.trabalho.controllers.base.*;
import com.trabalho.models.*;
import com.trabalho.utils.*;

import java.util.LinkedList;

public class ClienteController extends ControllerBase implements IControllerBase<Cliente> {
    private MongoCollection<Document> clienteCollection = null;

    public ClienteController() {
        super("cliente");
        this.clienteCollection = super.getColecao();
    }

    @Override
    public boolean inserirRegistro(Cliente cliente) {
        try {
            int id = super.getMaiorId();

            if (id != -999 && id != -500) {
                Document doc = new Document("id_cliente", id+1)
                        .append("nome_cliente", cliente.getNomeCliente())
                        .append("cpf", cliente.getCpf())
                        .append("email", cliente.getEmail());
    
                clienteCollection.insertOne(doc);
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
    public boolean atualizarRegistro(int idRegistro, Cliente cliente) {
        try {
            if (this.existeRegistro(idRegistro)) {
                Document filtroId = new Document("id_cliente", idRegistro);
                Document atualizacao = new Document("$set", new Document("nome_cliente", cliente.getNomeCliente())
                            .append("cpf", cliente.getCpf())
                            .append("email", cliente.getEmail()));
                
                clienteCollection.updateOne(filtroId, atualizacao);
                return true;

            }
            return false;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    @Override
    public Cliente buscarRegistroPorId(int idPesquisa) {
        try {
            Document result = clienteCollection.find(Filters.eq("id_cliente", idPesquisa))
                    .first();

            if (result != null) {
                return new Cliente(idPesquisa,
                        result.getString("nome_cliente"),
                        result.getString("cpf"),
                        result.getString("email"));
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
    public LinkedList<Cliente> listarTodosRegistros() {
        LinkedList<Cliente> listaRegistros = new LinkedList<>();
        try {
            MongoCursor<Document> cursor = clienteCollection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                listaRegistros.add(new Cliente(doc.getInteger("id_cliente"),
                        doc.getString("nome_cliente"),
                        doc.getString("cpf"),
                        doc.getString("email")));
            }
            return listaRegistros;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }
}
