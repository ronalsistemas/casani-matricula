package com.rcasani.service.impl;

import com.rcasani.model.Rol;
import com.rcasani.repository.IGenericRepo;
import com.rcasani.repository.IRolRepo;
import com.rcasani.service.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolServiceImpl extends CRUDImpl<Rol, Integer> implements IRolService {

    private final IRolRepo repo;

    @Override
    protected IGenericRepo<Rol, Integer> getRepo() {
        return repo;
    }
}
