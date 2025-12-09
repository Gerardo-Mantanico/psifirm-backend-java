package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.horario.HorarioReqDto;
import com.pifirm.domain.dto.ilempleado.ILEmpleadoReqDto;
import com.pifirm.domain.dto.ilempleado.ILEmpleadoResDto;
import com.pifirm.domain.dto.user.UserCreationDto;
import com.pifirm.persistence.entity.HorarioEntity;
import com.pifirm.persistence.entity.ILEmpleadoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PsmMapper {

    ILEmpleadoEntity toILEmpleadoEntity(ILEmpleadoReqDto ilempleadoReqDto);
    ILEmpleadoResDto toILEmpleadoResDto(ILEmpleadoEntity ilempleadoEntity);

    HorarioEntity toHorarioEntity(HorarioReqDto horarioReqDto);

    List<HorarioEntity> toListHorarioEntity(List<HorarioReqDto> horarioReqDto);
}
