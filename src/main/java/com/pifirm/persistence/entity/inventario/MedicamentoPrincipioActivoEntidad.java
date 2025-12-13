package com.pifirm.persistence.entity.inventario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "medicamento_principio_activo", uniqueConstraints = {@UniqueConstraint(columnNames = {"medicamento_id", "principio_activo_id"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MedicamentoPrincipioActivoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicamento_id")
    private MedicamentoEntidad medicamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "principio_activo_id")
    private PrincipioActivoEntidad principioActivo;

    @Column(name = "concentracion", precision = 10, scale = 2, nullable = false)
    private BigDecimal concentracion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_medida_id")
    private UnidadMedidaEntidad unidadMedida;

    public MedicamentoPrincipioActivoEntidad() {
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

    public PrincipioActivoEntidad getPrincipioActivo() {
        return principioActivo;
    }

    public void setPrincipioActivo(PrincipioActivoEntidad principioActivo) {
        this.principioActivo = principioActivo;
    }

    public BigDecimal getConcentracion() {
        return concentracion;
    }

    public void setConcentracion(BigDecimal concentracion) {
        this.concentracion = concentracion;
    }

    public UnidadMedidaEntidad getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedidaEntidad unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
}

