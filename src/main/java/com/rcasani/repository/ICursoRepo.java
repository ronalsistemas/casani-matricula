package com.rcasani.repository;

import com.rcasani.model.Curso;

import java.util.List;

public interface ICursoRepo extends IGenericRepo<Curso, Integer> {

    List<Curso> findByNombre(String nombre);

    List<Curso> findByNombreLikeIgnoreCase(String nombre);

    List<Curso> findByNombreAndEstado(String nombre, boolean estado);
}
