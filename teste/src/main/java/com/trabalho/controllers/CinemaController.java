package com.trabalho.controllers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import com.trabalho.controllers.base.*;
import com.trabalho.models.*;
import com.trabalho.utils.*;

import java.util.LinkedList;

public class CinemaController extends ControllerBase implements IControllerBase<Cinema> {
    private MongoCollection<Document> cinemaCollection = null;

    public CinemaController() {
        super("cinema");
        this.cinemaCollection = super.getColecao();
    }

    @Override
    public boolean inserirRegistro(Cinema cinema) {
        try {
            int id = super.getMaiorId();

            if (id != -999 && id != -500) {
                Document doc = new Document("id_cinema", id+1)
                        .append("nome_cinema", cinema.getNomeCinema())
                        .append("id_endereco", cinema.getEndereco().getIdEndereco());
    
                cinemaCollection.insertOne(doc);
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
    public boolean atualizarRegistro(int idRegistro, Cinema cinema) {
        try {
            if (this.existeRegistro(idRegistro)) {
                Document filtroId = new Document("id_cinema", idRegistro);
                Document atualizacao = new Document("$set", new Document("nome_cinema", cinema.getNomeCinema())
                            .append("id_endereco", cinema.getEndereco().getIdEndereco()));
                
                cinemaCollection.updateOne(filtroId, atualizacao);
                return true;

            }
            return false;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    @Override
    public Cinema buscarRegistroPorId(int idPesquisa) {
        EnderecoController enderecoController = new EnderecoController();
        try {
            Document result = cinemaCollection.find(Filters.eq("id_cinema", idPesquisa))
                    .first();

            if (result != null) {
                return new Cinema(idPesquisa,
                        result.getString("nome_cinema"),
                        enderecoController.buscarRegistroPorId(result.getInteger("id_endereco")));
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
    public LinkedList<Cinema> listarTodosRegistros() {
        LinkedList<Cinema> listaRegistros = new LinkedList<>();
        EnderecoController enderecoController = new EnderecoController();

        try {
            MongoCursor<Document> cursor = cinemaCollection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                listaRegistros.add(new Cinema(doc.getInteger("id_cinema"),
                        doc.getString("nome_cinema"),
                        enderecoController.buscarRegistroPorId(doc.getInteger("id_endereco"))));
            }
            return listaRegistros;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        CinemaController cinemaController = new CinemaController();
        EnderecoController enderecoController = new EnderecoController();

        cinemaController.inserirRegistro(new Cinema("Teste Cinema", enderecoController.buscarRegistroPorId(1)));
        MenuFormatter.linha();
        MenuFormatter.linha();
        cinemaController.buscarRegistroPorId(7);
        MenuFormatter.linha();
        cinemaController.atualizarRegistro(2, new Cinema("Teste Cinema 2", enderecoController.buscarRegistroPorId(3)));
        MenuFormatter.linha();
        cinemaController.buscarRegistroPorId(7);
       
        MenuFormatter.linha();
        for (Cinema testeCinema : cinemaController.listarTodosRegistros()) {
            System.out.println(testeCinema);
            MenuFormatter.linha();
        }
        
        System.out.println("Número de Registros: " + cinemaController.contarRegistros());
        System.out.println("Maior Id: " + cinemaController.getMaiorId());
       
        
        System.out.println("Registro 7 existe: " + cinemaController.existeRegistro(7));
        System.out.println("Registro 7 foi excluído: " +cinemaController.excluirRegistro(7));
        System.out.println("Registro 7 existe: " +cinemaController.existeRegistro(7));
        System.out.println("Todos os registros foram excluídos: " +cinemaController.excluirTodosRegistros());
    }
}

