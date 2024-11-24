package com.trabalho.models;

public class Cinema{
    private int idCinema;
    private String nomeCinema;
    private Endereco endereco;

    public Cinema(int idCinema,String nomeCinema,Endereco endereco){
        this.idCinema=idCinema;
        this.nomeCinema=nomeCinema;
        this.endereco=endereco;
    }

    public Cinema(String nomeCinema, Endereco endereco){
        this.nomeCinema = nomeCinema;
        this.endereco=endereco;
    }

    public int getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(int idCinema) {
        this.idCinema = idCinema;
    }

    public String getNomeCinema() {
        return nomeCinema;
    }

    public void setNomeCinema(String nomeCinema) {
        this.nomeCinema = nomeCinema;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cinema [idCinema=" + idCinema + ", nomeCinema=" + nomeCinema + ", endereco=" + endereco + "]";
    }

    
    
}