package com.pifirm.persistence.entity;

import com.pifirm.domain.enums.EstadoCivil;
import com.pifirm.domain.enums.Genero;
import com.pifirm.domain.enums.NivelEducativo;
import com.pifirm.domain.enums.Parentesco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "paciente")
@Data
public class PacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @NotNull
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "genero", nullable = false, length = 20)
    private Genero genero;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil", nullable = false, length = 30)
    private EstadoCivil estadoCivil;

    @Size(max = 100)
    @Column(name = "ocupacion", length = 100)
    private String ocupacion;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_educativo", nullable = false, length = 50)
    private NivelEducativo nivelEducativo;

    @Size(max = 200)
    @Column(name = "direccion", length = 200)
    private String direccion;

    @NotNull
    @Size(max = 100)
    @Column(name = "persona_contacto", nullable = false, length = 100)
    private String personaContacto;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "parentesco", nullable = false, length = 30)
    private Parentesco parentesco;

    @NotNull
    @Size(max = 15)
    @Column(name = "telefono_contacto", nullable = false, length = 15)
    private String telefonoContacto;
}
