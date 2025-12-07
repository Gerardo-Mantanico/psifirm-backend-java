package com.pifirm.domain.dto.area;

import java.math.BigDecimal;
import java.time.Instant;

public record AreaResDto(
        Long id,
        String nombre,
        String descripcion,
        Instant createdAt,
        Instant updatedAt

) {
}
