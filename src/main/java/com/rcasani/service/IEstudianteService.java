package com.rcasani.service;

import com.rcasani.dto.EstudianteDTO;
import com.rcasani.model.Estudiante;

import java.util.List;

public interface IEstudianteService extends ICRUD<Estudiante, Integer> {

    List<EstudianteDTO> getNombresAndEstado(String nombre, String telefono);
}
