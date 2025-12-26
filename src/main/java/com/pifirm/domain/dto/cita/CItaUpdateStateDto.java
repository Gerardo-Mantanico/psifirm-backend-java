package com.pifirm.domain.dto.cita;

import com.pifirm.domain.enums.EstadoCita;

public record CItaUpdateStateDto(
        EstadoCita estado
) {
}
