// java
package com.pifirm.persistence.entity.planintervencion;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "configuracion_tratamiento")
public class ConfiguracionTratamientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hc_id", nullable = false)
    private Long hcId;

    @Column(name = "objetivo_cortoplazo", nullable = false)
    private String objetivoCortoplazo;

    @Column(name = "objetivo_medioplazo", nullable = false)
    private String objetivoMedioplazo;

    @Column(name = "objetivo_largoplazo", nullable = false)
    private String objetivoLargoplazo;

    @Column(name = "frecuencia_id", nullable = false)
    private Long frecuenciaId;

    @Column(name = "sesiones_por_semana")
    @Min(1)
    @Max(3)
    private Integer sesionesPorSemana;

    @Column(name = "duracion_estimada", precision = 10, scale = 2)
    private BigDecimal duracionEstimada;

    @Column(name = "costos_por_session", precision = 10, scale = 2)
    private BigDecimal costosPorSession;
}