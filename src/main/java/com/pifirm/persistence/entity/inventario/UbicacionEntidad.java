package com.pifirm.persistence.entity.inventario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ubicaciones")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UbicacionEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "ubicacion", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<LoteEntidad> lotes = new HashSet<>();

    public UbicacionEntidad() {
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

    public Set<LoteEntidad> getLotes() {
        return lotes;
    }

    public void setLotes(Set<LoteEntidad> lotes) {
        this.lotes = lotes;
    }
}

