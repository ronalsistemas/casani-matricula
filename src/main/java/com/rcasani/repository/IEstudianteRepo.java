package com.rcasani.repository;

import com.rcasani.dto.EstudianteDTO;
import com.rcasani.model.Estudiante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEstudianteRepo extends IGenericRepo<Estudiante, Integer> {
    /*
    @Query("SELECT new com.rcasani.model.Estudiante(e.nombres, e.estado) FROM Estudiante e WHERE e.nombres LIKE %:nombre% AND e.telefono = :telefono")
    List<Estudiante> getNombresAndTelefono(@Param("nombre") String nombre, @Param("telefono") String telefono);
     */
    @Query("""
       SELECT new com.rcasani.dto.EstudianteDTO(e.nombres, e.estado)
       FROM Estudiante e
       WHERE e.nombres LIKE %:nombre%
       AND e.telefono = :telefono
       """)
    List<EstudianteDTO> getNombresAndEstado(
            @Param("nombre") String nombre,
            @Param("telefono") String telefono);
}
