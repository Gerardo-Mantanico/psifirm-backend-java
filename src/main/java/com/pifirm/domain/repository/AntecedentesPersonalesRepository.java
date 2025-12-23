package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.AntecedentesPersonalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AntecedentesPersonalesRepository extends JpaRepository<AntecedentesPersonalesEntity, Long> {
    Optional<AntecedentesPersonalesEntity> findByHcId(Long hc);
}
