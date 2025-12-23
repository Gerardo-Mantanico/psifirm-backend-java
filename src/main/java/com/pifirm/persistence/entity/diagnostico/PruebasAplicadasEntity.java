package com.pifirm.persistence.entity.diagnostico;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@Entity
@Table(name ="prueba_aplicadas")
@NoArgsConstructor
public class PruebasAplicadasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hc_id", nullable = false)
    private Long hcId;

    @Column(name = "prueba_id", nullable = false)
    private Long prueba;

    @Column(name = "fecha_aplicacion")
    private LocalDate fechaAplicacion;

    @Column(precision = 5, scale = 2)
    private BigDecimal resultado;

    @Column(columnDefinition = "TEXT")
    private String interpretacion;

    @Column(columnDefinition = "TEXT")
    private String archivo;

}
