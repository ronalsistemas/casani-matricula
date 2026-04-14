package com.rcasani.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CursoDTO {

    private Integer idCurso;

    @NotBlank(message = "El nombre del curso es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre del curso debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotNull(message = "Los créditos son obligatorios")
    @Min(value = 1, message = "Los créditos deben ser como mínimo 1")
    @Max(value = 10, message = "Los créditos no pueden ser mayores a 10")
    private Integer creditos;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "ACTIVO|INACTIVO", message = "El estado debe ser ACTIVO o INACTIVO")
    private boolean estado;
}
