package com.pifirm.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "historia_clinica")
@Data
public class HistoriaClinicaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre_institucion", nullable = false)
    private String nombreInstitucion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servicio_id", nullable = false)
    private ServicioEntity servicio;

    @Column(name = "servicio", nullable = false)
    private String servicioNombre;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "date")
    private LocalDate date;

    // En el SQL original no hay FK explícita a user, por simplicidad guardamos el id del psicólogo y su nombre
    @Column(name = "psicologo_id", nullable = false)
    private Long psicologoId;

    @Column(name = "psicologo", nullable = false)
    private String psicologo;

    @Column(name = "colegiado_psicologo")
    private Integer colegiadoPsicologo;

    @Column(name = "estado")
    private Boolean estado = Boolean.TRUE;
}

