package com.rcasani.service;

import java.util.List;

public interface ICRUD<T, ID> {
    T guardar(T t) throws Exception;
    T actualizar(ID id, T t) throws Exception;
    List<T> listar() throws Exception;
    T buscar(ID id) throws Exception;
    void eliminar(ID id) throws Exception;
}
