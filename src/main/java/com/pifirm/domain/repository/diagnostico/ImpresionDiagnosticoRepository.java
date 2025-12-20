package com.pifirm.domain.repository.diagnostico;

import com.pifirm.persistence.entity.diagnostico.ImpresionDiagnosticaEntity;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImpresionDiagnosticoRepository extends JpaRepository<ImpresionDiagnosticaEntity, Long> {
}
