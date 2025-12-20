package com.pifirm.domain.dto.paciente;

import com.pifirm.domain.dto.user.UserDto;
import com.pifirm.domain.dto.user.UserSimpleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class PacienteResDto {
    private UserDto user;
    private InfPacienteReqDto informacionAdicional;

}
