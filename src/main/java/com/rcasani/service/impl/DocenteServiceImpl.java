package com.rcasani.service.impl;

import com.rcasani.model.Docente;
import com.rcasani.repository.IDocenteRepo;
import com.rcasani.repository.IGenericRepo;
import com.rcasani.service.IDocenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocenteServiceImpl extends CRUDImpl<Docente, Integer> implements IDocenteService {

    private final IDocenteRepo repo;

    @Override
    protected IGenericRepo<Docente, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Docente> getNombresAndEspecialidad(String nombre, String especialidad) {
        return repo.getNombresAndEspecialidad(nombre, especialidad);
    }

    @Override
    public Page<Docente> findPage(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public List<Docente> findAllOrder(String param) {
        Sort.Direction direction = param.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return repo.findAll(Sort.by(direction, "nombres"));
    }
}
