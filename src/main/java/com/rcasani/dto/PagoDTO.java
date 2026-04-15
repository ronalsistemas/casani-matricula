package com.rcasani.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagoDTO {

    private Integer idPago;

    @NotNull(message = "La matrícula es obligatoria")
    private Integer idMatricula;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El monto debe tener máximo 10 enteros y 2 decimales")
    private double monto;

    @NotNull(message = "La fecha de pago es obligatoria")
    private LocalDateTime fechaPago;

    @NotBlank(message = "El método de pago es obligatorio")
    @Pattern(
            regexp = "EFECTIVO|TARJETA|TRANSFERENCIA|YAPE|PLIN",
            message = "Método de pago inválido"
    )
    private String metodoPago;

    @Size(max = 100, message = "La referencia no debe exceder 100 caracteres")
    private String nroOperacion;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(
            regexp = "COMPLETADO|PENDIENTE|ANULADO",
            message = "Estado inválido"
    )
    private String estado;
}
