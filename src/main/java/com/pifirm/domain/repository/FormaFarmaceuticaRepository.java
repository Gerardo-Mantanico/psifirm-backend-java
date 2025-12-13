package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.inventario.FormaFarmaceuticaEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormaFarmaceuticaRepository extends JpaRepository<FormaFarmaceuticaEntidad, Long> {
    Optional<FormaFarmaceuticaEntidad> findByNombre(String nombre);
}

