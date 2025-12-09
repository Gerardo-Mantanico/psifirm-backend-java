package com.pifirm.domain.dto.paciente;

import com.pifirm.domain.enums.EstadoCivil;
import com.pifirm.domain.enums.Genero;
import com.pifirm.domain.enums.NivelEducativo;
import com.pifirm.domain.enums.Parentesco;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfPacienteReqDto {

    @NotNull
    @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
    private LocalDate fechaNacimiento;

    @NotNull
    private Genero genero;

    @NotNull
    private EstadoCivil estadoCivil;

    @NotBlank
    private String ocupacion;
    private NivelEducativo nivelEducativo;
    private String direccion;
    private String personaContacto;
    private Parentesco parentesco;
    private String telefonoContacto;

}
