package com.rcasani.repository;

import com.rcasani.dto.ProcedureDTO;
import com.rcasani.model.Matricula;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface IMatriculaRepo extends IGenericRepo<Matricula, Integer> {

    @Query("FROM Matricula m WHERE m.estudiante.nombres = :nombreEstudiante")
    List<Matricula> getMatriculaByEstudiante(String nombreEstudiante);

    @Query(value = "select * from fn_matriculas()", nativeQuery = true)
    List<ProcedureDTO> getVentasFecha();

    @Procedure(procedureName = "pr_matricula")
    void estadoProcedure();
}
