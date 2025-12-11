package com.pifirm.domain.dto.cita;

import java.time.LocalDateTime;

public record CitaDto(
        Long servicioMedicoId,
        LocalDateTime fechaCita,
        String nota
) {
}

