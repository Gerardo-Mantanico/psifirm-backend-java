package com.pifirm.domain.service.hc;

import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.HistorialPersonalRepository;
import com.pifirm.domain.repository.diagnostico.EvaluacionPsicologicaRepository;
import com.pifirm.persistence.entity.HistorialPersonalEntity;
import com.pifirm.persistence.entity.diagnostico.EvaluacionPsicologicaEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EvaluacionPacienteService {

    private final EvaluacionPsicologicaRepository evaluacionPsicologicaRepository;

    @Transactional
    public EvaluacionPsicologicaEntity save(EvaluacionPsicologicaEntity entidad) {
        entidad.setId(null);
        return evaluacionPsicologicaRepository.save(entidad);
    }

    public EvaluacionPsicologicaEntity findByIdHc(Long hc) {
        return evaluacionPsicologicaRepository.findByHcId(hc).orElseThrow(()-> new GeneralException("eror","No se encontraron historial personal para el paciente con ID: " + hc));
    }
}
