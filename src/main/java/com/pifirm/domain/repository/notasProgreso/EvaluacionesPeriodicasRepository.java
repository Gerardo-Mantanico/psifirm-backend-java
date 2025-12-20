package com.pifirm.domain.repository.notasProgreso;

import com.pifirm.persistence.entity.notasProgreso.EvaluacionesPeriodicasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionesPeriodicasRepository extends JpaRepository<EvaluacionesPeriodicasEntity, Long> {
}

