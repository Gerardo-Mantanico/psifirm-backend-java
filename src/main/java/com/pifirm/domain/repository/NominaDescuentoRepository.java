package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.NominaDescuentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NominaDescuentoRepository extends JpaRepository<NominaDescuentoEntity, Long> {
    List<NominaDescuentoEntity> findByNomina_Id(Long nominaId);
    Optional<NominaDescuentoEntity> findByTipoDescuento_Id(Long aLong);
}

