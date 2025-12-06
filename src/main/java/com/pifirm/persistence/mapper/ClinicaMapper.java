package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.clinica.ClinicaDto;
import com.pifirm.persistence.entity.ClinicaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClinicaMapper {
    ClinicaDto toDto(ClinicaEntity entity);
    ClinicaEntity toEntity(ClinicaDto dto);
    List<ClinicaDto> toDto(Iterable<ClinicaEntity> entities);
}

