package com.rcasani.controller;

import com.rcasani.dto.DocenteDTO;
import com.rcasani.model.Docente;
import com.rcasani.service.IDocenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docentes")
@RequiredArgsConstructor
public class DocenteController {

    private final IDocenteService docenteService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<DocenteDTO>> findAll() throws Exception {

        List<DocenteDTO> list = docenteService.listar()
                .stream()
                .map(this::convertToDto)
                .toList();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteDTO> findById(@PathVariable("id") Integer id) throws Exception {
        DocenteDTO obj = convertToDto(docenteService.buscar(id));

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<DocenteDTO> save(@Valid @RequestBody DocenteDTO dto) throws Exception {
        Docente obj = docenteService.guardar(convertToEntity(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(obj)) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody DocenteDTO dto) throws Exception {
        //dto.setIdCategory(id);
        Docente obj =  docenteService.actualizar(id, convertToEntity(dto));

        return ResponseEntity.ok().body(convertToDto(obj)) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        docenteService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    //Querys//

    //Buscar Docente por nombre y especialidad
    @GetMapping("/encontrar/nombre/especialidad")
    public ResponseEntity<List<DocenteDTO>> findByNombreEsp(@RequestParam("nombre") String nombre, @RequestParam("especialidad") String especialidad) throws Exception {
        List<DocenteDTO> list = docenteService.getNombresAndEspecialidad(nombre, especialidad).stream().map(this::convertToDto).toList();

        return ResponseEntity.ok().body(list);
    }

    //Paginacion de docentes indicando la página y la cantidad de registros
    @GetMapping("/paginacion")
    public ResponseEntity<Page<DocenteDTO>> findPage(Pageable pageable) throws Exception {
        Page<DocenteDTO> page = docenteService.findPage(pageable).map(this::convertToDto);

        return ResponseEntity.ok().body(page);
    }

    //Ordenando la lista de docentes
    @GetMapping("/orden")
    public ResponseEntity<List<DocenteDTO>> findOrder(@RequestParam String param) throws Exception {
        return ResponseEntity.ok(docenteService.findAllOrder(param).stream().map(this::convertToDto).toList());
    }

    private DocenteDTO convertToDto(Docente obj) {
        return modelMapper.map(obj, DocenteDTO.class);
    }

    private Docente convertToEntity(DocenteDTO dto) {
        return modelMapper.map(dto, Docente.class);
    }
}
