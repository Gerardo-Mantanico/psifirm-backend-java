package com.pifirm.persistence.entity.planintervencion;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "modalidad")
public class ModalidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "configuracion_tratamiento", nullable = false)
    private ConfiguracionTratamientoEntity configuracionTratamiento;
}

