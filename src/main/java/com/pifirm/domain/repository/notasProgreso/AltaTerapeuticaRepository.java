package com.pifirm.domain.repository.notasProgreso;

import com.pifirm.persistence.entity.notasProgreso.AltaTerapeuticaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AltaTerapeuticaRepository extends JpaRepository<AltaTerapeuticaEntity, Long> {
}

