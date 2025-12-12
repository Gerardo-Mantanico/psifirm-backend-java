package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.clinica.ClinicaDto;
import com.pifirm.persistence.entity.ClinicaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-11T17:54:39-0600",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 25.0.1 (Arch Linux)"
)
@Component
public class ClinicaMapperImpl implements ClinicaMapper {

    @Override
    public ClinicaDto toDto(ClinicaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ClinicaDto clinicaDto = new ClinicaDto();

        clinicaDto.setId( entity.getId() );
        clinicaDto.setNombre( entity.getNombre() );
        clinicaDto.setCorreo( entity.getCorreo() );
        clinicaDto.setTelefono( entity.getTelefono() );
        clinicaDto.setDireccion( entity.getDireccion() );
        clinicaDto.setHorasAtencion( entity.getHorasAtencion() );
        clinicaDto.setHoraCierre( entity.getHoraCierre() );
        clinicaDto.setNit( entity.getNit() );
        clinicaDto.setRegistroSanitario( entity.getRegistroSanitario() );

        return clinicaDto;
    }

    @Override
    public ClinicaEntity toEntity(ClinicaDto dto) {
        if ( dto == null ) {
            return null;
        }

        ClinicaEntity clinicaEntity = new ClinicaEntity();

        clinicaEntity.setId( dto.getId() );
        clinicaEntity.setNombre( dto.getNombre() );
        clinicaEntity.setCorreo( dto.getCorreo() );
        clinicaEntity.setTelefono( dto.getTelefono() );
        clinicaEntity.setDireccion( dto.getDireccion() );
        clinicaEntity.setHorasAtencion( dto.getHorasAtencion() );
        clinicaEntity.setHoraCierre( dto.getHoraCierre() );
        clinicaEntity.setNit( dto.getNit() );
        clinicaEntity.setRegistroSanitario( dto.getRegistroSanitario() );

        return clinicaEntity;
    }

    @Override
    public List<ClinicaDto> toDto(Iterable<ClinicaEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ClinicaDto> list = new ArrayList<ClinicaDto>();
        for ( ClinicaEntity clinicaEntity : entities ) {
            list.add( toDto( clinicaEntity ) );
        }

        return list;
    }
}
