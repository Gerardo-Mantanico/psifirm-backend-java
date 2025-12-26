
package com.pifirm.domain.repository.reporte;

import com.pifirm.domain.dto.reporte.MedicamentoNearMinProjection;
import com.pifirm.persistence.entity.inventario.MedicamentoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventarioRepository extends JpaRepository<MedicamentoEntidad, Long> {
    @Query(value = """
        SELECT
          m.id AS medicamento_id,
          m.nombre_comercial,
          m.stock_total,
          m.stock_minimo,
          (m.stock_total - m.stock_minimo) AS diferencia,
          m.precio_venta,
          m.activo
        FROM medicamentos m
        WHERE m.stock_total <= m.stock_minimo + :umbral
        ORDER BY diferencia ASC, m.stock_total ASC
        """, nativeQuery = true)
    List<MedicamentoNearMinProjection> findMedicamentosProximosAlMinimo(@Param("umbral") int umbral);
}