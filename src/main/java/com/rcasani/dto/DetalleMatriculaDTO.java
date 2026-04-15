package com.rcasani.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetalleMatriculaDTO {

    //private Integer id;

    @JsonBackReference
    //@NotNull(message = "La matrícula es obligatoria")
    @JsonIncludeProperties(value = { "idMatricula" })
    private MatriculaDTO matricula;

    @NotNull(message = "El curso es obligatorio")
    @JsonIncludeProperties(value = { "idCurso", "nombre" })
    private CursoDTO curso;

    @NotNull(message = "El docente es obligatorio")
    @JsonIncludeProperties(value = { "idDocente", "nombres", "apellidos" })
    private DocenteDTO docente;

    @DecimalMin(value = "0.0", message = "La nota mínima es 0")
    @DecimalMax(value = "20.0", message = "La nota máxima es 20")
    private double notaFinal;
}
