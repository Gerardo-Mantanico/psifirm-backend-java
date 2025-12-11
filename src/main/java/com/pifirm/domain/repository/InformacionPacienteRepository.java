package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.InformacionPacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformacionPacienteRepository extends JpaRepository<InformacionPacienteEntity, Long> {
}
