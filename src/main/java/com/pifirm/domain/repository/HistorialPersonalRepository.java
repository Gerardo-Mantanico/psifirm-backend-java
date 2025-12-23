package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.HistorialPersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HistorialPersonalRepository extends JpaRepository<HistorialPersonalEntity, Long> {
    Optional<HistorialPersonalEntity> findByHcId(Long hc);
}
