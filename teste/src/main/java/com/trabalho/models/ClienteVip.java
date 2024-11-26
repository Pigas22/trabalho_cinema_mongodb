package com.trabalho.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteVip extends Cliente {
    private double desconto;
    private boolean acessoPrioritario;

    public ClienteVip () {}

    public ClienteVip(int idCliente, String nomeCliente, String cpf, String email, double desconto, boolean acessoPrioritario) {
        super(idCliente, nomeCliente, cpf, email);
        this.desconto = desconto;
        this.acessoPrioritario = acessoPrioritario;
    }

    public ClienteVip(String nomeCliente, String cpf, String email, double desconto, boolean acessoPrioritario) {
        super(nomeCliente, cpf, email);
        this.desconto = desconto;
        this.acessoPrioritario = acessoPrioritario;
    }

    public ClienteVip(Cliente cliente, double desconto, boolean acessoPrioritario) {
        super(cliente.getIdCliente(), cliente.getNomeCliente(), cliente.getCpf(), cliente.getCpf());
        this.desconto = desconto;
        this.acessoPrioritario = acessoPrioritario;
    }

    // Getters e Setters
    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public boolean isAcessoPrioritario() {
        return acessoPrioritario;
    }

    public void setAcessoPrioritario(boolean acessoPrioritario) {
        this.acessoPrioritario = acessoPrioritario;
    }

    // Método para calcular o valor com desconto
    public double calcularDesconto(double valorCompra) {
        return valorCompra - (valorCompra * (desconto / 100));
    }

    @JsonProperty("idCliente")
    public void setClienteById(int idCliente) {
        this.setIdClente(idCliente);
    }
}
