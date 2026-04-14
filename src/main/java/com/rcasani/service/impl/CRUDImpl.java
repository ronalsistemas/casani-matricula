package com.rcasani.service.impl;

import com.rcasani.exception.ModelNotFoundException;
import com.rcasani.repository.IGenericRepo;
import com.rcasani.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public T guardar(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T actualizar(ID id, T t) throws Exception {
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return getRepo().save(t);
    }

    @Override
    public List<T> listar() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T buscar(ID id) throws Exception {
        return getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void eliminar(ID id) throws Exception {
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        getRepo().deleteById(id);
    }
}
