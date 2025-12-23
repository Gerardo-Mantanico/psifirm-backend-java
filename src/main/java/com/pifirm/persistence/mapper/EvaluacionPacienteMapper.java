package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.hc.data.AntecedentesPersonalesDto;
import com.pifirm.persistence.entity.AntecedentesPersonalesEntity;
import com.pifirm.persistence.entity.diagnostico.EvaluacionPsicologicaEntity;
import com.pifirm.persistence.entity.notasProgreso.EvaluacionesPeriodicasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvaluacionPacienteMapper {

    EvaluacionesPeriodicasEntity toDo (AntecedentesPersonalesDto dto);
}
