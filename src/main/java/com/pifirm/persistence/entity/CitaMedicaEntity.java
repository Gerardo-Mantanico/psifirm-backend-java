package com.pifirm.persistence.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "cita_medica")
public class CitaMedicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hc_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private HistoriaClinicaEntity historiaClinica;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity medico;

    @Column(name = "fecha_cita", nullable = false)
    private LocalDateTime fechaCita;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_cita", length = 20, nullable = false)
    private EstadoCita estadoCita = EstadoCita.PROGRAMADA;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "servicio_medico_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ServicioEntity servicioMedico;

    @Column(name = "nota", columnDefinition = "TEXT")
    private String nota;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HistoriaClinicaEntity getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinicaEntity historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public UserEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(UserEntity paciente) {
        this.paciente = paciente;
    }

    public UserEntity getMedico() {
        return medico;
    }

    public void setMedico(UserEntity medico) {
        this.medico = medico;
    }

    public LocalDateTime getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }

    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }

    public ServicioEntity getServicioMedico() {
        return servicioMedico;
    }

    public void setServicioMedico(ServicioEntity servicioMedico) {
        this.servicioMedico = servicioMedico;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

