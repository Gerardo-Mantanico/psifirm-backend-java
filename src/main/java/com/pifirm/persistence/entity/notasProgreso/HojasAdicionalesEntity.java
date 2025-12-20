package com.pifirm.persistence.entity.notasProgreso;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hojas_adicionales")
public class HojasAdicionalesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hc_id", nullable = false)
    private Long hcId;

    @Column(name = "consentimiento_firmado", nullable = false, columnDefinition = "TEXT")
    private String consentimientoFirmado;

    @Column(name = "resultado_completo_con_otro_profecionales", nullable = false, columnDefinition = "TEXT")
    private String resultadoCompletoConOtroProfecionales;

    @Column(name = "registro_conductuales", nullable = false, columnDefinition = "TEXT")
    private String registroConductuales;
}

