package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.NominaBonoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NominaBonoRepository extends JpaRepository<NominaBonoEntity, Long> {
    List<NominaBonoEntity> findByNomina_Id(Long nominaId);

    Optional<NominaBonoEntity> findByTipoBono_Id(Long aLong);
}

