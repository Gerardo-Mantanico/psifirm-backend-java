package com.pifirm.persistence.entity.inventario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medicamentos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MedicamentoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_comercial", nullable = false, length = 150)
    private String nombreComercial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_farmaceutica_id")
    private FormaFarmaceuticaEntidad formaFarmaceutica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private CategoriaProductoEntidad categoria;

    @Column(name = "unidades_por_empaque", nullable = false)
    private Integer unidadesPorEmpaque;

    @Column(name = "stock_minimo")
    private Integer stockMinimo = 0;

    @Column(name = "precio_venta", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioVenta;

    @Column(name = "activo")
    private Boolean activo = true;

    @OneToMany(mappedBy = "medicamento", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<MedicamentoPrincipioActivoEntidad> principios = new HashSet<>();

    @OneToMany(mappedBy = "medicamento", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<LoteEntidad> lotes = new HashSet<>();

    public MedicamentoEntidad() {
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

    public FormaFarmaceuticaEntidad getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public void setFormaFarmaceutica(FormaFarmaceuticaEntidad formaFarmaceutica) {
        this.formaFarmaceutica = formaFarmaceutica;
    }

    public CategoriaProductoEntidad getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProductoEntidad categoria) {
        this.categoria = categoria;
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

    public Set<MedicamentoPrincipioActivoEntidad> getPrincipios() {
        return principios;
    }

    public void setPrincipios(Set<MedicamentoPrincipioActivoEntidad> principios) {
        this.principios = principios;
    }

    public Set<LoteEntidad> getLotes() {
        return lotes;
    }

    public void setLotes(Set<LoteEntidad> lotes) {
        this.lotes = lotes;
    }
}

