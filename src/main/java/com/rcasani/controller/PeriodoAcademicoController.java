package com.rcasani.controller;

import com.rcasani.dto.PeriodoAcademicoDTO;
import com.rcasani.model.PeriodoAcademico;
import com.rcasani.service.IPeriodoAcademicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/periodo")
@RequiredArgsConstructor
public class PeriodoAcademicoController {

    private final IPeriodoAcademicoService periodoAcademicoService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PeriodoAcademicoDTO>> findAll() throws Exception {

        List<PeriodoAcademicoDTO> list = periodoAcademicoService.listar()
                .stream()
                .map(this::convertToDto)
                .toList();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeriodoAcademicoDTO> findById(@PathVariable("id") Integer id) throws Exception {
        PeriodoAcademicoDTO obj = convertToDto(periodoAcademicoService.buscar(id));

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<PeriodoAcademicoDTO> save(@Valid @RequestBody PeriodoAcademicoDTO dto) throws Exception {
        PeriodoAcademico obj = periodoAcademicoService.guardar(convertToEntity(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(obj)) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeriodoAcademicoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody PeriodoAcademicoDTO dto) throws Exception {
        //dto.setIdCategory(id);
        PeriodoAcademico obj =  periodoAcademicoService.actualizar(id, convertToEntity(dto));

        return ResponseEntity.ok().body(convertToDto(obj)) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        periodoAcademicoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    private PeriodoAcademicoDTO convertToDto(PeriodoAcademico obj) {
        return modelMapper.map(obj, PeriodoAcademicoDTO.class);
    }

    private PeriodoAcademico convertToEntity(PeriodoAcademicoDTO dto) {
        return modelMapper.map(dto, PeriodoAcademico.class);
    }
}
