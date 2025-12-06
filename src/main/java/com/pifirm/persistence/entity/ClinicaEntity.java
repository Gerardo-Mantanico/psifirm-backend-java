package com.pifirm.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Table(name = "clinica")
@Data
public class ClinicaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @NotNull
    private String nombre;

    @Size(max = 255)
    @NotNull
    @Email
    private String correo;

    @Size(max = 20)
    private String telefono;

    @Size(max = 100)
    private String direccion;

    @NotNull
    private LocalTime horasAtencion;

    @NotNull
    private LocalTime horaCierre;

    @Size(max = 50)
    @NotNull
    private String nit;

    @Size(max = 50)
    @NotNull
    private String registroSanitario;
  }

