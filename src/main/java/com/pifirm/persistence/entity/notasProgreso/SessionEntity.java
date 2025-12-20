package com.pifirm.persistence.entity.notasProgreso;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "session")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hc_id", nullable = false)
    private Long hcId;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "numero_sesiones", nullable = false)
    private Integer numeroSesiones;

    @Column(name = "asistencia", nullable = false)
    private boolean asistencia;

    @Column(name = "justificacion_inasistencia", columnDefinition = "TEXT")
    private String justificacionInasistencia;

    @Column(name = "temas_abordados", nullable = false, columnDefinition = "TEXT")
    private String temasAbordados;

    @Column(name = "intervenciones_realizadas", nullable = false, columnDefinition = "TEXT")
    private String intervencionesRealizadas;

    @Column(name = "repuesta_paciente", nullable = false, columnDefinition = "TEXT")
    private String repuestaPaciente;

    @Column(name = "tareas_asignadas", nullable = false, columnDefinition = "TEXT")
    private String tareasAsignadas;

    @Column(name = "Observaciones", nullable = false, columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "proxima_cita", nullable = false)
    private LocalDateTime proximaCita;

    @Column(name = "firma_psicologo", nullable = false, columnDefinition = "TEXT")
    private String firmaPsicologo;
}



