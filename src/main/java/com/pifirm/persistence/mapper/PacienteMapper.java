package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.paciente.PacienteReqDto;
import com.pifirm.domain.dto.user.UserCreationDto;
import com.pifirm.persistence.entity.PacienteEntity;
import com.pifirm.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    UserCreationDto toUserEntity(PacienteReqDto dto);
    PacienteEntity toPacienteEntity(PacienteReqDto dto);
}
