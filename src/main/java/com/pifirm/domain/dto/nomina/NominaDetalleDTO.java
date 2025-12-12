package com.pifirm.domain.dto.nomina;

import java.math.BigDecimal;

public record NominaDetalleDTO(
        Long id,
        Long tipoId,
        String tipoDescripcion,
        BigDecimal monto
) {}

