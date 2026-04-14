package com.rcasani.service.impl;

import com.rcasani.model.Usuario;
import com.rcasani.repository.IGenericRepo;
import com.rcasani.repository.IUsuarioRepo;
import com.rcasani.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl extends CRUDImpl<Usuario, Integer> implements IUsuarioService {

    private final IUsuarioRepo repo;

    @Override
    protected IGenericRepo<Usuario, Integer> getRepo() {
        return repo;
    }
}
