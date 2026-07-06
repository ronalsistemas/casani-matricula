package com.rcasani.repository;

import com.rcasani.model.Docente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDocenteRepo extends IGenericRepo<Docente, Integer> {

    @Query("FROM Docente d WHERE d.nombres LIKE %:nombre% AND d.especialidad = :especialidad")
    List<Docente> getNombresAndEspecialidad(@Param("nombre") String nombre, @Param("especialidad") String especialidad);

}
