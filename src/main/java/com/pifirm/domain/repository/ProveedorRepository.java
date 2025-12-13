package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.inventario.ProveedorEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<ProveedorEntidad, Long> {
    Optional<ProveedorEntidad> findByNombre(String nombre);
}

