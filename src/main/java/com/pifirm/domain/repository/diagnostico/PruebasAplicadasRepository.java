package com.pifirm.domain.repository.diagnostico;

import com.pifirm.persistence.entity.diagnostico.PruebasAplicadasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PruebasAplicadasRepository extends JpaRepository<PruebasAplicadasEntity, Long> {
    List<PruebasAplicadasEntity> findAllByHcId(Long hcId);
}
