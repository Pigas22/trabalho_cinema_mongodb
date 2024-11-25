package com.trabalho.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.io.File;
import java.io.IOException;

public class Arquivo {
    private static final boolean LOG = false;
    private static final String PROJECT_PATH = "teste\\src\\main\\java\\com\\trabalho";

    // Método para procurar o caminho absoluto de uma certa pasta (sem mensagens)
    public static String procuraPasta(String nomePasta) {
        String diretorioAtual = System.getProperty("user.dir");
        String caminhoCompleto = diretorioAtual + File.separator + PROJECT_PATH + File.separator + nomePasta;
        File pasta = new File(caminhoCompleto);
        return pasta.getAbsolutePath();
    }

    public static Dados lerJSON(String caminhoArquivo) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(caminhoArquivo), Dados.class);

        } catch (IOException e) {
            MenuFormatter.msgTerminalERROR("Erro ao ler o arquivo JSON: " + e.getMessage());
            return null;
        }
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

    public static boolean isLog() {
        return LOG;
    }
}
