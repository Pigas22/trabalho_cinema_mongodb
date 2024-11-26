package com.trabalho.reports;

public class DadosInformacaoSessoes {
    private String horario;
    private String nomeFilme;
    private int qtdAssentos;
    private double preco;

    public DadosInformacaoSessoes(String horario, String nomeFilme, int qtdAssentos, double preco) {
        this.horario = horario;
        this.nomeFilme = nomeFilme;
        this.qtdAssentos = qtdAssentos;
        this.preco = preco;
    }

    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }
    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public int getQtdAssentos() {
        return qtdAssentos;
    }
    public void setQtdAssentos(int qtdAssentos) {
        this.qtdAssentos = qtdAssentos;
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "DadosInformacaoSessoes [" + "\n" +
            "   horario = " + horario + "\n" +
            "   nomeFilme = " + nomeFilme + "\n" +
            "   qtdAssentos = " + qtdAssentos + "\n" +
            "   preco = " + preco + "\n" +
            "]";
    }
}
