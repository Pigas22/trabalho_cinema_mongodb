package com.trabalho.controllers;

import com.trabalho.conexion.*;
import com.trabalho.utils.*;
import com.trabalho.models.*;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedList;

public class SessaoController {
    public static boolean inserirRegistro (Sessao sessao) {
        String sql = "INSERT INTO sessao (id_sessao, horario, id_cinema, id_filme, qtd_assentos) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int idSessao = SessaoController.getMaiorId();
            if (idSessao == -500) {
                idSessao = 0;

            } else if (idSessao == -999) {
                return false;
            }
            
            pstmt.setInt(1, idSessao+1);
            pstmt.setTimestamp(2, sessao.getHorario());
            pstmt.setInt(3, sessao.getCinema().getIdCinema());
            pstmt.setInt(4, sessao.getFilme().getIdFilme());
            pstmt.setInt(5, sessao.getQtdAssentos());

            pstmt.executeUpdate();

            return true;
            
        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }
    
    public static boolean excluirRegistro (int idSessao) {
        String sql = "DELETE FROM sessao WHERE id_sessao = ?;";

        if (SessaoController.existeRegistro(idSessao)) {
            try (Connection conn = Database.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, idSessao);
                pstmt.executeUpdate();

                return true;

            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return false;
            }
            
        } else {
            MenuFormatter.msgTerminalERROR("Endereço não encontrado no Banco de Dados.");
            return false;
        }
    }

    public static boolean excluirTodosRegistros () {
        try (Connection conn = Database.conectar();
            Statement stmt = conn.createStatement()) {

                String sql = "DELETE FROM sessao;";
                stmt.executeUpdate(sql);

                return true;
                

            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return false;
            }
    }

    public static boolean atualizarRegistro (int idSessao, Timestamp horario, int idCinema, int idFilme, int qtdAssentos) {
        String sql = "UPDATE sessao SET horario = ?, id_cinema = ?, id_filme = ?, qtd_assentos = ? WHERE id_sessao = ?;";

        if (FilmeController.existeRegistro(idFilme)) {
            try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
                pstmt.setTimestamp(1, horario);
                pstmt.setInt(2, idCinema);
                pstmt.setInt(3, idFilme);
                pstmt.setInt(4, qtdAssentos);
                pstmt.setInt(5, idSessao);

            pstmt.executeUpdate();

            return true;
            
            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return false;
            }
            
        } else {
            MenuFormatter.msgTerminalERROR("Endereço não encontrado no Banco de Dados.");
            return false;
        }
    }

    public static boolean atualizarRegistro (Sessao sessao) {
        String sql = "UPDATE sessao SET horario = ?, id_cinema = ?, id_filme = ?, qtd_assentos = ? WHERE id_sessao = ?;";

        if (FilmeController.existeRegistro(sessao.getIdSessao())) {
            try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
                pstmt.setTimestamp(1, sessao.getHorario());
                pstmt.setInt(2, sessao.getCinema().getIdCinema());
                pstmt.setInt(3, sessao.getFilme().getIdFilme());
                pstmt.setInt(4, sessao.getQtdAssentos());
                pstmt.setInt(5, sessao.getIdSessao());

            pstmt.executeUpdate();

            return true;
            
            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return false;
            }
            
        } else {
            MenuFormatter.msgTerminalERROR("Endereço não encontrado no Banco de Dados.");
            return false;
        }
    }
   
    public static Sessao buscarRegistroPorId (int idSessaoPesquisa) {
        if (SessaoController.existeRegistro(idSessaoPesquisa)) {
            String sql = "SELECT * FROM sessao WHERE id_sessao = ?";
    
            // Inicialização com valores irreais
            int idSessao = -500, idCinema  = -500, idFilme = -500, qtdAssentos = -500;
            Timestamp horairo = null;
    
            try (Connection conn = Database.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
                pstmt.setInt(1, idSessaoPesquisa);
    
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    idSessao = rs.getInt("id_sassao");
                    horairo = rs.getTimestamp("horario");
                    idCinema = rs.getInt("id_cinema");
                    idFilme = rs.getInt("id_filme");
                    qtdAssentos = rs.getInt("qtd_assentos");
                }

                Cinema cinema = CinemaController.buscarRegistroPorId(idCinema);
                Filme filme = FilmeController.buscarRegistroPorId(idFilme);
                
                return new Sessao(idSessao, horairo, cinema, filme, qtdAssentos);
    
            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return null;
            }

        } else {
            MenuFormatter.msgTerminalERROR("Não encontrado nenhum resgistro com o ID informado.");
            return null;
        }

    }

    public static LinkedList<Sessao> listarTodosRegistros () {
        LinkedList<Sessao> listaResgistros = new LinkedList<Sessao>();
        String sql = "SELECT * FROM sessao ORDER BY id_sessao ASC";

        int idSessao, idCinema, idFilme, qtdAssentos;
        Timestamp horairo;
        Cinema cinema;
        Filme filme;

        try (Connection conn = Database.conectar();
            Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                idSessao = rs.getInt("id_sessao");
                horairo = rs.getTimestamp("horario");
                idCinema = rs.getInt("id_cinema");
                idFilme = rs.getInt("id_filme");
                qtdAssentos = rs.getInt("qtd_assentos");

                cinema = CinemaController.buscarRegistroPorId(idCinema);
                filme = FilmeController.buscarRegistroPorId(idFilme);

                listaResgistros.add(new Sessao(idSessao, horairo, cinema, filme, qtdAssentos));
            }

            return listaResgistros;

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }
    
    public static int contarRegistros () {
        try (Connection conn = Database.conectar();
            Statement stmt = conn.createStatement()) {
            
            int qtdSessao = 0;
            ResultSet rs = stmt.executeQuery("SELECT COUNT(id_sessao) AS resultado FROM sessao;");
            
            while (rs.next()) {
                qtdSessao = rs.getInt("resultado");
            }

            return qtdSessao;

        } catch (SQLException e) {
            return -999;
        }
    }

    public static boolean existeRegistro (int idSessao) {
        String sql = "SELECT COUNT(id_sessao) AS resultado FROM sessao WHERE id_sessao = ?;";
        int qtdSessao = 0;

        try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idSessao);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                qtdSessao = rs.getInt("resultado");
            }

            if (qtdSessao == 0) {
                return false;
                
            } else {
                return true;
            }

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    private static int getMaiorId() {
        String sql = "SELECT id_sessao AS resultado FROM sessao ORDER BY id_sessao DESC LIMIT 1;";
        int ultimoId = -500;

        try (Connection conn = Database.conectar();
            Statement pstmt = conn.createStatement()) {

            ResultSet rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                ultimoId = rs.getInt("resultado");
            }

            return ultimoId;

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return -999;
        }
    }
}