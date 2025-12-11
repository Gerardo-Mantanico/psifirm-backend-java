package com.pifirm.domain.dto.hc;

import com.pifirm.domain.dto.hc.informacion.AntecedentesPersonaleResDto;
import com.pifirm.domain.dto.hc.informacion.HistorialPersonalResDto;
import com.pifirm.domain.dto.hc.informacion.InformacionPacienteResDto;
import com.pifirm.domain.dto.psm.PsmBasica;
import com.pifirm.domain.dto.servicio.ServicioResDto;

import java.time.LocalDate;

public record hcResDto(
        String nombreInstitucion,
        ServicioResDto sercicio,
        String code,
        LocalDate date,
        PsmBasica psm,
        Boolean estado,

        //informacion  del paciente
        InformacionPacienteResDto informacionPacienteResDto,
        AntecedentesPersonaleResDto antecedentesPersonalesDto,
        HistorialPersonalResDto historialPersonalResDto
) {
}
