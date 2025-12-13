package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.inventario.LoteEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoteRepository extends JpaRepository<LoteEntidad, Long> {
    Optional<LoteEntidad> findByNumeroLote(String numeroLote);
}

