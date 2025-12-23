package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.hc.HcResDto;
import com.pifirm.domain.dto.hc.data.AntecedentesPersonalesDto;
import com.pifirm.persistence.entity.AntecedentesPersonalesEntity;
import com.pifirm.persistence.entity.HistoriaClinicaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AntecedentesPersonalesMapper {

    AntecedentesPersonalesEntity toDo (AntecedentesPersonalesDto dto);
}
