package com.pifirm.domain.dto.servicio;

import java.math.BigDecimal;
import java.time.Instant;

public record ServicioResDto(
        Long id,
        String nombre,
        String descripcion,
        BigDecimal precio,
        Instant createdAt,
        Instant updatedAt

) {
}
