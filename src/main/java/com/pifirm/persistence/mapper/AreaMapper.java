package com.pifirm.persistence.mapper;


import com.pifirm.domain.dto.area.AreaDto;
import com.pifirm.domain.dto.area.AreaResDto;
import com.pifirm.persistence.entity.AreaEntity;
import com.pifirm.persistence.entity.ServicioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ServicioMapper.class})
public interface AreaMapper {
    AreaResDto toDto(AreaEntity entity);
    
    @Mapping(target = "servicio", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AreaEntity toEntity(AreaDto dto);
    
    List<AreaResDto> toDto(Iterable<AreaEntity> entities);
}

