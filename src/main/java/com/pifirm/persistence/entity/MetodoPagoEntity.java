package com.pifirm.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "metodo_pago")
@Data
public class MetodoPagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", nullable = false, unique = true, length = 100)
    private String descripcion;
}
