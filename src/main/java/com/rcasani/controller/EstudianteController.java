package com.rcasani.controller;

import com.rcasani.dto.EstudianteDTO;
import com.rcasani.model.Estudiante;
import com.rcasani.service.IEstudianteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final IEstudianteService estudianteService;
    @Qualifier("estudianteMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> findAll() throws Exception {

        List<EstudianteDTO> list = estudianteService.listar()
                .stream()
                .map(this::convertToDto)
                .toList();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> findById(@PathVariable("id") Integer id) throws Exception {
        EstudianteDTO obj = convertToDto(estudianteService.buscar(id));

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> save(@Valid @RequestBody EstudianteDTO dto) throws Exception {
        Estudiante obj = estudianteService.guardar(convertToEntity(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(obj)) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody EstudianteDTO dto) throws Exception {
        //dto.setIdCategory(id);
        Estudiante obj =  estudianteService.actualizar(id, convertToEntity(dto));

        return ResponseEntity.ok().body(convertToDto(obj)) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        estudianteService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    private EstudianteDTO convertToDto(Estudiante obj) {
        return modelMapper.map(obj, EstudianteDTO.class);
    }

    private Estudiante convertToEntity(EstudianteDTO dto) {
        return modelMapper.map(dto, Estudiante.class);
    }
}
