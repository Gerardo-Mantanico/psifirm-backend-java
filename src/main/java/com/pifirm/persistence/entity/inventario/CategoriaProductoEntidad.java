package com.pifirm.persistence.entity.inventario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categorias")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CategoriaProductoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<MedicamentoEntidad> medicamentos = new HashSet<>();

    public CategoriaProductoEntidad() {
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

    public Set<MedicamentoEntidad> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(Set<MedicamentoEntidad> medicamentos) {
        this.medicamentos = medicamentos;
    }
}
