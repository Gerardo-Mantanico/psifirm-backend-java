package com.pifirm.domain.dto.horario;

import com.pifirm.domain.utils.UniqueValue;
import com.pifirm.persistence.entity.AreaEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record HorarioReqDto(
        @NotBlank(message = "El campo dia es obligatorio")
        String diaSemana,
        @NotNull(message = "La hora de inicio es obligatorio")
        LocalTime horaInicio,
        @NotNull(message = "La hora final es obligatorio")
        LocalTime horaFin

) {
}
