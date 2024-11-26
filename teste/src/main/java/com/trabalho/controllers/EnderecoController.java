package com.trabalho.controllers;

import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import com.mongodb.client.*;
import org.bson.Document;

import com.trabalho.connection.*;
import com.trabalho.models.*;
import com.trabalho.utils.*;

import java.util.LinkedList;

public class EnderecoController implements ControllerBase<Endereco> {
    private MongoCollection<Document> enderecoCollection = null;

    public EnderecoController() {
        this.enderecoCollection = DatabaseMongoDb.conectar().getCollection("endereco");
    }

    @Override
    public boolean inserirRegistro(Endereco endereco) {
        try {
            Document doc = new Document("numero", endereco.getNumero())
                    .append("rua", endereco.getRua())
                    .append("bairro", endereco.getBairro())
                    .append("cidade", endereco.getCidade())
                    .append("uf", endereco.getUf());

            enderecoCollection.insertOne(doc);
            return true;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean excluirRegistro(int idEndereco) {
        try {
            if (existeRegistro(idEndereco)) {
                enderecoCollection.deleteOne(Filters.eq("_id", new ObjectId(String.valueOf(idEndereco))));
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

    @Override
    public boolean excluirTodosRegistros() {
        try {
            enderecoCollection.deleteMany(new Document());
            return true;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    public boolean atualizarRegistro(int idEndereco, int numero, String rua, String bairro, String cidade, String uf) {
        try {
            if (existeRegistro(idEndereco)) {
                Document updateDoc = new Document("$set", new Document("numero", numero)
                        .append("rua", rua)
                        .append("bairro", bairro)
                        .append("cidade", cidade)
                        .append("uf", uf));
                enderecoCollection.updateOne(Filters.eq("_id", new ObjectId(String.valueOf(idEndereco))), updateDoc);
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

    @Override
    public boolean atualizarRegistro(Endereco endereco) {
        return atualizarRegistro(endereco.getIdEndereco(), endereco.getNumero(), endereco.getRua(),
                endereco.getBairro(), endereco.getCidade(), endereco.getUf());
    }

    @Override
    public Endereco buscarRegistroPorId(int idEnderecoPesquisa) {
        try {
            Document result = enderecoCollection.find(Filters.eq("_id", new ObjectId(String.valueOf(idEnderecoPesquisa))))
                    .first();

            if (result != null) {
                return new Endereco(result.getInteger("numero"),
                        result.getString("rua"),
                        result.getString("bairro"),
                        result.getString("cidade"),
                        result.getString("uf"));
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
    public LinkedList<Endereco> listarTodosRegistros() {
        LinkedList<Endereco> listaRegistros = new LinkedList<>();
        try {
            MongoCursor<Document> cursor = enderecoCollection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                listaRegistros.add(new Endereco(doc.getInteger("numero"),
                        doc.getString("rua"),
                        doc.getString("bairro"),
                        doc.getString("cidade"),
                        doc.getString("uf")));
            }
            return listaRegistros;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    @Override
    public int contarRegistros() {
        try {
            return (int) enderecoCollection.countDocuments();

        } catch (Exception e) {
            return -999;
        }
    }

    @Override
    public boolean existeRegistro(int idEndereco) {
        try {
            long count = enderecoCollection.countDocuments(Filters.eq("_id", new ObjectId(String.valueOf(idEndereco))));
            return count > 0;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        EnderecoController enderecoController = new EnderecoController();

        enderecoController.inserirRegistro(new Endereco(7, 50, "Rua dos Eucaliptos", "DM", "ES"));
    }
}
