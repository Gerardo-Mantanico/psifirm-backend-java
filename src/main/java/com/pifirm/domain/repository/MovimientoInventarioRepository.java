package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.inventario.MovimientoInventarioEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventarioEntidad, Long> {
    List<MovimientoInventarioEntidad> findByLoteId(Long loteId);
}

