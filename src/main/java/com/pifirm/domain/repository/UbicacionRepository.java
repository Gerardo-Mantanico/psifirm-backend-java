package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.inventario.UbicacionEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UbicacionRepository extends JpaRepository<UbicacionEntidad, Long> {
    Optional<UbicacionEntidad> findByNombre(String nombre);
}

