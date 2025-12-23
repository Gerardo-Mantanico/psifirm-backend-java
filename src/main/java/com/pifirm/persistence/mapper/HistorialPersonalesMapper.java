package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.hc.data.HistorialPersonalDto;
import com.pifirm.persistence.entity.HistorialPersonalEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HistorialPersonalesMapper {

    HistorialPersonalEntity toDo (HistorialPersonalDto dto);
}
