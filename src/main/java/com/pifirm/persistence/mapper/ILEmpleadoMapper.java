package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.ilempleado.ILEmpleadoResDto;
import com.pifirm.persistence.entity.ILEmpleadoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ILEmpleadoMapper {
    ILEmpleadoResDto toDO(ILEmpleadoEntity entity);
}
