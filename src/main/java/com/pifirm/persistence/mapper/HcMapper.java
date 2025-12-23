package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.hc.HcResDto;
import com.pifirm.persistence.entity.HistoriaClinicaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HcMapper {

    HcResDto toDo (HistoriaClinicaEntity entity);
}
