package com.pifirm.domain.dto.area;

import com.pifirm.domain.dto.servicio.ServicioResDto;
import java.time.Instant;

public record AreaResDto(
        Long id,
        String nombre,
        String descripcion,
        ServicioResDto servicio,
        Instant createdAt,
        Instant updatedAt

) {
}
