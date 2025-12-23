package com.pifirm.domain.dto.hc;

import com.pifirm.domain.dto.servicio.ServicioResDto;

import java.time.LocalDate;

public record HcResDto(
        Long id,
        String nombreInstitucion,
        String servicioNombre,
        String code,
        LocalDate date,
        String psicologo,
        Boolean estado,
        Integer colegiadoPsicologo

) {
}
