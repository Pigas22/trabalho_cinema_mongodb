package com.trabalho.models;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Sessao {
    private int idSessao;
    private int qtdAssentos;
    private Cinema cinema;
    private Filme filme;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp horario;
    
    
    public Sessao() {}

    public Sessao(int idSessao, Timestamp horario, Cinema cinema, Filme filme, int qtdAssentos) {
        this.idSessao = idSessao;
        this.horario = horario;
        this.qtdAssentos = qtdAssentos;
        this.cinema = cinema;
        this.filme = filme;
    }

    public Sessao(Timestamp horario, Cinema cinema, Filme filme, int qtdAssentos) {
        this.horario = horario;
        this.qtdAssentos = qtdAssentos;
        this.cinema = cinema;
        this.filme = filme;
    }

    public int getIdSessao() {
        return idSessao;
    }
    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public Timestamp getHorario() {
        return this.horario;
    }
    public void setHorario(Timestamp horario) {
        this.horario = horario;
    }

    public int getQtdAssentos() {
        return this.qtdAssentos;
    }
    public void setQtdAssentos(int qtdAssentos) {
        this.qtdAssentos = qtdAssentos;
    
    }
    
    public Cinema getCinema() {
        return this.cinema;
    }
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
    
    public Filme getFilme() {
        return this.filme;
    }
    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public String toString() {
        return "Sessao [" + "\n" + 
               "    idSessao = " + idSessao + ",\n" +  
               "    horario = " + horario + ",\n" + 
               "    idCinema = " + cinema.getIdCinema() + ",\n" + 
               "    idFilme = " + filme.getIdFilme() + ",\n" + 
               "    qtdAssentos = " + qtdAssentos + ",\n" + 
               "]";
    }


    @JsonProperty("idCinema")
    public void setCinemaById(int idCinema) {
        this.cinema = new Cinema();
        this.cinema.setIdCinema(idCinema);
    }

    @JsonProperty("idFilme")
    public void setFilmeById(int idFilme) {
        this.filme = new Filme ();
        this.filme.setIdFilme(idFilme);
    }
}