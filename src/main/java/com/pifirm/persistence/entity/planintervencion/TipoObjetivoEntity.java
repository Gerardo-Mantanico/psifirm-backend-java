package com.pifirm.persistence.entity.planintervencion;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@Entity
@Table(name = "tipo_objetivo")
public class TipoObjetivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_objetivo", columnDefinition = "TEXT", nullable = false)
    private String tipoObjetivo;

    @Column(name = "tiempo", nullable = false, unique = true)
    private String tiempo;
}



