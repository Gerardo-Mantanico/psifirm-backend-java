package com.pifirm.persistence.entity.planintervencion;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "configuracion_tratamiento")
public class ConfiguracionTratamientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "objetivo_general", nullable = false)
    private TipoObjetivoEntity objetivoGeneral;

    @Column(name = "hc_id", nullable = false)
    private Long hcId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "frecuencia_id", nullable = false)
    private FrecuenciaEntity frecuencia;

    @Column(name = "sesiones_por_semana")
    @Min(1)
    @Max(3)
    private Integer sesionesPorSemana;

    @Column(name = "duracion_estimada")
    private Integer duracionEstimada;

    @Column(name = "costos_por_session", precision = 10, scale = 2)
    private BigDecimal costosPorSession;

}

