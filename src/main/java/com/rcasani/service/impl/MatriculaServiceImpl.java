package com.rcasani.service.impl;

import com.rcasani.model.Matricula;
import com.rcasani.repository.IGenericRepo;
import com.rcasani.repository.IMatriculaRepo;
import com.rcasani.service.IMatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl extends CRUDImpl<Matricula, Integer> implements IMatriculaService {

    private final IMatriculaRepo repo;

    @Override
    protected IGenericRepo<Matricula, Integer> getRepo() {
        return repo;
    }
}
