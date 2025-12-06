package com.pifirm.domain.dto.servicio;

import com.pifirm.domain.utils.UniqueValue;
import com.pifirm.persistence.entity.ServicioEntity;


import java.math.BigDecimal;


public record ServicioDto(
         @UniqueValue( fieldName = "nombre",  entityClass = ServicioEntity.class, message = "Ya existe un servicio con este nombre")
         String nombre,
         String descripcion,
         BigDecimal precio
) {

}

