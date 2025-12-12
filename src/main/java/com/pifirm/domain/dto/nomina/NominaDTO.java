package com.pifirm.domain.dto.nomina;

import com.pifirm.domain.dto.user.UserSimpleDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record NominaDTO(
        Long id,
        UserSimpleDto user,
        LocalDate periodo,
        BigDecimal salarioBase,
        Integer sesionesTrabajadas,
        BigDecimal salarioBruto,
        BigDecimal salarioNetoAdeudado,
        Long metodoPagoId,
        String detallePago,
        LocalDate fechaCierre,
        List<NominaDetalleDTO> bonos,
        List<NominaDetalleDTO> retenciones,
        List<NominaDetalleDTO> descuentos
) {}

