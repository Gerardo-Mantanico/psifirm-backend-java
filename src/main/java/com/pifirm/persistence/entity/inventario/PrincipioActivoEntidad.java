package com.pifirm.persistence.entity.inventario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "principios_activos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PrincipioActivoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true, length = 150)
    private String nombre;

    @OneToMany(mappedBy = "principioActivo", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<MedicamentoPrincipioActivoEntidad> medicamentos = new HashSet<>();

    public PrincipioActivoEntidad() {
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

    public Set<MedicamentoPrincipioActivoEntidad> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(Set<MedicamentoPrincipioActivoEntidad> medicamentos) {
        this.medicamentos = medicamentos;
    }
}

