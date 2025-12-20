package com.pifirm.domain.repository.planintervencion;

import com.pifirm.persistence.entity.planintervencion.ModalidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModalidadRepository extends JpaRepository<ModalidadEntity, Long> {
}

