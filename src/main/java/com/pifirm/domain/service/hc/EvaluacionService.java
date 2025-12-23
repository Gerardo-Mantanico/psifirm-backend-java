package com.pifirm.domain.service.hc;

import com.pifirm.domain.dto.hc.data.AntecedentesPersonalesDto;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.AntecedentesPersonalesRepository;
import com.pifirm.domain.repository.notasProgreso.EvaluacionesPeriodicasRepository;
import com.pifirm.persistence.entity.AntecedentesPersonalesEntity;
import com.pifirm.persistence.entity.notasProgreso.EvaluacionesPeriodicasEntity;
import com.pifirm.persistence.mapper.AntecedentesPersonalesMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EvaluacionService {

    private final EvaluacionesPeriodicasRepository evaluacionesPeriodicasRepository;

    public EvaluacionesPeriodicasEntity save(EvaluacionesPeriodicasEntity entidad) {
         entidad.setId(null);
         entidad.setFechaEvalucacion(LocalDate.now());
        return evaluacionesPeriodicasRepository.save(entidad);
    }

    public List<EvaluacionesPeriodicasEntity> findByIdHc(Long hc) {
        return evaluacionesPeriodicasRepository.findByHcId(hc);
    }

    public void deleteById(Long id) {
        evaluacionesPeriodicasRepository.deleteById(id);
    }
}
