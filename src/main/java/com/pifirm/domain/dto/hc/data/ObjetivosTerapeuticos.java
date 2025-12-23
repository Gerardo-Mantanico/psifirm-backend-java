package com.pifirm.domain.dto.hc.data;

import java.math.BigDecimal;
import java.util.List;

public record ObjetivosTerapeuticos(

        Long hcId,
        String  objetivoCortoplazo,
        String objetivoMedioplazo,
        String objetivoLargoplazo,
        List<Long> modalidad,
        List<Long> enfoqueTerapeutico,
        Long frecuencia,
        Integer sesionesPorSemana,
        BigDecimal duracionEstimada,
        BigDecimal costoPorSesion
) {
}
