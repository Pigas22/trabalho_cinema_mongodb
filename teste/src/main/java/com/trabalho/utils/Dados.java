package com.trabalho.utils;

import java.util.List;

import com.trabalho.models.*;

public class Dados {
    private List<Endereco> endereco;
    private List<Cinema> cinema;
    private List<Filme> filme;
    private List<Sessao> sessao;
    private List<Venda> venda;

    // Getters e Setters
    public List<Endereco> getEndereco() {
        return endereco;
    }
    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }

    public List<Cinema> getCinema() {
        return cinema;
    }
    public void setCinema(List<Cinema> cinema) {
        this.cinema = cinema;
    }

    public List<Filme> getFilme() {
        return filme;
    }
    public void setFilme(List<Filme> filme) {
        this.filme = filme;
    }

    public List<Sessao> getSessao() {
        return sessao;
    }
    public void setSessao(List<Sessao> sessao) {
        this.sessao = sessao;
    }

    public List<Venda> getVenda() {
        return venda;
    }
    public void setVenda(List<Venda> venda) {
        this.venda = venda;
    }
}
