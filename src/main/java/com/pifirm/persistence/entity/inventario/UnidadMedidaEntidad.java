package com.pifirm.persistence.entity.inventario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "unidades_medida")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UnidadMedidaEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true, length = 20)
    private String nombre;

    @Column(name = "simbolo", nullable = false, unique = true, length = 10)
    private String simbolo;

    @OneToMany(mappedBy = "unidadMedida", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<MedicamentoPrincipioActivoEntidad> principios = new HashSet<>();

    public UnidadMedidaEntidad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public Set<MedicamentoPrincipioActivoEntidad> getPrincipios() {
        return principios;
    }

    public void setPrincipios(Set<MedicamentoPrincipioActivoEntidad> principios) {
        this.principios = principios;
    }
}

