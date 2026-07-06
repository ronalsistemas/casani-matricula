package com.rcasani.service.impl;

import com.rcasani.dto.EstudianteDTO;
import com.rcasani.model.Estudiante;
import com.rcasani.repository.IEstudianteRepo;
import com.rcasani.repository.IGenericRepo;
import com.rcasani.service.IEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl extends CRUDImpl<Estudiante, Integer> implements IEstudianteService {

    private final IEstudianteRepo estudianteRepo;

    @Override
    protected IGenericRepo<Estudiante, Integer> getRepo() {
        return estudianteRepo;
    }

    @Override
    public Estudiante actualizar(Integer id, Estudiante estudiante) throws Exception {

        Estudiante existente = estudianteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("ID NOT FOUND: " + id));

        existente.setNombres(estudiante.getNombres());
        existente.setApellidos(estudiante.getApellidos());
        existente.setDni(estudiante.getDni());
        existente.setCorreo(estudiante.getCorreo());
        existente.setTelefono(estudiante.getTelefono());
        existente.setFechaNacimiento(estudiante.getFechaNacimiento());
        existente.setEstado(estudiante.isEstado());

        return estudianteRepo.save(existente);
    }

    @Override
    public List<EstudianteDTO> getNombresAndEstado(String nombre, String telefono) {
        return estudianteRepo.getNombresAndEstado(nombre, telefono);
    }

}
