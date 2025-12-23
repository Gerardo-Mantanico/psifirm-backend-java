package com.pifirm.persistence.entity.planintervencion;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import javax.print.attribute.standard.MediaSize;

@Data
@NoArgsConstructor
@Entity
@Table(name = "enfoque")
public class EnfoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  //  @ManyToOne(optional = false)
    @Column(name = "configuracion_tratamiento", nullable = false)
    private Long configuracionTratamiento;

    @Column(name = "enfoque_terapeutico_id")
    private Long enfoqueTerapeuticoId;
 }

