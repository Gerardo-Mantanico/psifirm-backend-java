package com.pifirm.domain.dto.inventario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoDetalleDTO {

    private Long id;
    private String nombreComercial;
    private FormaFarmaceuticaDetalleDTO formaFarmaceutica;
    private CategoriaProductoDetalleDTO categoria;
    private Integer unidadesPorEmpaque;
    private Integer stockMinimo;
    private BigDecimal precioVenta;
    private Boolean activo;
    private List<PrincipioActivoDetalleDTO> principiosActivos = new ArrayList<>();

    // Clases internas para detalles de relaciones
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FormaFarmaceuticaDetalleDTO {
        private Long id;
        private String nombre;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoriaProductoDetalleDTO {
        private Long id;
        private String nombre;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PrincipioActivoDetalleDTO {
        private Long id;
        private String nombre;
        private BigDecimal concentracion;
        private UnidadMedidaDetalleDTO unidadMedida;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnidadMedidaDetalleDTO {
        private Long id;
        private String nombre;
        private String simbolo;
    }
}

