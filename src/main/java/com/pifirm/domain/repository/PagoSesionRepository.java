package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.PagoSesionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoSesionRepository extends JpaRepository<PagoSesionEntity, Long> {
    List<PagoSesionEntity> findAllByPagado(Boolean pagado);
}
