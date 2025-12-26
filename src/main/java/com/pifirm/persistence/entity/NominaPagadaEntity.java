package com.pifirm.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "nomina_pagada")
@Data
public class NominaPagadaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomina_id", nullable = false)
    private Long nominaId;

    @Column(name = "user_id", nullable = true)
    private Long userId;

    @Column(name = "codigo_factura")
    private String codigoFactura;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "data_json", columnDefinition = "jsonb")
    private String dataJson;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDateTime fechaPago;

    @Column(name = "pagado", nullable = false)
    private Boolean pagado = true;

    @Column(name = "created_at", insertable = false)
    private LocalDateTime createdAt;

}