package com.pifirm.domain.dto.area;

import com.pifirm.domain.utils.UniqueValue;
import com.pifirm.persistence.entity.AreaEntity;
import com.pifirm.persistence.entity.ServicioEntity;

import java.math.BigDecimal;


public record AreaDto(
         @UniqueValue( fieldName = "nombre",  entityClass = AreaEntity.class, message = "Ya existe un area con este nombre")
         String nombre,
         String descripcion
) {

}

