package com.pifirm.domain.dto.cita;

import com.pifirm.domain.dto.servicio.ServicioResDto;
import com.pifirm.domain.dto.user.UserSimpleDto;
import com.pifirm.domain.enums.EstadoCita;

import java.time.LocalDateTime;

public record CitaResDto(
        Long id,
        Long historiaClinicaId,
        UserSimpleDto paciente,
        UserSimpleDto medico,
        ServicioResDto servicioMedico,
        LocalDateTime fechaCita,
        EstadoCita estadoCita,
        String nota,
        LocalDateTime createdAt
) {
}

