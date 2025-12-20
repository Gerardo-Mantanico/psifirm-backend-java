package com.pifirm.persistence.entity.notasProgreso;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "alta_terapeutica")
public class AltaTerapeuticaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hc_id", nullable = false)
    private Long hcId;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDateTime fechaAlta;

    @ManyToOne
    @JoinColumn(name = "motivo_alta_id", nullable = false)
    private MotivoAltaEntity motivoAlta;

    @Column(name = "estado_alta", nullable = false, columnDefinition = "TEXT")
    private String estadoAlta;

    @Column(name = "recomendaciones_posteriores", nullable = false, columnDefinition = "TEXT")
    private String recomendacionesPosteriores;

    @Column(name = "seguimiento_programada")
    private Boolean seguimientoProgramada;

    @Column(name = "fecha_seguimiento", nullable = false)
    private LocalDate fechaSeguimiento;

    @Column(name = "firma_paciente", nullable = false, columnDefinition = "TEXT")
    private String firmaPaciente;

    @Column(name = "firma_psicologo", nullable = false, columnDefinition = "TEXT")
    private String firmaPsicologo;
}

