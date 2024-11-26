package com.trabalho.reports;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;

import java.util.LinkedList;
import java.util.List;

import org.bson.conversions.Bson;
import org.bson.Document;

import com.trabalho.connection.*;
import com.trabalho.utils.*;

abstract class Relatorio {
    public static LinkedList<DadosSomaIngressos> listaDadosSomaIngressos() {
        LinkedList<DadosSomaIngressos> relatSomaIngresso = new LinkedList<DadosSomaIngressos>();
        try {
            MongoCollection<Document> sessoes = DatabaseMongoDb.conectar().getCollection("sessao");

            // Agregação para calcular valor total por seção
            List<Bson> pipeline = List.of(
                Aggregates.group(
                    "$id_sessao",
                    Accumulators.sum("valor_total", "$preco"),
                    Accumulators.first("filme", "$filme")
                )
            );

            AggregateIterable<Document> resultados = sessoes.aggregate(pipeline);

            for (Document doc : resultados) {
                int secaoID = doc.getInteger("_id");
                String nomeFilme = doc.getString("filme");
                double valorTotal = doc.getDouble("valor_total");

                relatSomaIngresso.add(new DadosSomaIngressos(secaoID, nomeFilme, valorTotal));   
            }

            return relatSomaIngresso;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    public static LinkedList<DadosInformacaoSessoes> listarInfoSessoes() {
        LinkedList<DadosInformacaoSessoes> relatInfoSessoes = new LinkedList<DadosInformacaoSessoes>();
        try {
            MongoCollection<Document> sessoes = DatabaseMongoDb.conectar().getCollection("sessao");

            // Projeção para dados necessários
            FindIterable<Document> resultados = sessoes.find()
                .projection(new Document("horario", 1).append("filme", 1).append("qtd_assentos", 1).append("preco", 1));

            for (Document doc : resultados) {
                String horario = doc.getString("horario");
                String nomeFilme = doc.getString("filme");
                int qtdAssentos = doc.getInteger("qtd_assentos");
                double preco = doc.getDouble("preco");

                relatInfoSessoes.add(new DadosInformacaoSessoes (horario, nomeFilme, qtdAssentos,preco));
            }

            return relatInfoSessoes;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    public static LinkedList<DadosCinemaEndereco> listarCinemaEndereco() {
        LinkedList<DadosCinemaEndereco> relatCinemaEndereco = new LinkedList<DadosCinemaEndereco>();

        try {
            MongoCollection<Document> cinemas = DatabaseMongoDb.conectar().getCollection("cinema");

            // Projeção para nome do cinema e rua do endereço
            FindIterable<Document> resultados = cinemas.find()
                .projection(new Document("nome_cinema", 1).append("endereco.rua", 1));

            for (Document doc : resultados) {
                String nomeCinema = doc.getString("nome_cinema");
                String enderecoRua = doc.getEmbedded(List.of("endereco", "rua"), String.class);

                
                relatCinemaEndereco.add(new DadosCinemaEndereco(nomeCinema, enderecoRua));
            }

            return relatCinemaEndereco;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        /* TESTE 01
        for (DadosSomaIngressos item : Relatorio.listaDadosSomaIngressos()) {
            System.out.println(item);
            MenuFormatter.linha();
        }
        */

        /* TESTE 02
        for (DadosCinemaEndereco item : Relatorio.listarCinemaEndereco()) {
            System.out.println(item);
            MenuFormatter.linha();
        }
        */

        /* TESTE 03
        for (DadosInformacaoSessoes item : Relatorio.listarInfoSessoes()) {
            System.out.println(item);
            MenuFormatter.linha();
        }
        */
    }
}
