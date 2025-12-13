package com.pifirm.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "nomina_retenciones")
@Data
public class NominaRetencionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomina_id", nullable = false)
    @JsonBackReference("nomina-retenciones")
    private NominaEntity nomina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_retencion_id", nullable = false)
    private TipoRetencionEntity tipoRetencion;

    @Column(name = "monto", nullable = false)
    private BigDecimal monto;
}
