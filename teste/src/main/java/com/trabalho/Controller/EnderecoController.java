package com.trabalho.Controller;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.trabalho.models.Endereco;
import com.trabalho.utils.MenuFormatter;

import java.util.LinkedList;

public class EnderecoController {

    private static MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017"); // Conecta ao servidor MongoDB
    private static MongoDatabase database = mongoClient.getDatabase("meu_banco");  // Substitua pelo nome do seu banco de dados
    private static MongoCollection<Document> enderecoCollection = database.getCollection("endereco");

    // Inserir um novo Endereco
    public static boolean inserirRegistro(Endereco endereco) {
        try {
            Document doc = new Document("numero", endereco.getNumero())
                    .append("rua", endereco.getRua())
                    .append("bairro", endereco.getBairro())
                    .append("cidade", endereco.getCidade())
                    .append("uf", endereco.getUf());

            enderecoCollection.insertOne(doc);
            return true;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    // Excluir um Endereco específico pelo ID
    public static boolean excluirRegistro(int idEndereco) {
        try {
            if (existeRegistro(idEndereco)) {
                enderecoCollection.deleteOne(Filters.eq("_id", new ObjectId(String.valueOf(idEndereco))));
                return true;
            } else {
                MenuFormatter.msgTerminalERROR("Endereço não encontrado no Banco de Dados.");
                return false;
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    // Excluir todos os Enderecos
    public static boolean excluirTodosRegistros() {
        try {
            enderecoCollection.deleteMany(new Document());
            return true;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    // Atualizar um Endereco específico pelo ID
    public static boolean atualizarRegistro(int idEndereco, int numero, String rua, String bairro, String cidade, String uf) {
        try {
            if (existeRegistro(idEndereco)) {
                Document updateDoc = new Document("$set", new Document("numero", numero)
                        .append("rua", rua)
                        .append("bairro", bairro)
                        .append("cidade", cidade)
                        .append("uf", uf));
                enderecoCollection.updateOne(Filters.eq("_id", new ObjectId(String.valueOf(idEndereco))), updateDoc);
                return true;
            } else {
                MenuFormatter.msgTerminalERROR("Endereço não encontrado no Banco de Dados.");
                return false;
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    // Atualizar um objeto Endereco
    public static boolean atualizarRegistro(Endereco endereco) {
        return atualizarRegistro(endereco.getIdEndereco(), endereco.getNumero(), endereco.getRua(),
                endereco.getBairro(), endereco.getCidade(), endereco.getUf());
    }

    // Buscar um Endereco pelo ID
    public static Endereco buscarRegistroPorId(int idEnderecoPesquisa) {
        try {
            Document result = enderecoCollection.find(Filters.eq("_id", new ObjectId(String.valueOf(idEnderecoPesquisa))))
                    .first();

            if (result != null) {
                return new Endereco(result.getInteger("numero"),
                        result.getString("rua"),
                        result.getString("bairro"),
                        result.getString("cidade"),
                        result.getString("uf"));
            } else {
                MenuFormatter.msgTerminalERROR("Não encontrado nenhum registro com o ID informado.");
                return null;
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    // Listar todos os Enderecos
    public static LinkedList<Endereco> listarTodosRegistros() {
        LinkedList<Endereco> listaRegistros = new LinkedList<>();
        try {
            MongoCursor<Document> cursor = enderecoCollection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                listaRegistros.add(new Endereco(doc.getInteger("numero"),
                        doc.getString("rua"),
                        doc.getString("bairro"),
                        doc.getString("cidade"),
                        doc.getString("uf")));
            }
            return listaRegistros;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    // Contar o total de Enderecos
    public static int contarRegistros() {
        try {
            return (int) enderecoCollection.countDocuments();
        } catch (Exception e) {
            return -999;
        }
    }

    // Verificar se um Endereco específico existe
    public static boolean existeRegistro(int idEndereco) {
        try {
            long count = enderecoCollection.countDocuments(Filters.eq("_id", new ObjectId(String.valueOf(idEndereco))));
            return count > 0;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }
}

