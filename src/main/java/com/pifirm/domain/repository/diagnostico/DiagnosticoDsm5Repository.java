package com.pifirm.domain.repository.diagnostico;

import com.pifirm.persistence.entity.diagnostico.DiagnosticoDsm5Entity;
import com.pifirm.persistence.entity.diagnostico.TipoPruebasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticoDsm5Repository extends JpaRepository<DiagnosticoDsm5Entity, Long> {
}
