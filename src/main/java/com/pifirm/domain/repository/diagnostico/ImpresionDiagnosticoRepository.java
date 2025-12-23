package com.pifirm.domain.repository.diagnostico;

import com.pifirm.persistence.entity.diagnostico.ImpresionDiagnosticaEntity;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImpresionDiagnosticoRepository extends JpaRepository<ImpresionDiagnosticaEntity, Long> {
    Optional<ImpresionDiagnosticaEntity> findByHcId(Long hc);
}
