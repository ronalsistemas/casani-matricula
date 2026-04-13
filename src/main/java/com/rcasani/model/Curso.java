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
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_curso")
    private Integer idCurso;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "creditos")
    private Integer creditos;

    @Column(name = "estado")
    private boolean estado; // 1 = activo, 0 = inactivo
}
