package com.pifirm.domain.dto.especialidad;

import java.time.Instant;

public record EspecialidadResDto(
        Long id,
        String nombre,
        String descripcion,
        Instant createdAt,
        Instant updatedAt

) {
}
