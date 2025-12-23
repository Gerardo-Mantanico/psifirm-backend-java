package com.pifirm.persistence.entity.notasProgreso;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "evaluaciones_periodicas")
public class EvaluacionesPeriodicasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hc_id", nullable = false)
    private Long hcId;

    @Column(name = "fecha_evalucacion")
    private LocalDate fechaEvalucacion;

    @Column(name = "tipo_evaluacion", nullable = false)
    private Long tipoEvaluacion;

    @Column(name = "progreso_observado", nullable = false, columnDefinition = "TEXT")
    private String progresoObservado;

    @Column(name = "objetivo_alcanzado", nullable = false, columnDefinition = "TEXT")
    private String objetivoAlcanzado;

    @Column(name = "objetivos_pedientes", nullable = false, columnDefinition = "TEXT")
    private String objetivosPedientes;

    @Column(name = "recomendaciones", columnDefinition = "TEXT")
    private String recomendaciones;

    @Column(name = "escala_progreso", nullable = false)
    private Integer escalaProgreso;

    @Column(name = "modificacion_plan_tratamiento", nullable = false, columnDefinition = "TEXT")
    private String modificacionPlanTratamiento;

    @Column(name = "reevaluacion_diagnostico", nullable = false, columnDefinition = "TEXT")
    private String reevaluacionDiagnostico;
}

