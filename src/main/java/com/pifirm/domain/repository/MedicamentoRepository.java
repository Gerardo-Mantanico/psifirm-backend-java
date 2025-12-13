package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.inventario.MedicamentoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicamentoRepository extends JpaRepository<MedicamentoEntidad, Long> {
    Optional<MedicamentoEntidad> findByNombreComercial(String nombreComercial);
}

