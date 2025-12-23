package com.pifirm.domain.repository.notasProgreso;

import com.pifirm.persistence.entity.notasProgreso.AltaTerapeuticaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AltaTerapeuticaRepository extends JpaRepository<AltaTerapeuticaEntity, Long> {
    Optional< AltaTerapeuticaEntity>findByHcId(Long hcId);
}

