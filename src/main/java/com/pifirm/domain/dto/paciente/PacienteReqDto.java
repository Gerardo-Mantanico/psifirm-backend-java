package com.pifirm.domain.dto.paciente;

import com.pifirm.domain.enums.EstadoCivil;
import com.pifirm.domain.enums.Genero;
import com.pifirm.domain.enums.NivelEducativo;
import com.pifirm.domain.enums.Parentesco;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteReqDto {

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Min(value = 8, message = "El numero de telefono tiene que tener como minimo 8 digitos")
    @Max(value = 20, message = "El numero de telefono tiene que tener como maximo 20 digitos")
    private String phoneNumber;

    @NotBlank
    @Min(value=8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

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
    private Long  roleId;


}
