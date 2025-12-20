package com.pifirm.domain.repository.planintervencion;

import com.pifirm.persistence.entity.planintervencion.ModalidadIntervencionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModalidadIntervencionRepository extends JpaRepository<ModalidadIntervencionEntity, Long> {
}

