package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.paciente.InfPacienteReqDto;
import com.pifirm.domain.dto.user.UserCreationDto;
import com.pifirm.persistence.entity.PacienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InfPacienteMapper {
    PacienteEntity toPacienteEntity(InfPacienteReqDto dto);
    InfPacienteReqDto toDO(PacienteEntity entity);
}
