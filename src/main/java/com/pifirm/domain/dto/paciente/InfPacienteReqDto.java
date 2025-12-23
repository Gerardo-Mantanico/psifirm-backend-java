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
    private Long hcId;

    @NotNull
    @Min(value = 0, message = "edad must be >= 0")
    @Max(value = 150, message = "edad must be <= 150")
    private Integer edad;

    @NotBlank
    @Size(max = 200)
    private String nombreCompleto;

    @NotBlank
    private String genero;

    @NotBlank
    private String estadoCivil;

    @NotBlank
    @Size(max = 100)
    private String ocupacion;

    @NotBlank
    private String nivelEducativo;

    @NotBlank
    @Size(max = 300)
    private String direccion;

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9 .-]{7,20}$", message = "telefono inválido")
    private String telefono;

    @NotBlank
    @Email
    @Size(max = 200)
    private String email;

    @NotBlank
    @Size(max = 200)
    private String personaContacto;

    @NotBlank
    private String parentescoContacto;

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9 .-]{7,20}$", message = "telefonoContacto inválido")
    private String telefonoContacto;

    @NotBlank
    @Size(max = 200)
    private String procedencia;

    @NotBlank
    @Size(max = 1000)
    private String motivoConsulta;

}
