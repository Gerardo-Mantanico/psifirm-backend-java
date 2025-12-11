package com.pifirm.domain.dto.hc.informacion;

import com.pifirm.domain.dto.psm.PsmBasica;
import com.pifirm.domain.dto.servicio.ServicioResDto;
import org.eclipse.angus.mail.imap.protocol.BODY;

import java.time.LocalDate;

public record HistorialPersonalResDto(
    Long id,
    String desarrolloEvolutivo,
    String hAcademico,
    String hMedico,
    String medicacionActual,
    String consumoAlcohol,
    String consumoDrogas,
    String consumoTabaco,
    Boolean tratamientoPrevios,
    String tratamtientoPreviosDetalles,
    Boolean hospitalizacion,
    String hospitalizacionDetalles

) {
}
