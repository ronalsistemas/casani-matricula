package com.rcasani.service.impl;

import com.rcasani.model.Docente;
import com.rcasani.repository.IDocenteRepo;
import com.rcasani.repository.IGenericRepo;
import com.rcasani.service.IDocenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocenteServiceImpl extends CRUDImpl<Docente, Integer> implements IDocenteService {

    private final IDocenteRepo repo;

    @Override
    protected IGenericRepo<Docente, Integer> getRepo() {
        return repo;
    }
}
