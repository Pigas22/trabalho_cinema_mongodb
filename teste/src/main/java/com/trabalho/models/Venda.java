package com.trabalho.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Venda {
    private int idVenda;
    private Cliente cliente;
    private int assento;
    private String formaPagamento;
    private Sessao sessao;

    public Venda() {}

    public Venda(int idVenda, Cliente cliente, int assento, String formaPagamento, Sessao sessao) {
        this.idVenda = idVenda;
        this.cliente = cliente;
        this.assento = assento;
        this.formaPagamento = formaPagamento;
        this.sessao = sessao;
    }

    public Venda(Cliente cliente, int assento, String formaPagamento, Sessao sessao) {
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return this.cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
               "    idCliente = " + cliente.getIdCliente() + ",\n" + 
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

    @JsonProperty("idCliente")
    public void setClienteById(int idCliente) {
        this.cliente = new Cliente();
        this.cliente.setIdClente(idCliente);
    }
}