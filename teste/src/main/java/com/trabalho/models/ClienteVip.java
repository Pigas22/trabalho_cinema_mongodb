package com.trabalho.models;

public class ClienteVip extends Cliente {
    private double desconto;  // Porcentagem de desconto
    private boolean acessoPrioritario;

    // Construtor da classe ClienteVIP com desconto
    public ClienteVip(String nome, String cpf, String email, int idCliente, double desconto, boolean acessoPrioritario) {
        super(nome, cpf, email, idCliente);  // Chama o construtor da classe pai (Cliente)
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
}
