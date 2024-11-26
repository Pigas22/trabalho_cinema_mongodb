package com.trabalho.controllers;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import com.trabalho.controllers.base.*;
import com.trabalho.models.*;
import com.trabalho.utils.*;

import java.util.LinkedList;

public class FilmeController extends ControllerBase implements IControllerBase<Filme> {
    private MongoCollection<Document> filmeCollection = null;

    public FilmeController() {
        super("filme");
        this.filmeCollection = super.getColecao();
    }

    @Override
    public boolean inserirRegistro(Filme filme) {
        try {
            int id = super.getMaiorId();

            if (id != -999 && id != -500) {
                Document doc = new Document("id_filme", id+1)
                        .append("nome_filme", filme.getNomeFilme())
                        .append("preco", filme.getPreco());
    
                filmeCollection.insertOne(doc);
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
    public boolean atualizarRegistro(int idRegistro, Filme filme) {
        try {
            if (this.existeRegistro(idRegistro)) {
                Document filtroId = new Document("id_filme", idRegistro);
                Document atualizacao = new Document("$set", new Document("nome_filme", filme.getNomeFilme())
                            .append("preco", filme.getPreco()));
                
                filmeCollection.updateOne(filtroId, atualizacao);
                return true;

            }
            return false;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    @Override
    public Filme buscarRegistroPorId(int idPesquisa) {
        try {
            Document result = filmeCollection.find(Filters.eq("id_filme", idPesquisa))
                    .first();

            if (result != null) {
                return new Filme(idPesquisa,
                        result.getString("nome_filme"),
                        result.getDouble("preco"));
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
    public LinkedList<Filme> listarTodosRegistros() {
        LinkedList<Filme> listaRegistros = new LinkedList<>();
        try {
            MongoCursor<Document> cursor = filmeCollection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                listaRegistros.add(new Filme(doc.getInteger("id_filme"),
                        doc.getString("nome_filme"),
                        doc.getDouble("preco")));
            }
            return listaRegistros;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        FilmeController filmeController = new FilmeController();
        filmeController.inserirRegistro(new Filme("Filme Teste", 15.78));
        
        MenuFormatter.linha();
        System.out.println(filmeController.buscarRegistroPorId(1));
        MenuFormatter.linha();
        filmeController.atualizarRegistro(1, new Filme("Deadpool e Wolverine", 20.60));
       
        MenuFormatter.linha();
        for (Filme testfilme : filmeController.listarTodosRegistros()) {
            System.out.println(testfilme);
            MenuFormatter.linha();
        }
    
        System.out.println("Número de Registros: " + filmeController.contarRegistros());
        System.out.println("Maior Id: " + filmeController.getMaiorId());
        System.out.println("Registro 7 existe: " + filmeController.existeRegistro(2));
        System.out.println("Registro 7 foi excluído: " +filmeController.excluirRegistro(2));
        System.out.println("Registro 7 existe: " +filmeController.existeRegistro(2));
        System.out.println("Todos os registros foram excluídos: " +filmeController.excluirTodosRegistros());
        
    }
}
