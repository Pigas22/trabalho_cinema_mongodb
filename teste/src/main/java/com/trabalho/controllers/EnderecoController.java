package com.trabalho.controllers;

import com.mongodb.client.model.Filters;
import com.mongodb.client.*;
import org.bson.Document;

import com.trabalho.controllers.base.*;
import com.trabalho.models.*;
import com.trabalho.utils.*;

import java.util.LinkedList;

public class EnderecoController extends ControllerBase implements IControllerBase<Endereco> {
    private MongoCollection<Document> enderecoCollection = null;

    public EnderecoController() {
        super("endereco");
        this.enderecoCollection = super.getColecao();
    }

    @Override
    public boolean inserirRegistro(Endereco endereco) {
        try {
            int id = super.getMaiorId();

            if (id != -999 && id != -500) {
                Document doc = new Document("id_endereco", id+1)
                        .append("numero", endereco.getNumero())
                        .append("rua", endereco.getRua())
                        .append("bairro", endereco.getBairro())
                        .append("cidade", endereco.getCidade())
                        .append("uf", endereco.getUf());
    
                enderecoCollection.insertOne(doc);
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
    public boolean atualizarRegistro(int idRegistro, Endereco endereco) {
        try {
            if (this.existeRegistro(idRegistro)) {
                Document filtroId = new Document("id_endereco", idRegistro);
                Document atualizacao = new Document("$set", new Document("numero", endereco.getNumero())
                            .append("rua", endereco.getRua())
                            .append("bairro", endereco.getBairro())
                            .append("cidade", endereco.getCidade())
                            .append("uf", endereco.getUf()));
                
                enderecoCollection.updateOne(filtroId, atualizacao);
                return true;

            }
            return false;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    @Override
    public Endereco buscarRegistroPorId(int idEnderecoPesquisa) {
        try {
            Document result = enderecoCollection.find(Filters.eq("id_endereco", idEnderecoPesquisa))
                    .first();

            if (result != null) {
                return new Endereco(idEnderecoPesquisa,
                        result.getInteger("numero"),
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
                listaRegistros.add(new Endereco(doc.getInteger("id_endereco"),
                        doc.getInteger("numero"),
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

    public static void main(String[] args) {
        EnderecoController enderecoController = new EnderecoController();
        enderecoController.inserirRegistro(new Endereco(50, "Rua dos Eucaliptos", "DM", "ES"));
        /*
        MenuFormatter.linha();
        MenuFormatter.linha();
        System.out.println(enderecoController.buscarRegistroPorId(7));
        MenuFormatter.linha();
        enderecoController.atualizarRegistro(7, new Endereco(80, "Rua dos Testes", "MF", "ES"));
        MenuFormatter.linha();
        System.out.println(enderecoController.buscarRegistroPorId(7));
       
        MenuFormatter.linha();
        for (Endereco testEndereco : enderecoController.listarTodosRegistros()) {
            System.out.println(testEndereco);
            MenuFormatter.linha();
        }
        */

        System.out.println("Número de Registros: " + enderecoController.contarRegistros());
        System.out.println("Maior Id: " + enderecoController.getMaiorId());
       
        /*
        System.out.println("Registro 7 existe: " + enderecoController.existeRegistro(7));
        System.out.println("Registro 7 foi excluído: " +enderecoController.excluirRegistro(7));
        System.out.println("Registro 7 existe: " +enderecoController.existeRegistro(7));
        System.out.println("Todos os registros foram excluídos: " +enderecoController.excluirTodosRegistros());
        */
    }
}
