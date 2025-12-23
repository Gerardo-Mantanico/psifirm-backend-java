package com.pifirm.domain.dto.hc.data;

import jakarta.validation.constraints.NotNull;

public record AntecedentesPersonalesDto(
         Long hcId,
        @NotNull
         String estructuraFa,

         Boolean trastornos ,
         String trastornoDetalles,

        @NotNull
         String eventosFr
) {
}
