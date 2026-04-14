package com.rcasani.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RolDTO {

    private Integer idRol;

    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre del rol debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[A-Z_]+$", message = "El nombre debe estar en mayúsculas y sin espacios")
    private String nombre; // Ej: ADMIN, DOCENTE, ESTUDIANTE

    @Size(max = 150, message = "La descripción no debe exceder 150 caracteres")
    private String descripcion;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "ACTIVO|INACTIVO", message = "El estado debe ser ACTIVO o INACTIVO")
    private boolean estado;
}
