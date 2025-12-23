package com.pifirm.domain.dto.diagnostico;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionPsicologicaDto {
    @NotNull
    private Long hcId;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer estadoAnimo;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer ansiedad;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer funcionamientoSocial;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer calidadSueno;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer apetito;

    @Size(max = 5000)
    private String observacionesGenerales;
}

