package com.pifirm.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipo_bono")
@Data
public class TipoBonoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", nullable = false, unique = true, length = 100)
    private String descripcion;
}
