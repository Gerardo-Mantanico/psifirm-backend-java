package com.pifirm.persistence.entity.inventario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pifirm.domain.enums.TipoMovimientoEntidad;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_inventario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MovimientoInventarioEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id")
    private LoteEntidad lote;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", length = 20)
    private TipoMovimientoEntidad tipoMovimiento;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "observacion", columnDefinition = "TEXT")
    private String observacion;

    public MovimientoInventarioEntidad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoteEntidad getLote() {
        return lote;
    }

    public void setLote(LoteEntidad lote) {
        this.lote = lote;
    }

    public TipoMovimientoEntidad getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimientoEntidad tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}

