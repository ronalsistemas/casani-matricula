package com.rcasani.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleMatricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_matricula", nullable = false, foreignKey = @ForeignKey(name = "fk_detalle_matricula"))
    private Matricula matricula;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false, foreignKey = @ForeignKey(name = "fk_detalle_curso"))
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_docente", nullable = false, foreignKey = @ForeignKey(name = "fk_detalle_docente"))
    private Docente docente;

    @Column(name = "nota_final")
    private double notaFinal;
}
