package com.pifirm.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "informacion_paciente")
@Data
public class InformacionPacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hc_id")
    private Long hcId;

    @NotNull
    @Column(name = "edad", nullable = false)
    private Integer edad;

    @NotNull
    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @NotNull
    @Column(name = "genero", nullable = false)
    private String genero;

    @NotNull
    @Column(name = "estado_civil", nullable = false)
    private String estadoCivil;

    @NotNull
    @Column(name = "ocupacion", nullable = false)
    private String ocupacion;

    @NotNull
    @Column(name = "nivel_educativo", nullable = false)
    private String nivelEducativo;

    @NotNull
    @Column(name = "direccion", nullable = false, columnDefinition = "TEXT")
    private String direccion;

    @NotNull
    @Column(name = "telefono", nullable = false)
    private String telefono;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "persona_contacto", nullable = false)
    private String personaContacto;

    @NotNull
    @Column(name = "parentesco_contacto", nullable = false)
    private String parentescoContacto;

    @NotNull
    @Column(name = "telefono_contacto", nullable = false)
    private String telefonoContacto;

    @NotNull
    @Column(name = "procedencia", nullable = false)
    private String procedencia;

    @NotNull
    @Column(name = "motivo_consulta", nullable = false, columnDefinition = "TEXT")
    private String motivoConsulta;
}

