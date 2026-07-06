package com.rcasani.controller;

import com.rcasani.dto.PagoDTO;
import com.rcasani.model.Pago;
import com.rcasani.service.IPagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final IPagoService pagoService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PagoDTO>> findAll() throws Exception {

        List<PagoDTO> list = pagoService.listar()
                .stream()
                .map(this::convertToDto)
                .toList();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> findById(@PathVariable("id") Integer id) throws Exception {
        PagoDTO obj = convertToDto(pagoService.buscar(id));

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<PagoDTO> save(@Valid @RequestBody PagoDTO dto) throws Exception {
        Pago obj = pagoService.guardar(convertToEntity(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(obj)) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody PagoDTO dto) throws Exception {
        //dto.setIdCategory(id);
        Pago obj =  pagoService.actualizar(id, convertToEntity(dto));

        return ResponseEntity.ok().body(convertToDto(obj)) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        pagoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    /// queries //////////////////////

    // Pago mas caro
    @GetMapping("/costoso")
    public ResponseEntity<PagoDTO> findCostoso() throws Exception {
        Pago obj = pagoService.getPagoCostoso();

        return ResponseEntity.ok().body(convertToDto(obj)) ;
    }

    private PagoDTO convertToDto(Pago obj) {
        return modelMapper.map(obj, PagoDTO.class);
    }

    private Pago convertToEntity(PagoDTO dto) {
        return modelMapper.map(dto, Pago.class);
    }
}
