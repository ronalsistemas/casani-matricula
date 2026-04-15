package com.rcasani.controller;

import com.rcasani.dto.UsuarioDTO;
import com.rcasani.model.Usuario;
import com.rcasani.service.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final IUsuarioService usuarioService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() throws Exception {

        List<UsuarioDTO> list = usuarioService.listar()
                .stream()
                .map(this::convertToDto)
                .toList();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") Integer id) throws Exception {
        UsuarioDTO obj = convertToDto(usuarioService.buscar(id));

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioDTO dto) throws Exception {
        Usuario obj = usuarioService.guardar(convertToEntity(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(obj)) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody UsuarioDTO dto) throws Exception {
        //dto.setIdCategory(id);
        Usuario obj =  usuarioService.actualizar(id, convertToEntity(dto));

        return ResponseEntity.ok().body(convertToDto(obj)) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        usuarioService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    private UsuarioDTO convertToDto(Usuario obj) {
        return modelMapper.map(obj, UsuarioDTO.class);
    }

    private Usuario convertToEntity(UsuarioDTO dto) {
        return modelMapper.map(dto, Usuario.class);
    }
}
