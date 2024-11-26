package com.trabalho.controllers;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.trabalho.models.Cliente;
import com.trabalho.utils.MenuFormatter;

import java.util.LinkedList;

public class ClienteController {

    private static MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017"); // Conecta ao servidor MongoDB
    private static MongoDatabase database = mongoClient.getDatabase("meu_banco");  // Substitua pelo nome do seu banco de dados
    private static MongoCollection<Document> clienteCollection = database.getCollection("clientes"); // Nome da coleção de clientes

    // Inserir um novo Cliente
    public static boolean inserirRegistro(Cliente cliente) {
        try {
            Document doc = new Document("nome", cliente.getNome())
                    .append("cpf", cliente.getCpf())
                    .append("email", cliente.getEmail())
                    .append("idCliente", cliente.getIdCliente()); // Usando idCliente

            clienteCollection.insertOne(doc);
            return true;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    // Excluir um Cliente específico pelo ID
    public static boolean excluirRegistro(int idCliente) {
        try {
            if (existeRegistro(idCliente)) {
                clienteCollection.deleteOne(Filters.eq("idCliente", idCliente)); // Usando idCliente
                return true;
            } else {
                MenuFormatter.msgTerminalERROR("Cliente não encontrado no Banco de Dados.");
                return false;
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    // Excluir todos os Clientes
    public static boolean excluirTodosRegistros() {
        try {
            clienteCollection.deleteMany(new Document());
            return true;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    // Atualizar um Cliente específico pelo ID
    public static boolean atualizarRegistro(int idCliente, String nome, String cpf, String email) {
        try {
            if (existeRegistro(idCliente)) {
                Document updateDoc = new Document("$set", new Document("nome", nome)
                        .append("cpf", cpf)
                        .append("email", email));
                clienteCollection.updateOne(Filters.eq("idCliente", idCliente), updateDoc); // Usando idCliente
                return true;
            } else {
                MenuFormatter.msgTerminalERROR("Cliente não encontrado no Banco de Dados.");
                return false;
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    // Atualizar um objeto Cliente
    public static boolean atualizarRegistro(Cliente cliente) {
        return atualizarRegistro(cliente.getIdCliente(), cliente.getNome(), cliente.getCpf(), cliente.getEmail());
    }

    // Buscar um Cliente pelo ID
    public static Cliente buscarRegistroPorId(int idClientePesquisa) {
        try {
            Document result = clienteCollection.find(Filters.eq("idCliente", idClientePesquisa))
                    .first();

            if (result != null) {
                return new Cliente(result.getString("nome"),
                        result.getString("cpf"),
                        result.getString("email"),
                        result.getInteger("idCliente"));
            } else {
                MenuFormatter.msgTerminalERROR("Não encontrado nenhum cliente com o ID informado.");
                return null;
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    // Listar todos os Clientes
    public static LinkedList<Cliente> listarTodosRegistros() {
        LinkedList<Cliente> listaClientes = new LinkedList<>();
        try {
            MongoCursor<Document> cursor = clienteCollection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                listaClientes.add(new Cliente(doc.getString("nome"),
                        doc.getString("cpf"),
                        doc.getString("email"),
                        doc.getInteger("idCliente"))); // Usando idCliente
            }
            return listaClientes;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    // Contar o total de Clientes
    public static int contarRegistros() {
        try {
            return (int) clienteCollection.countDocuments();
        } catch (Exception e) {
            return -999;
        }
    }

    // Verificar se um Cliente específico existe
    public static boolean existeRegistro(int idCliente) {
        try {
            long count = clienteCollection.countDocuments(Filters.eq("idCliente", idCliente)); // Usando idCliente
            return count > 0;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }
}
