package com.rcasani.service.impl;

import com.rcasani.model.Curso;
import com.rcasani.repository.ICursoRepo;
import com.rcasani.repository.IGenericRepo;
import com.rcasani.service.ICursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl extends CRUDImpl<Curso, Integer> implements ICursoService {

    private final ICursoRepo repo;

    @Override
    protected IGenericRepo<Curso, Integer> getRepo() {
        return repo;
    }
}
