package com.trabalho.models;

public class Cliente {
    private int idCliente;
    private String nomeCliente;
    private String cpf;
    private String email;

    public Cliente () {}

    public Cliente (int idCliente, String nomeCliente, String cpf, String email) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.cpf = cpf;
        this.email = email;
    }

    public Cliente (String nomeCliente, String cpf, String email) {
        this.nomeCliente = nomeCliente;
        this.cpf = cpf;
        this.email = email;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
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

    @Override
    public String toString() {
        return "Cliente [" + "\n" + 
            "   idCliente = " + idCliente + ",\n" + 
            "   nomeCliente = " + nomeCliente + ",\n" + 
            "   email = " + email + ",\n" + 
            "   cpf = " + cpf + "\n" + 
            "]";
    }
}
