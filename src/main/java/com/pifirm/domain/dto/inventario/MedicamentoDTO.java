package com.pifirm.domain.dto.inventario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class MedicamentoDTO {

    private Long id;
    private String nombreComercial;
    private Long formaFarmaceuticaId;
    private Long categoriaId;
    private Integer unidadesPorEmpaque;
    private Integer stockMinimo;
    private BigDecimal precioVenta;
    private Boolean activo;
    private Long principioActivoId;
    private BigDecimal concentracion;
    private Long unidadMedidaId;



}
