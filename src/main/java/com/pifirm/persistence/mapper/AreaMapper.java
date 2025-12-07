package com.pifirm.persistence.mapper;


import com.pifirm.domain.dto.area.AreaDto;
import com.pifirm.domain.dto.area.AreaResDto;
import com.pifirm.persistence.entity.AreaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AreaMapper {
    AreaResDto toDto(AreaEntity entity);
    AreaEntity toEntity(AreaDto dto);
    List<AreaResDto> toDto(Iterable<AreaEntity> entities);
}

