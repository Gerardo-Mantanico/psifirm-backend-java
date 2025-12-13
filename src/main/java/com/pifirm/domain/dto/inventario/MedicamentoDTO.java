package com.pifirm.domain.dto.inventario;

import java.math.BigDecimal;

public class MedicamentoDTO {

    private Long id;
    private String nombreComercial;
    private Long formaFarmaceuticaId;
    private Long categoriaId;
    private Integer unidadesPorEmpaque;
    private Integer stockMinimo;
    private BigDecimal precioVenta;
    private Boolean activo;

    public MedicamentoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public Long getFormaFarmaceuticaId() {
        return formaFarmaceuticaId;
    }

    public void setFormaFarmaceuticaId(Long formaFarmaceuticaId) {
        this.formaFarmaceuticaId = formaFarmaceuticaId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Integer getUnidadesPorEmpaque() {
        return unidadesPorEmpaque;
    }

    public void setUnidadesPorEmpaque(Integer unidadesPorEmpaque) {
        this.unidadesPorEmpaque = unidadesPorEmpaque;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
