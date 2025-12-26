package com.pifirm.domain.dto.reporte;

import java.math.BigDecimal;

public interface MedicamentoNearMinProjection {
    Long getMedicamentoId();
    String getNombreComercial();
    Integer getStockTotal();
    Integer getStockMinimo();
    Integer getDiferencia();
    BigDecimal getPrecioVenta();
    Boolean getActivo();
}