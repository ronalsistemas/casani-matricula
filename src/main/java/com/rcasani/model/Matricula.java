package com.rcasani.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_matricula")
    private Integer idMatricula;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false, foreignKey = @ForeignKey(name = "fk_matricula_estudiante"))
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "fk_matricula_usuario"))
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_periodo", nullable = false, foreignKey = @ForeignKey(name = "fk_matricula_periodo"))
    private PeriodoAcademico periodoAcademico;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "estado", length = 20)
    private String estado; // PENDIENTE, COMPLETADO, ANULADO

    @Column(name = "monto_total")
    private double montoTotal;

    @Column(name = "saldo")
    private double saldo;

    // 🔗 Relación con DetalleMatricula
    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL)
    private List<DetalleMatricula> detalles;

}
