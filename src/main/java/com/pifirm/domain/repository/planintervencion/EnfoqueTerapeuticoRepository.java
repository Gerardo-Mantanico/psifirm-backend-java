package com.pifirm.domain.repository.planintervencion;

import com.pifirm.persistence.entity.planintervencion.EnfoqueTerapeuticoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfoqueTerapeuticoRepository extends JpaRepository<EnfoqueTerapeuticoEntity, Long> {
}

