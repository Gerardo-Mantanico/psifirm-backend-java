package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.horario.HorarioReqDto;
import com.pifirm.domain.dto.horario.HorarioResDto;
import com.pifirm.persistence.entity.HorarioEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HorarioMapper {

    HorarioResDto ToDO (HorarioEntity entity);
    List<HorarioResDto> listToDo (List<HorarioEntity> entity);
    HorarioEntity ToEntity(HorarioReqDto dto);
}
