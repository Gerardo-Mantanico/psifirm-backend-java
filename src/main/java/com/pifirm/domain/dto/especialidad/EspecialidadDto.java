package com.pifirm.domain.dto.especialidad;

import com.pifirm.domain.utils.UniqueValue;
import com.pifirm.persistence.entity.AreaEntity;


public record EspecialidadDto(
         @UniqueValue( fieldName = "nombre",  entityClass = AreaEntity.class, message = "Ya existe un area con este nombre")
         String nombre,
         String descripcion
) {

}

