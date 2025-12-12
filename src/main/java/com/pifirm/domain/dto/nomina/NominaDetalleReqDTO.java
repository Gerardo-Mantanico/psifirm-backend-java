package com.pifirm.domain.dto.nomina;

import java.math.BigDecimal;

public record NominaDetalleReqDTO(
        Long tipoId,
        BigDecimal monto
) {}

