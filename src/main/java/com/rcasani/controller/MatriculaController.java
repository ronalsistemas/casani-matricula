package com.rcasani.controller;

import com.rcasani.dto.MatriculaDTO;
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

    private MatriculaDTO convertToDto(Matricula obj) {
        return modelMapper.map(obj, MatriculaDTO.class);
    }

    private Matricula convertToEntity(MatriculaDTO dto) {
        return modelMapper.map(dto, Matricula.class);
    }
}
