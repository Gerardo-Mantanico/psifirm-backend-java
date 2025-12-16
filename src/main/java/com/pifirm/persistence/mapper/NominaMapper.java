package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.nomina.NominaDTO;
import com.pifirm.domain.dto.nomina.NominaDetalleDTO;
import com.pifirm.domain.dto.nomina.NominaDetalleReqDTO;
import com.pifirm.domain.dto.nomina.NominaSimpleDTO;
import com.pifirm.persistence.entity.NominaBonoEntity;
import com.pifirm.persistence.entity.NominaDescuentoEntity;
import com.pifirm.persistence.entity.NominaEntity;
import com.pifirm.persistence.entity.NominaRetencionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NominaMapper {

    // Mapear desde DTO simple hacia la entidad (MapStruct puede crear UserEntity/MetodoPagoEntity si existen constructores por defecto)
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "metodoPago.id", source = "metodoPagoId")
    NominaEntity toEntity(NominaSimpleDTO dto);

    // Mapear desde la entidad hacia el DTO
    @Mapping(source = "metodoPago.descripcion.", target = "metodoPago")
    NominaDTO toDto(NominaEntity entity);

    List<NominaDTO> toDtos(List<NominaEntity> entities);

    // Mapear desde el DTO de detalle hacia la entidad detalle (establecer tipoX.id)
    @Mapping(target = "tipoBono.id", source = "tipoId")
    NominaBonoEntity toBonoEntity(NominaDetalleReqDTO dto);

    @Mapping(target = "tipoRetencion.id", source = "tipoId")
    NominaRetencionEntity toRetencionEntity(NominaDetalleReqDTO dto);

    @Mapping(target = "tipoDescuento.id", source = "tipoId")
    NominaDescuentoEntity toDescuentoEntity(NominaDetalleReqDTO dto);

    // Mapear desde la entidad detalle hacia el DTO plano (extraer id y descripcion del tipo)
    @Mapping(source = "tipoBono.id", target = "tipoId")
    @Mapping(source = "tipoBono.descripcion", target = "tipoDescripcion")
    NominaDetalleDTO toDetalleDto(NominaBonoEntity entity);

    @Mapping(source = "tipoRetencion.id", target = "tipoId")
    @Mapping(source = "tipoRetencion.descripcion", target = "tipoDescripcion")
    NominaDetalleDTO toDetalleDto(NominaRetencionEntity entity);

    @Mapping(source = "tipoDescuento.id", target = "tipoId")
    @Mapping(source = "tipoDescuento.descripcion", target = "tipoDescripcion")
    NominaDetalleDTO toDetalleDto(NominaDescuentoEntity entity);
}
