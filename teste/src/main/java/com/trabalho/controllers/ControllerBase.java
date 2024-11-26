package com.trabalho.controllers;

import java.util.LinkedList;

public interface ControllerBase<T> {
    // Inserir um novo Registro
    public boolean inserirRegistro(T objeto);

    // Excluir um Registro específico pelo ID
    public boolean excluirRegistro(int idObjeto);

    // Excluir todos os Registros
    public boolean excluirTodosRegistros();

    // Atualizar um Registro específico pelo ID
    // public boolean atualizarRegistro(int idEndereco, int numero, String rua, String bairro, String cidade, String uf);

    // Atualizar um Registro
    public boolean atualizarRegistro(T objeto);

    // Buscar um Registro pelo ID
    public T buscarRegistroPorId(int idObjetoPesquisa);

    // Listar todos os Registros
    public LinkedList<T> listarTodosRegistros();

    // Contar o total de Registros
    public int contarRegistros();

    // Verificar se um Registro específico existe
    public boolean existeRegistro(int idObjeto);
}
