package com.pifirm.persistence.entity.planintervencion;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "enfoque_terapeutico")
public class EnfoqueTerapeuticoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 255, nullable = false, unique = true)
    private String nombre;
}

