package com.pifirm.persistence.mapper;


import com.pifirm.domain.dto.especialidad.EspecialidadDto;
import com.pifirm.domain.dto.especialidad.EspecialidadResDto;
import com.pifirm.persistence.entity.EspecialidadEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EspecialidadMapper {
    EspecialidadResDto toDto(EspecialidadEntity entity);
    EspecialidadEntity toEntity(EspecialidadDto dto);
    List<EspecialidadResDto> toDto(Iterable<EspecialidadEntity> entities);
}

