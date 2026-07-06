package com.rcasani.service.impl;

import com.rcasani.model.Curso;
import com.rcasani.repository.ICursoRepo;
import com.rcasani.repository.IGenericRepo;
import com.rcasani.service.ICursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl extends CRUDImpl<Curso, Integer> implements ICursoService {

    private final ICursoRepo repo;

    @Override
    protected IGenericRepo<Curso, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Curso> cursoNombre(String nombre) {
        return repo.findByNombre(nombre);
    }

    @Override
    public List<Curso> cursoNombreLike(String nombre) {
        return repo.findByNombreLikeIgnoreCase("%" + nombre + "%");
    }

    @Override
    public List<Curso> findByNombreAndEstado(String nombre, boolean estado) {
        return repo.findByNombreAndEstado(nombre, estado);
    }

    @Override
    public Page<Curso> findPage(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
