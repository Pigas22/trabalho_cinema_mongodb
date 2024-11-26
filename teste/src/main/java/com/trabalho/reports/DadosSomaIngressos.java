package com.trabalho.reports;

public class DadosSomaIngressos {
    private int secaoID;
    private String nomeFilme;
    private double valorTotal;

    public DadosSomaIngressos(int secaoID, String nomeFilme, double valorTotal) {
        this.secaoID = secaoID;
        this.nomeFilme = nomeFilme;
        this.valorTotal = valorTotal;
    }

    public int getSecaoID() {
        return secaoID;
    }
    public void setSecaoID(int secaoID) {
        this.secaoID = secaoID;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }
    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "DadosSomaIngressos [" + "\n" +
            "   secaoID = " + secaoID + "\n" +
            "   nomeFilme = " + nomeFilme + "\n" +
            "   valorTotal = " + valorTotal + "\n" +
            "]";
    }
}
