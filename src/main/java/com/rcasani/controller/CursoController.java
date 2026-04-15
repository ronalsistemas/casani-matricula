package com.rcasani.controller;

import com.rcasani.dto.CursoDTO;
import com.rcasani.model.Curso;
import com.rcasani.service.ICursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final ICursoService cursoService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> findAll() throws Exception {

        List<CursoDTO> list = cursoService.listar()
                .stream()
                .map(this::convertToDto)
                .toList();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> findById(@PathVariable("id") Integer id) throws Exception {
        CursoDTO obj = convertToDto(cursoService.buscar(id));

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<CursoDTO> save(@Valid @RequestBody CursoDTO dto) throws Exception {
        Curso obj = cursoService.guardar(convertToEntity(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(obj)) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody CursoDTO dto) throws Exception {
        //dto.setIdCategory(id);
        Curso obj =  cursoService.actualizar(id, convertToEntity(dto));

        return ResponseEntity.ok().body(convertToDto(obj)) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        cursoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    private CursoDTO convertToDto(Curso obj) {
        return modelMapper.map(obj, CursoDTO.class);
    }

    private Curso convertToEntity(CursoDTO dto) {
        return modelMapper.map(dto, Curso.class);
    }
}
