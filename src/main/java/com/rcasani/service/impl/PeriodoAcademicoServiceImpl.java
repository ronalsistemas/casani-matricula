package com.rcasani.service.impl;

import com.rcasani.model.PeriodoAcademico;
import com.rcasani.repository.IGenericRepo;
import com.rcasani.repository.IPeriodoAcademicoRepo;
import com.rcasani.service.IPeriodoAcademicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PeriodoAcademicoServiceImpl extends CRUDImpl<PeriodoAcademico, Integer> implements IPeriodoAcademicoService {

    private final IPeriodoAcademicoRepo repo;

    @Override
    protected IGenericRepo<PeriodoAcademico, Integer> getRepo() {
        return repo;
    }
}
