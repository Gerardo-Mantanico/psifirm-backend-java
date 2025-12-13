package com.pifirm.persistence.entity.inventario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lotes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LoteEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicamento_id")
    private MedicamentoEntidad medicamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id")
    private ProveedorEntidad proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacion_id")
    private UbicacionEntidad ubicacion;

    @Column(name = "numero_lote", length = 50)
    private String numeroLote;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_compra", precision = 10, scale = 2)
    private BigDecimal precioCompra;

    @OneToMany(mappedBy = "lote", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<MovimientoInventarioEntidad> movimientos = new HashSet<>();

    public LoteEntidad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MedicamentoEntidad getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(MedicamentoEntidad medicamento) {
        this.medicamento = medicamento;
    }

    public ProveedorEntidad getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorEntidad proveedor) {
        this.proveedor = proveedor;
    }

    public UbicacionEntidad getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionEntidad ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Set<MovimientoInventarioEntidad> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Set<MovimientoInventarioEntidad> movimientos) {
        this.movimientos = movimientos;
    }
}

