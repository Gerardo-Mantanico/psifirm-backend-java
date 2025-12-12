package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.NominaRetencionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NominaRetencionRepository extends JpaRepository<NominaRetencionEntity, Long> {
    List<NominaRetencionEntity> findByNomina_Id(Long nominaId);

    Optional<NominaRetencionEntity> findByTipoRetencion_Id(Long aLong);
}

