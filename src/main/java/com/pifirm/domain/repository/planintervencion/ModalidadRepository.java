package com.pifirm.domain.repository.planintervencion;

import com.pifirm.persistence.entity.planintervencion.ModalidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModalidadRepository extends JpaRepository<ModalidadEntity, Long> {
    List<ModalidadEntity> findByConfiguracionTratamiento(Long id);
}

