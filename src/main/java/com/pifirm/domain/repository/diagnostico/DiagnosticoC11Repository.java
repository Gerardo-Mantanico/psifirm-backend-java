package com.pifirm.domain.repository.diagnostico;

import com.pifirm.persistence.entity.diagnostico.DiagnosticoCie11Entity;
import com.pifirm.persistence.entity.diagnostico.DiagnosticoDsm5Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticoC11Repository extends JpaRepository<DiagnosticoCie11Entity, Long> {
}
