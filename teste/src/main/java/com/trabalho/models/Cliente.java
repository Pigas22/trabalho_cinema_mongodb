package com.trabalho.models;

public class Cliente {
    private String nome;
    private String cpf;
    private String email;
    private int idCliente;

    public Cliente(String nome, String cpf, String email, int idCliente) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.idCliente = idCliente;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getIdCliente() {
        return idCliente;
    }
    public void setIdClente(int idCliente) {
        this.idCliente = idCliente;
    }
}
