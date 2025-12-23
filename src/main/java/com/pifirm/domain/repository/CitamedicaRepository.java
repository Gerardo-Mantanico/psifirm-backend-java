package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.CitaMedicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CitamedicaRepository extends JpaRepository<CitaMedicaEntity,Long> {
    CitaMedicaEntity findByHistoriaClinica_Id(Long hcId);
}
