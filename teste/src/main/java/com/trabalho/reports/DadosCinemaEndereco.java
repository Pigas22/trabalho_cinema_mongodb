package com.trabalho.reports;

public  class DadosCinemaEndereco {
    private String nomeCinema;
    private String enderecoRua;

    public DadosCinemaEndereco(String nomeCinema, String enderecoRua) {
        this.nomeCinema = nomeCinema;
        this.enderecoRua = enderecoRua;
    }

    public String getNomeCinema() {
        return nomeCinema;
    }
    public void setNomeCinema(String nomeCinema) {
        this.nomeCinema = nomeCinema;
    }
    
    public String getEnderecoRua() {
        return enderecoRua;
    }
    public void setEnderecoRua(String enderecoRua) {
        this.enderecoRua = enderecoRua;
    }

    @Override
    public String toString() {
        return "DadosCinemaEndereco [" + "\n" +
            "   nomeCinema = " + nomeCinema + "\n" +
            "   enderecoRua = " + enderecoRua + "\n" +
            "]";
    }
}
