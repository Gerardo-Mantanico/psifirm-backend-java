package com.pifirm.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "nomina_descuentos")
@Data
public class NominaDescuentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomina_id", nullable = false)
    private NominaEntity nomina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_descuento_id", nullable = false)
    private TipoDescuentoEntity tipoDescuento;

    @Column(name = "monto", nullable = false)
    private BigDecimal monto;
}

