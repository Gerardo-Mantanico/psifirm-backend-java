package com.pifirm.domain.dto.psm;

import com.pifirm.domain.dto.horario.HorarioReqDto;
import com.pifirm.domain.dto.ilempleado.ILEmpleadoReqDto;
import com.pifirm.domain.dto.user.UserCreationDto;

import java.util.List;

public record PsmReqDto(
        UserCreationDto user,
        ILEmpleadoReqDto ilempleadoReqDto,
        List<HorarioReqDto> horarioReqDto
) {
}
