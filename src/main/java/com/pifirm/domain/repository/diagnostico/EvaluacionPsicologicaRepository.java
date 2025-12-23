package com.pifirm.domain.repository.diagnostico;

import com.pifirm.persistence.entity.diagnostico.EvaluacionPsicologicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EvaluacionPsicologicaRepository extends JpaRepository<EvaluacionPsicologicaEntity, Long> {
    Optional<EvaluacionPsicologicaEntity> findByHcId(Long hc);
}
