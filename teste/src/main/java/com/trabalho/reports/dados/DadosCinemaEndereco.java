package com.trabalho.reports.dados;

public  class DadosCinemaEndereco {
    private int idCinema;
    private String nomeCinema;
    private String ruaEndereco;
    private String cidadeEndereco;

    public DadosCinemaEndereco(int idCinema, String nomeCinema, String ruaEndereco, String cidadeEndereco) {
        this.idCinema = idCinema;
        this.nomeCinema = nomeCinema;
        this.ruaEndereco = ruaEndereco;
        this.cidadeEndereco = cidadeEndereco;
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
    
    public String getRuaEndereco() {
        return ruaEndereco;
    }
    public void setRuaEndereco(String ruaEndereco) {
        this.ruaEndereco = ruaEndereco;
    }

    public String getCidadeEndereco() {
        return cidadeEndereco;
    }
    public void setCidadeEndereco(String cidadeEndereco) {
        this.cidadeEndereco = cidadeEndereco;
    }

    @Override
    public String toString() {
        return "DadosCinemaEndereco [" + "\n" +
            "   idCinema = " + idCinema + "\n" +
            "   nomeCinema = " + nomeCinema + "\n" +
            "   enderecoRua = " + ruaEndereco + "\n" +
            "   enderecoCidade = " + cidadeEndereco + "\n" +
            "]";
    }
}
