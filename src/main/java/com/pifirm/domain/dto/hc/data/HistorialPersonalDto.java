package com.pifirm.domain.dto.hc.data;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialPersonalDto {

    private Long hcId;

    @Size(max = 5000)
    private String desarrolloEvolutivo;

    @Size(max = 5000)
    private String hAcademica;

    @Size(max = 5000)
    private String hMedica;

    @Size(max = 5000)
    private String medicacionActual;

    @Size(max = 50)
    private String consumoAlcohol;

    @Size(max = 50)
    private String consumoTabaco;

    @Size(max = 5000)
    private String consumoDrogras;

    private Boolean tratamientosPrevios;

    @Size(max = 5000)
    private String tratamientosPreviosDetalles;

    private Boolean hospitalizaciones;

    @Size(max = 5000)
    private String hospitalizacionesDetalles;
}

