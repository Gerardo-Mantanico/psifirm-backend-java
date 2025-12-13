package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.inventario.PrincipioActivoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrincipioActivoRepository extends JpaRepository<PrincipioActivoEntidad, Long> {
    Optional<PrincipioActivoEntidad> findByNombre(String nombre);
}

