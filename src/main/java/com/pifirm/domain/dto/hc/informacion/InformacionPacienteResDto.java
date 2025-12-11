package com.pifirm.domain.dto.hc.informacion;

public record InformacionPacienteResDto(
        Long id,
        Integer edad,
        String nombreCompleto,
        String genero,
        String estadoCivil,
        String ocupacion,
        String nivelEducativo,
        String direccion,
        String telefono,
        String emial,
        String personaContacto,
        String parentescoContacto,
        String telefonoContacto,
        String procedencia,
        String motivoConsulta
) {
}
