package com.trabalho.reports.dados;

public class DadosSomaIngressos {
    private int sessaoID;
    private String nomeFilme;
    private String valorTotal;

    public DadosSomaIngressos(int sessaoID, String nomeFilme, String valorTotal) {
        this.sessaoID = sessaoID;
        this.nomeFilme = nomeFilme;
        this.valorTotal = valorTotal;
    }

    public int getSessaoID() {
        return sessaoID;
    }
    public void setSessaoID(int sessaoID) {
        this.sessaoID = sessaoID;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }
    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public String getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "DadosSomaIngressos [" + "\n" +
            "   sessaoID = " + sessaoID + "\n" +
            "   nomeFilme = " + nomeFilme + "\n" +
            "   valorTotal = " + valorTotal + "\n" +
            "]";
    }
}
