package com.pifirm.persistence.entity.notasProgreso;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "notas_especiales")
public class NotasEspecialesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hc_id", nullable = false)
    private Long hcId;

    @Column(name = "situaciones_criticas", columnDefinition = "TEXT")
    private String situacionesCriticas;

    @Column(name = "contacto_con_otro_profecionales", columnDefinition = "TEXT")
    private String contactoConOtroProfecionales;

    @Column(name = "autorizaciones", columnDefinition = "TEXT")
    private String autorizaciones;

    @Column(name = "confidencialidad_limites", columnDefinition = "TEXT")
    private String confidencialidadLimites;
}

