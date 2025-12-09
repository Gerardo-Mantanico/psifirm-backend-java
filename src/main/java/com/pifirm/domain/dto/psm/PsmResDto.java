package com.pifirm.domain.dto.psm;

import com.pifirm.domain.dto.horario.HorarioReqDto;
import com.pifirm.domain.dto.ilempleado.ILEmpleadoReqDto;
import com.pifirm.domain.dto.ilempleado.ILEmpleadoResDto;

import java.util.List;

public record PsmResDto(
        ILEmpleadoResDto ilempleadoResDto,
        List<HorarioReqDto> horarioReqDto
) {
}
