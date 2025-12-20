package com.pifirm.domain.repository.planintervencion;

import com.pifirm.persistence.entity.planintervencion.ConfiguracionTratamientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracionTratamientoRepository extends JpaRepository<ConfiguracionTratamientoEntity, Long> {
}

