package com.pifirm.persistence.entity.diagnostico;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "impresion_diagnostica")
public class ImpresionDiagnosticaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hc_id", nullable = false)
    private Long hcId;

    //@ManyToOne
   // @JoinColumn(name = "diagnostico_principal_cie_11", nullable = false)
    @Column(name = "diagnostico_principal_cie_11", nullable = false)
    private Long diagnosticoPrincipalCie11;

   // @ManyToOne
    //@JoinColumn(name = "diagnostico_principal_dsm_5", nullable = false)
    @Column(name = "diagnostico_principal_dsm_5", nullable = false)
    private Long diagnosticoPrincipalDsm5;

    @Column(name = "factores_predisponentes", columnDefinition = "TEXT")
    private String factoresPredisponentes;

    @Column(name = "factores_precipiantes", columnDefinition = "TEXT")
    private String factoresPrecipitantes;

    @Column(name = "factores_mantenedores", columnDefinition = "TEXT")
    private String factoresMantenedores;

    @Column(name = "nivel_funcionamiento", nullable = false)
    private Integer nivelFuncionamiento;
}
