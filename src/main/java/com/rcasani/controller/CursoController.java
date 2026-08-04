package com.rcasani.controller;

import com.rcasani.dto.CursoDTO;
import com.rcasani.dto.DocenteDTO;
import com.rcasani.model.Curso;
import com.rcasani.service.ICursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final ICursoService cursoService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @PreAuthorize("@authService.hasAccess()") //Evalua los roles para el controlador
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

    //Querys//

    //Buscar curso por nombre
    @GetMapping("/encontrar/curso/{nombre}")
    public ResponseEntity<List<CursoDTO>> findByName(@PathVariable("nombre") String nombre) throws Exception {
        List<CursoDTO> list = cursoService.cursoNombre(nombre).stream().map(this::convertToDto).toList();

        return ResponseEntity.ok().body(list);
    }

    //Buscar curso por nombre pero filtrando
    @GetMapping("/encontrar/curso/filtro/{nombre}")
    public ResponseEntity<List<CursoDTO>> findByNameLike(@PathVariable("nombre") String nombre) throws Exception {
        List<CursoDTO> list = cursoService.cursoNombreLike(nombre).stream().map(this::convertToDto).toList();

        return ResponseEntity.ok().body(list);
    }

    //Buscar curso por nombre y con el estado habilitado
    @GetMapping("/encontrar/curso/habilitado")
    public ResponseEntity<List<CursoDTO>> findByNameEnabled(@RequestParam("nombre") String nombre, @RequestParam("estado") boolean estado) throws Exception {
        List<CursoDTO> list = cursoService.findByNombreAndEstado(nombre, estado).stream().map(this::convertToDto).toList();

        return ResponseEntity.ok().body(list);
    }

    //Paginacion de cursos indicando la página y la cantidad de registros, pero colocando sus propias parámetros
    @GetMapping("/paginacion02")
    public ResponseEntity<Page<CursoDTO>> findPage2(
            @RequestParam(name = "p") int page,
            @RequestParam(name = "s") int size
    ) throws Exception {
        Page<CursoDTO> pageResult = cursoService.findPage(PageRequest.of(page, size)).map(this::convertToDto);

        return ResponseEntity.ok().body(pageResult);
    }

    private CursoDTO convertToDto(Curso obj) {
        return modelMapper.map(obj, CursoDTO.class);
    }

    private Curso convertToEntity(CursoDTO dto) {
        return modelMapper.map(dto, Curso.class);
    }
}
