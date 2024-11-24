package com.trabalho.utils;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Arquivo {
    private static boolean log = false;

    // Método para procurar o caminho absoluto de uma certa pasta (sem mensagens)
    public static String procuraPasta(String nomePasta) {
        String diretorioAtual = System.getProperty("user.dir");
        String caminhoCompleto = diretorioAtual + File.separator + nomePasta;
        File pasta = new File(caminhoCompleto);
        return pasta.getAbsolutePath();
    }

    // Método para buscar documentos de uma coleção MongoDB e retornar como uma String
    public static String lerMongoDB(MongoCollection<Document> collection) {
        StringBuilder resultado = new StringBuilder();

        try {
            for (Document doc : collection.find()) {
                resultado.append(doc.toJson()).append("\n");
            }
        } catch (Exception e) {
            return null;
        }

        return resultado.toString();
    }

    public static boolean logAtivo() {
        return Arquivo.log;
    }

    public static void setLog(boolean log) {
        Arquivo.log = log;
    }
}
