package com.pifirm.domain.dto.ilempleado;

import com.pifirm.domain.dto.area.AreaResDto;
import com.pifirm.domain.dto.especialidad.EspecialidadDto;
import com.pifirm.domain.dto.especialidad.EspecialidadResDto;

import java.math.BigDecimal;

public record ILEmpleadoResDto(
        EspecialidadResDto especialidad,
        String colegiado,
        AreaResDto area
) {
}
