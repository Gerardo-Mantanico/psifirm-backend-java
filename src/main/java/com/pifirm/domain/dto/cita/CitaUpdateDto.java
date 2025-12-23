package com.pifirm.domain.dto.cita;

import com.pifirm.domain.enums.EstadoCita;

import java.time.LocalDateTime;

public record  CitaUpdateDto (
        Long servicioMedicoId,
        LocalDateTime fechaCita,
        String nota,
        EstadoCita estado
) {

}
