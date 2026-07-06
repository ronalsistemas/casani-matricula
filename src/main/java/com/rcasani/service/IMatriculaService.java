package com.rcasani.service;

import com.rcasani.dto.ProcedureDTO;
import com.rcasani.model.Matricula;

import java.util.List;
import java.util.Map;

public interface IMatriculaService extends ICRUD<Matricula, Integer> {

    List<Matricula> getMatriculaByEstudiante(String nombreEstudiante);

    String getPagadoEstudiante();

    Map<String, Long> getMatriculaEstudiante();

    Map<String, Long> getSolicitadoDocente();

    List<ProcedureDTO> getVentasFecha();

    void estadoProcedure();
}
