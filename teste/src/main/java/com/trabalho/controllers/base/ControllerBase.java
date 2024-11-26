package com.trabalho.controllers.base;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import com.trabalho.connection.*;
import com.trabalho.utils.*;

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
            if (this.contarRegistros() == 0) {
                return 0;

            } else {
                Document maiorIdDoc = this.colecao.find()
                        .sort(new Document("id_" + this.collectionName, -1))
                        .limit(1)
                        .first();
    
                if (maiorIdDoc != null) {
                    maiorId = maiorIdDoc.getInteger("id_" + this.collectionName);
                }                
    
                return maiorId;
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return maiorId = -999;
        }
    }

    // Verificar se um Registro específico existe, a partir do ID
    public boolean existeRegistro(int idRegistro) {
        try {
            long count = this.colecao.countDocuments(Filters.eq("id_"+collectionName, idRegistro));
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
    public boolean excluirRegistro(int idRegistro) {
        try {
            if (this.existeRegistro(idRegistro)) {
                this.colecao.deleteOne(Filters.eq("id_"+collectionName, idRegistro));
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

    // Getter e "Setter" de Coleção
    public void atualizarColecao() {
        this.colecao = DatabaseMongoDb.conectar().getCollection(this.collectionName);
    }
    public MongoCollection<Document> getColecao() {
        return this.colecao;
    }
}
