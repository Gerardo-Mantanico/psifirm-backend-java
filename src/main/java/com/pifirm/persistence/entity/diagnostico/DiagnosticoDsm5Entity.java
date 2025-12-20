package com.pifirm.persistence.entity.diagnostico;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "diagnostico_dsm_5")
public class DiagnosticoDsm5Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(nullable = false)
    private String codigo;

    @Column(columnDefinition = "TEXT")
    private String description;
}
