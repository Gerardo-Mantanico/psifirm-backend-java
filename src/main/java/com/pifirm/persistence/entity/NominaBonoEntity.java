package com.pifirm.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "nomina_bonos", schema = "public")
@Data
public class NominaBonoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomina_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference("nomina-bonos")
    private NominaEntity nomina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_bono_id", referencedColumnName = "id", nullable = false)
    private TipoBonoEntity tipoBono;

    @Column(name = "monto", nullable = false)
    private BigDecimal monto;
}
