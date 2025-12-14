package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.servicio.ServicioDto;
import com.pifirm.domain.dto.servicio.ServicioResDto;
import com.pifirm.persistence.entity.ServicioEntity;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-13T16:50:51-0600",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 25.0.1 (Arch Linux)"
)
@Component
public class ServicioMapperImpl implements ServicioMapper {

    @Override
    public ServicioResDto toDto(ServicioEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String nombre = null;
        String descripcion = null;
        BigDecimal precio = null;
        Instant createdAt = null;
        Instant updatedAt = null;

        id = entity.getId();
        nombre = entity.getNombre();
        descripcion = entity.getDescripcion();
        precio = entity.getPrecio();
        createdAt = entity.getCreatedAt();
        updatedAt = entity.getUpdatedAt();

        ServicioResDto servicioResDto = new ServicioResDto( id, nombre, descripcion, precio, createdAt, updatedAt );

        return servicioResDto;
    }

    @Override
    public ServicioEntity toEntity(ServicioDto dto) {
        if ( dto == null ) {
            return null;
        }

        ServicioEntity servicioEntity = new ServicioEntity();

        servicioEntity.setNombre( dto.nombre() );
        servicioEntity.setDescripcion( dto.descripcion() );
        servicioEntity.setPrecio( dto.precio() );

        return servicioEntity;
    }

    @Override
    public List<ServicioResDto> toDto(Iterable<ServicioEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ServicioResDto> list = new ArrayList<ServicioResDto>();
        for ( ServicioEntity servicioEntity : entities ) {
            list.add( toDto( servicioEntity ) );
        }

        return list;
    }
}
