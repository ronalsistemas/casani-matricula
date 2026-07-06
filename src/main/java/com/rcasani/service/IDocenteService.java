package com.rcasani.service;

import com.rcasani.model.Docente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDocenteService extends ICRUD<Docente, Integer> {

    List<Docente> getNombresAndEspecialidad(String nombre, String especialidad);

    Page<Docente> findPage(Pageable pageable);

    List<Docente> findAllOrder(String param);
}
