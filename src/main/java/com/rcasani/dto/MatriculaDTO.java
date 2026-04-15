package com.rcasani.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatriculaDTO {

    private Integer idMatricula;

    @NotNull(message = "El estudiante es obligatorio")
    @JsonIncludeProperties(value = { "idEstudiante" })
    private EstudianteDTO estudiante;

    @NotNull(message = "El usuario es obligatorio")
    @JsonIncludeProperties(value = { "idUsuario", "username" })
    private UsuarioDTO usuario;

    @NotNull(message = "El periodo académico es obligatorio")
    //@JsonIncludeProperties(value = { "idPeriodo", "nombre" })
    private PeriodoAcademicoDTO periodo;

    @NotNull(message = "La fecha de matrícula es obligatoria")
    @PastOrPresent(message = "La fecha de matrícula no puede ser futura")
    private LocalDateTime fechaMatricula;

    @NotNull(message = "El monto total es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto total debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El monto total debe tener máximo 10 enteros y 2 decimales")
    private double montoTotal;

    @NotNull(message = "El saldo es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El saldo no puede ser negativo")
    @Digits(integer = 10, fraction = 2, message = "El saldo debe tener máximo 10 enteros y 2 decimales")
    private double saldo;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "PENDIENTE|PAGADO|ANULADO", message = "Estado inválido")
    private String estado;

    @NotEmpty(message = "Debe registrar al menos un curso")
    @JsonManagedReference
    private List<DetalleMatriculaDTO> detalles;
}
