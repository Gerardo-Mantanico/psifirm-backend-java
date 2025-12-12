package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.nomina.NominaDTO;
import com.pifirm.domain.dto.nomina.NominaDetalleDTO;
import com.pifirm.domain.dto.nomina.NominaDetalleReqDTO;
import com.pifirm.domain.dto.nomina.NominaSimpleDTO;
import com.pifirm.persistence.entity.NominaBonoEntity;
import com.pifirm.persistence.entity.NominaDescuentoEntity;
import com.pifirm.persistence.entity.NominaEntity;
import com.pifirm.persistence.entity.NominaRetencionEntity;
import jdk.dynalink.linker.LinkerServices;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NominaMapper {

    NominaEntity toEntity(NominaSimpleDTO dto);
    NominaDTO toDto(NominaEntity entity);
    List<NominaDTO> toDtos(List<NominaEntity> entities);

    NominaBonoEntity toBonoEntity(NominaDetalleReqDTO dto);
    NominaRetencionEntity toRetencionEntity(NominaDetalleReqDTO dto);
    NominaDescuentoEntity toDescuentoEntity(NominaDetalleReqDTO dto);


    NominaDetalleDTO toDetalleDto(NominaBonoEntity entity);
    NominaDetalleDTO toDetalleDto(NominaRetencionEntity entity);
    NominaDetalleDTO toDetalleDto(NominaDescuentoEntity entity);
}
