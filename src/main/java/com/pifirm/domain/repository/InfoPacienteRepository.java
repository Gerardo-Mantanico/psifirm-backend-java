package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.InformacionPacienteEntity;
import com.pifirm.persistence.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InfoPacienteRepository extends JpaRepository<InformacionPacienteEntity, Long> {
    Optional<InformacionPacienteEntity> findByHcId(Long id);
}
