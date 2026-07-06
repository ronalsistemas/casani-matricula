package com.rcasani.controller;

import com.rcasani.dto.MatriculaDTO;
import com.rcasani.dto.ProcedureDTO;
import com.rcasani.model.Matricula;
import com.rcasani.service.IMatriculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final IMatriculaService matriculaService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> findAll() throws Exception {

        List<MatriculaDTO> list = matriculaService.listar()
                .stream()
                .map(this::convertToDto)
                .toList();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDTO> findById(@PathVariable("id") Integer id) throws Exception {
        MatriculaDTO obj = convertToDto(matriculaService.buscar(id));

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<MatriculaDTO> save(@Valid @RequestBody MatriculaDTO dto) throws Exception {
        Matricula obj = matriculaService.guardar(convertToEntity(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(obj)) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody MatriculaDTO dto) throws Exception {
        //dto.setIdCategory(id);
        Matricula obj =  matriculaService.actualizar(id, convertToEntity(dto));

        return ResponseEntity.ok().body(convertToDto(obj)) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        matriculaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    // Query //

    //Matriculas por Estudiante
    @GetMapping("/byEstudiante")
    public ResponseEntity<List<MatriculaDTO>> findByEstudiante(@RequestParam("estudiante") String estudiante) throws Exception {
        List<MatriculaDTO> list = matriculaService.getMatriculaByEstudiante(estudiante).stream().map(this::convertToDto).toList();

        return ResponseEntity.ok().body(list);
    }

    //Estudiante que ha Pagado más por sus matrículas
    @GetMapping("/pagado/estudiante")
    public ResponseEntity<String> getPagadoMatricula() throws Exception {
        String estudiante = matriculaService.getPagadoEstudiante();

        return ResponseEntity.ok().body(estudiante) ;
    }

    //Cantidad de Matriculas por Estudiante
    @GetMapping("/cantidad/estudiante")
    public ResponseEntity<Map<String, Long>> getMatriculaEstudiante() throws Exception {
        return ResponseEntity.ok(matriculaService.getMatriculaEstudiante());
    }

    //El docente más solicitado
    @GetMapping("/docente/solicitado")
    public ResponseEntity<Map<String, Long>> getSolicitadoDocente() throws Exception{
        Map<String, Long> byDocente = matriculaService.getSolicitadoDocente();

        return ResponseEntity.ok(byDocente);
    }

    //Cantidad de ventas agrupadas por fecha (Se está usando una función creada en una BD)
    @GetMapping("/cantidad/fventas")
    public ResponseEntity<List<ProcedureDTO>> getVentasFecha() throws Exception {
        return ResponseEntity.ok(matriculaService.getVentasFecha());
    }

    //Actualizando el estado a PENDIENTE de las matrículas (Se está usando un procedimiento creada en una BD)
    @GetMapping("/estado/pendiente")
    public ResponseEntity<Void> estado() throws Exception {
        matriculaService.estadoProcedure();
        return ResponseEntity.ok().build();
    }

    private MatriculaDTO convertToDto(Matricula obj) {
        return modelMapper.map(obj, MatriculaDTO.class);
    }

    private Matricula convertToEntity(MatriculaDTO dto) {
        return modelMapper.map(dto, Matricula.class);
    }
}
