package com.trabalho.controllers.base;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.trabalho.connection.DatabaseMongoDb;
import com.trabalho.utils.MenuFormatter;

public abstract class ControllerBase {
    private String collectionName = "";
    private MongoCollection<Document> colecao = null;

    public ControllerBase(String collectionName) {
        this.collectionName = collectionName; 
        this.colecao = DatabaseMongoDb.conectar().getCollection(collectionName);
    }

    // Contar o total de Registros
    public int contarRegistros() {
        try {
            return (int) this.colecao.countDocuments();
        } catch (Exception e) {
            return -999;
        }
    }

    // Retorna o maio ID da Coleção
    public int getMaiorId() {
        int maiorId = -500;

        try  {
            Document maiorIdDoc = this.colecao.find()
                    .sort(new Document("id" + capPrimeiraLetra(this.collectionName), -1))
                    .limit(1)
                    .first();

            if (maiorIdDoc != null) {
                maiorId = maiorIdDoc.getInteger("id" + capPrimeiraLetra(this.collectionName));
            }                

            return maiorId;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return maiorId = -999;
        }
    }

    // Verificar se um Registro específico existe, a partir do ID
    public boolean existeRegistro(int idObjeto) {
        try {
            long count = this.colecao.countDocuments(Filters.eq("_id", new ObjectId(String.valueOf("id" + capPrimeiraLetra(this.collectionName)))));
            return count > 0;

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    // Excluir todos os Registros
    public boolean excluirTodosRegistros() {
        try {
            this.colecao.deleteMany(new Document());
            return true;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    // Excluir um Registro específico pelo ID
    public boolean excluirRegistro(int idObjeto) {
        try {
            if (this.existeRegistro(idObjeto)) {
                this.colecao.deleteOne(Filters.eq("_id", new ObjectId(String.valueOf("id" + capPrimeiraLetra(collectionName)))));
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

    /*
     * Capitaliza a Primeira letra da palavra, utilizado para o nome dos IDs das Classes Models, a partir das coleções. 
     * Exemplo: 
     * - Coleção = endereco
     * - Id Classe Model = idEndreco
     */ 
    private static String capPrimeiraLetra(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // Getter e "Setter" de Coleção
    public void atualizarColecao() {
        this.colecao = DatabaseMongoDb.conectar().getCollection(this.collectionName);
    }
    public MongoCollection<Document> getCollection() {
        return this.colecao;
    }
}
