package com.rcasani.service.impl;

import com.rcasani.dto.ProcedureDTO;
import com.rcasani.model.DetalleMatricula;
import com.rcasani.model.Matricula;
import com.rcasani.repository.IGenericRepo;
import com.rcasani.repository.IMatriculaRepo;
import com.rcasani.service.IMatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl extends CRUDImpl<Matricula, Integer> implements IMatriculaService {

    private final IMatriculaRepo repo;

    @Override
    protected IGenericRepo<Matricula, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Matricula> getMatriculaByEstudiante(String nombreEstudiante) {
        return repo.getMatriculaByEstudiante(nombreEstudiante);
    }

    @Override
    public String getPagadoEstudiante() {
        Map<String, Double> byEstudiante = repo.findAll()
                .stream()
                .collect(groupingBy(m -> m.getEstudiante().getNombres(), summingDouble(Matricula::getMontoTotal)));

        System.out.println(byEstudiante);

        return Collections.max(byEstudiante.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
    }

    @Override
    public Map<String, Long> getMatriculaEstudiante() {
        return repo.findAll()
                .stream()
                .collect(groupingBy(s -> s.getEstudiante().getNombres(), counting()));
    }

    @Override
    public Map<String, Long> getSolicitadoDocente() {
        Stream<Matricula> matriculaStream = repo.findAll().stream();
        Stream<List<DetalleMatricula>> lsStream = matriculaStream.map(Matricula::getDetalles);

        Stream<DetalleMatricula> streamDetalle = lsStream.flatMap(Collection::stream);
        Map<String, Long> byDocente = streamDetalle
                .collect(groupingBy(d -> d.getDocente().getNombres(), counting()));

        return byDocente.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new
                ));
    }

    @Override
    public List<ProcedureDTO> getVentasFecha() {
        return repo.getVentasFecha();
    }

    @Override
    public void estadoProcedure() {
        repo.estadoProcedure();
    }
}
