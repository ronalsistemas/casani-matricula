package com.rcasani.controller;

import com.rcasani.dto.RolDTO;
import com.rcasani.model.Rol;
import com.rcasani.service.IRolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolController {

    private final IRolService rolService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<RolDTO>> findAll() throws Exception {

        List<RolDTO> list = rolService.listar()
                .stream()
                .map(this::convertToDto)
                .toList();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> findById(@PathVariable("id") Integer id) throws Exception {
        RolDTO obj = convertToDto(rolService.buscar(id));

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<RolDTO> save(@Valid @RequestBody RolDTO dto) throws Exception {
        Rol obj = rolService.guardar(convertToEntity(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(obj)) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody RolDTO dto) throws Exception {
        //dto.setIdCategory(id);
        Rol obj =  rolService.actualizar(id, convertToEntity(dto));

        return ResponseEntity.ok().body(convertToDto(obj)) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        rolService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    private RolDTO convertToDto(Rol obj) {
        return modelMapper.map(obj, RolDTO.class);
    }

    private Rol convertToEntity(RolDTO dto) {
        return modelMapper.map(dto, Rol.class);
    }
}
