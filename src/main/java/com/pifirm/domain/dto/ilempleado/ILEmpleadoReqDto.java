package com.pifirm.domain.dto.ilempleado;

import java.math.BigDecimal;

public record ILEmpleadoReqDto(
        Long especialidadId,
        String colegiado,
        Long areaId,
        String tipoContrato,
        BigDecimal tarifa
) {
}


