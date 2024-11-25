package com.trabalho.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Venda {
    private int idVenda;
    private String nomeCliente;
    private int assento;
    private String formaPagamento;
    private Sessao sessao;

    public Venda() {}

    public Venda(int idVenda, String nomeCliente, int assento, String formaPagamento, Sessao sessao) {
        this.idVenda = idVenda;
        this.nomeCliente = nomeCliente;
        this.assento = assento;
        this.formaPagamento = formaPagamento;
        this.sessao = sessao;
    }

    public Venda(String nomeCliente, int assento, String formaPagamento, Sessao sessao) {
        this.nomeCliente = nomeCliente;
        this.assento = assento;
        this.formaPagamento = formaPagamento;
        this.sessao = sessao;
    }

    public int getIdVenda() {
        return idVenda;
    }
    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getAssento() {
        return this.assento;
    }
    public void setAssento(int assento) {
        this.assento = assento;
    }

    public String getFormaPagamento() {
        return this.formaPagamento;
    }
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    public Sessao getSessao() {
        return this.sessao;
    }
    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public String toString() {
        return "Venda [" + "\n" + 
               "    idVenda = " + idVenda + ",\n" +  
               "    nomeCliente = " + nomeCliente + ",\n" + 
               "    assento = " + assento + ",\n" + 
               "    formaPagamento = " + formaPagamento + ",\n" + 
               "    idSessao = " + sessao.getIdSessao() + ",\n" + 
               "]";
    }
    

    @JsonProperty("idSessao")
    public void setSessaoById(int idSessao) {
        this.sessao = new Sessao();
        this.sessao.setIdSessao(idSessao);
    }
}