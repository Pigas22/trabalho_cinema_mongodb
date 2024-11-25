package com.trabalho.utils;

import java.util.List;

import com.trabalho.models.*;

public class Dados {
    private List<Endereco> enderecos;
    private List<Cinema> cinemas;
    private List<Filme> filmes;
    private List<Sessao> sessoes;
    private List<Venda> vendas;

    // Getters e Setters
    public List<Endereco> getEnderecos() {
        return enderecos;
    }
    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }
    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }
    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }

    public List<Sessao> getSessoes() {
        return sessoes;
    }
    public void setSessoes(List<Sessao> sessoes) {
        this.sessoes = sessoes;
    }

    public List<Venda> getVendas() {
        return vendas;
    }
    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
}
