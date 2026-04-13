package com.rcasani.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_pago")
    private Integer idPago;

    @ManyToOne
    @JoinColumn(name = "id_matricula", nullable = false, foreignKey = @ForeignKey(name = "fk_pago_matricula"))
    private Matricula matricula;

    @Column(name = "monto", nullable = false)
    private double monto;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    @Column(name = "metodo_pago", length = 50)
    private String metodoPago; // EFECTIVO, TARJETA, YAPE, PLIN

    @Column(name = "nro_operacion", length = 100)
    private String nroOperacion;

    @Column(name = "estado", length = 20)
    private String estado; // PAGADO, PENDIENTE, ANULADO
}
