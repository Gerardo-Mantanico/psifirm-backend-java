package com.pifirm.persistence.entity.planintervencion;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;



@Data
@NoArgsConstructor
@Entity
@Table(name = "frecuencia")
public class FrecuenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", length = 255, nullable = false, unique = true)
    private String descripcion;
}

