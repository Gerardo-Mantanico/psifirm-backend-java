package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.cita.CitaDto;
import com.pifirm.domain.dto.cita.CitaResDto;
import com.pifirm.persistence.entity.CitaMedicaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    @Mapping(source = "historiaClinica.id", target = "historiaClinicaId")
   // @Mapping(source = "paciente.id", target = "pacienteId")
    //@Mapping(source = "medico.id", target = "medicoId")
   // @Mapping(source = "servicioMedico.id", target = "servicioMedicoId")
    CitaResDto toDto(CitaMedicaEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "historiaClinica", ignore = true)
    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "medico", ignore = true)
    @Mapping(target = "servicioMedico", ignore = true)
    @Mapping(target = "estadoCita", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    CitaMedicaEntity toEntity(CitaDto dto);

    List<CitaResDto> toDto(Iterable<CitaMedicaEntity> entities);
}

