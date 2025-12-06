package com.pifirm.domain.dto.clinica;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ClinicaDto {
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;
    private LocalTime horasAtencion;
    private LocalTime horaCierre;
    private String nit;
    private String registroSanitario;

 }

