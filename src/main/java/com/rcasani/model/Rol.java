package com.rcasani.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Rol {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "nombre", length = 50, unique = true, nullable = false)
    private String nombre; // ADMIN, DOCENTE, ESTUDIANTE

    @Column(name = "descripcion", length = 150)
    private String descripcion;

    @Column(name = "estado")
    private boolean estado; // 1 = activo, 0 = inactivo
}
