package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.inventario.UnidadMedidaEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnidadMedidaRepository extends JpaRepository<UnidadMedidaEntidad, Long> {
    Optional<UnidadMedidaEntidad> findByNombre(String nombre);
    Optional<UnidadMedidaEntidad> findBySimbolo(String simbolo);
}

