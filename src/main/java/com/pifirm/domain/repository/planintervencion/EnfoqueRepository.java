package com.pifirm.domain.repository.planintervencion;

import com.pifirm.persistence.entity.planintervencion.EnfoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfoqueRepository extends JpaRepository<EnfoqueEntity, Long> {
}

