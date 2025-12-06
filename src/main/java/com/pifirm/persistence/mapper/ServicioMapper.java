package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.servicio.ServicioDto;
import com.pifirm.domain.dto.servicio.ServicioResDto;
import com.pifirm.persistence.entity.ServicioEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicioMapper {
    ServicioResDto toDto(ServicioEntity entity);
    ServicioEntity toEntity(ServicioDto dto);
    List<ServicioResDto> toDto(Iterable<ServicioEntity> entities);
}

