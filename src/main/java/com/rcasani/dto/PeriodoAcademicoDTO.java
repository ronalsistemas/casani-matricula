package com.rcasani.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PeriodoAcademicoDTO {

    private Integer idPeriodo;

    @NotBlank(message = "El nombre del periodo es obligatorio")
    @Size(min = 5, max = 50, message = "El nombre debe tener entre 5 y 50 caracteres")
    private String nombre; // Ej: 2026-I

    @NotNull(message = "La fecha de inicio es obligatoria")
    private Date fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private Date fechaFin;

}
