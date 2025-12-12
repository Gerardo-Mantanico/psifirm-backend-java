package com.pifirm.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "nominas")
@Data
public class NominaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "periodo", nullable = false)
    private LocalDate periodo;

    @Column(name = "salario_base", nullable = false)
    private BigDecimal salarioBase;

    @Column(name = "sesiones_trabajadas")
    private Integer sesionesTrabajadas = 0;

    @Column(name = "salario_bruto", nullable = false)
    private BigDecimal salarioBruto=  BigDecimal.ZERO;

    @Column(name = "salario_neto_adeudado", nullable = false)
    private BigDecimal salarioNetoAdeudado = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metodo_pago_id")
    private MetodoPagoEntity metodoPago;

    @Column(name = "detalle_pago")
    private String detallePago;

    @Column(name = "fecha_cierre")
    private LocalDate fechaCierre;

    @OneToMany(mappedBy = "nomina", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NominaBonoEntity> bonos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "nomina", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NominaRetencionEntity> retenciones = new LinkedHashSet<>();

    @OneToMany(mappedBy = "nomina", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NominaDescuentoEntity> descuentos = new LinkedHashSet<>();
}

