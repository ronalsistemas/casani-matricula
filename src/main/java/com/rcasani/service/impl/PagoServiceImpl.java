package com.rcasani.service.impl;

import com.rcasani.model.Pago;
import com.rcasani.repository.IGenericRepo;
import com.rcasani.repository.IPagoRepo;
import com.rcasani.service.IPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl extends CRUDImpl<Pago, Integer> implements IPagoService {

    private final IPagoRepo repo;

    @Override
    protected IGenericRepo<Pago, Integer> getRepo() {
        return repo;
    }

    @Override
    public Pago getPagoCostoso() {
        return repo.findAll()
                .stream()
                .max(Comparator.comparing(Pago::getMonto))
                .orElse(new Pago());
    }
}
