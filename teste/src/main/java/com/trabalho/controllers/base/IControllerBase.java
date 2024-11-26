package com.trabalho.controllers.base;

import java.util.LinkedList;

public interface IControllerBase<T> {
    // Inserir um novo Registro
    public boolean inserirRegistro(T objeto);

    // Atualizar um Registro
    public boolean atualizarRegistro(T objeto);

    // Buscar um Registro pelo ID
    public T buscarRegistroPorId(int idObjetoPesquisa);

    // Listar todos os Registros
    public LinkedList<T> listarTodosRegistros();
}
