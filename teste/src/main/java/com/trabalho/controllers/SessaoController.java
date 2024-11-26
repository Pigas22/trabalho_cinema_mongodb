package com.trabalho.controllers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoCursor;

import com.trabalho.controllers.base.*;
import com.trabalho.models.*;
import com.trabalho.utils.*;

import java.util.LinkedList;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.bson.Document;

public class SessaoController extends ControllerBase implements IControllerBase<Sessao> {
    private MongoCollection<Document> sessaoCollection = null;

    public SessaoController() {
        super("sessao");
        this.sessaoCollection = super.getColecao();
    }

    @Override
    public boolean inserirRegistro(Sessao sessao) {
        try {
            int id = super.getMaiorId();

            if (id != -999 && id != -500) {
                Document doc = new Document("id_sessao", id+1)
                        .append("horario", sessao.getHorario())
                        .append("id_cinema", sessao.getCinema().getIdCinema())
                        .append("id_filme", sessao.getFilme().getIdFilme())
                        .append("qtd_assentos", sessao.getQtdAssentos());
    
                sessaoCollection.insertOne(doc);
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
    public boolean atualizarRegistro(int idRegistro, Sessao sessao) {
        try {
            if (this.existeRegistro(idRegistro)) {
                Document filtroId = new Document("id_sessao", idRegistro);
                Document atualizacao = new Document("$set", new Document("horario", sessao.getHorario())
                            .append("id_cinema", sessao.getCinema().getIdCinema())
                            .append("id_filme", sessao.getFilme().getIdFilme())
                            .append("qtd_assentos", sessao.getQtdAssentos()));
                
                sessaoCollection.updateOne(filtroId, atualizacao);
                return true;

            }
            return false;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    @Override
    public Sessao buscarRegistroPorId(int idPesquisa) {
        FilmeController filmeController = new FilmeController();
        CinemaController cinemaController = new CinemaController();

        try {
            Document result = sessaoCollection.find(Filters.eq("id_sessao", idPesquisa))
                    .first();

            if (result != null) {
                Timestamp horario = new Timestamp(result.getDate("horario").getTime());
                return new Sessao(idPesquisa,
                        horario,
                        cinemaController.buscarRegistroPorId(result.getInteger("id_cinema")),
                        filmeController.buscarRegistroPorId(result.getInteger("id_filme")),
                        result.getInteger("qtd_assentos"));
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
    public LinkedList<Sessao> listarTodosRegistros() {
        LinkedList<Sessao> listaRegistros = new LinkedList<>();
        FilmeController filmeController = new FilmeController();
        CinemaController cinemaController = new CinemaController();

        Timestamp horario;
        try {
            MongoCursor<Document> cursor = sessaoCollection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                horario = new Timestamp(doc.getDate("horario").getTime());
                listaRegistros.add(new Sessao(doc.getInteger("id_sessao"),
                        horario,
                        cinemaController.buscarRegistroPorId(doc.getInteger("id_cinema")),
                        filmeController.buscarRegistroPorId(doc.getInteger("id_filme")),
                        doc.getInteger("qtd_assentos")));
            }
            return listaRegistros;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }


    public static void main(String[] args) {
        SessaoController sessaoController = new SessaoController();
        CinemaController cinemaController = new CinemaController();
        FilmeController filmeController = new FilmeController();

        sessaoController.inserirRegistro(new Sessao(Timestamp.valueOf("2024-10-20 10:20:40"), 
                            cinemaController.buscarRegistroPorId(3), 
                            filmeController.buscarRegistroPorId(2), 
                            32));
        
        MenuFormatter.linha();
        System.out.println(sessaoController.buscarRegistroPorId(1));
        MenuFormatter.linha();
        sessaoController.atualizarRegistro(1, new Sessao(Timestamp.valueOf(LocalDateTime.now()), 
                                cinemaController.buscarRegistroPorId(3), 
                                filmeController.buscarRegistroPorId(2), 
                    32));
       
        MenuFormatter.linha();
        for (Sessao testSessao : sessaoController.listarTodosRegistros()) {
            System.out.println(testSessao);
            MenuFormatter.linha();
        }
    
        System.out.println("Número de Registros: " + sessaoController.contarRegistros());
        System.out.println("Maior Id: " + sessaoController.getMaiorId());
        System.out.println("Registro 7 existe: " + sessaoController.existeRegistro(2));
        System.out.println("Registro 7 foi excluído: " +sessaoController.excluirRegistro(2));
        System.out.println("Registro 7 existe: " +sessaoController.existeRegistro(2));
        System.out.println("Todos os registros foram excluídos: " +sessaoController.excluirTodosRegistros());
        
    }
}