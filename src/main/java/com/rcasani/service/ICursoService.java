package com.rcasani.service;

import com.rcasani.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICursoService extends ICRUD<Curso, Integer> {

    List<Curso> cursoNombre(String nombre);
    List<Curso> cursoNombreLike(String nombre);
    List<Curso> findByNombreAndEstado(String nombre, boolean estado);
    Page<Curso> findPage(Pageable pageable);
}
