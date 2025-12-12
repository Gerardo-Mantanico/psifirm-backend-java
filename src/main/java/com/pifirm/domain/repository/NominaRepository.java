package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.NominaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NominaRepository extends JpaRepository<NominaEntity, Long> {
    Optional<NominaEntity> findByUser_Id(Long userId);
}
