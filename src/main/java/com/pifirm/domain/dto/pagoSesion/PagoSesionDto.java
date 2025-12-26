package com.pifirm.domain.dto.pagoSesion;

import java.math.BigDecimal;

public record PagoSesionDto(
        Long id,
        BigDecimal monto,
        String nota,
        String comprobante
) {
}
