package com.pifirm.domain.service.hc;

import com.pifirm.domain.repository.diagnostico.EvaluacionPsicologicaRepository;
import com.pifirm.domain.repository.notasProgreso.EvaluacionesPeriodicasRepository;
import com.pifirm.persistence.entity.diagnostico.EvaluacionPsicologicaEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EstadoActualService {
    private final EvaluacionPsicologicaRepository evaluacionPsicologicaRepository;

    private EvaluacionPsicologicaEntity save (EvaluacionPsicologicaEntity entidad) {
        return evaluacionPsicologicaRepository.save(entidad);
    }

    private EvaluacionPsicologicaEntity findByIdPaciente(Long hc) {
        return evaluacionPsicologicaRepository.findByHcId(hc).orElse(null);
    }
}
