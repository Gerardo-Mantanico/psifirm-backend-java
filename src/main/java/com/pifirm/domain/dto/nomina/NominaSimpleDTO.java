package com.pifirm.domain.dto.nomina;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NominaSimpleDTO(
        Long userId,
        LocalDate periodo,
        BigDecimal salarioBase,
        Long metodoPagoId,
        String detallePago,
        LocalDate fechaCierre
) {}

