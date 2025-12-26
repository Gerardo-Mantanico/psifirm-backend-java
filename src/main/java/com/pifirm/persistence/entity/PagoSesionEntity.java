package com.pifirm.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "pago_sesion")
public class PagoSesionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id", nullable = false, columnDefinition = "text")
    @NotNull
    private String pacienteId;

    @Column(name = "session_id", nullable = false)
    @NotNull
    private Long sessionId;

    @Column(name = "monto", nullable = false, precision = 12, scale = 2)
    @NotNull
    @PositiveOrZero
    private BigDecimal monto;

    @Column(name = "pagado", nullable = false)
    private Boolean pagado = Boolean.TRUE;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDateTime fechaPago = LocalDateTime.now();

    @Column(name = "notas", columnDefinition = "text")
    private String notas;

    @Column(name = "comprobante", columnDefinition = "text")
    private String comprobante;

    @Column(name = "codigo", length = 50, unique = true)
    private String codigo;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
