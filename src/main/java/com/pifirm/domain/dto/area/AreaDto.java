package com.pifirm.domain.dto.area;

import com.pifirm.domain.repository.ServicioRepository;
import com.pifirm.domain.utils.ExistsValue;
import com.pifirm.domain.utils.UniqueValue;
import com.pifirm.persistence.entity.AreaEntity;
import jakarta.validation.constraints.NotNull;


public record AreaDto(
         @UniqueValue( fieldName = "nombre",  entityClass = AreaEntity.class, message = "Ya existe un area con este nombre")
         String nombre,
         String descripcion,
         @NotNull(message = "El servicio es requerido")
         @ExistsValue(repository = ServicioRepository.class, message = "El servicio no existe")
         Long servicioId
) {

}

