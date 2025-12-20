package com.pifirm.domain.service.hc;

import com.pifirm.domain.dto.paciente.PacienteResDto;
import com.pifirm.domain.repository.InformacionPacienteRepository;
import com.pifirm.persistence.entity.HistoriaClinicaEntity;
import com.pifirm.persistence.entity.InformacionPacienteEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DatosIdentificacionPacienteService {
    private  final InformacionPacienteRepository info;


    public  void registroPaciente(PacienteResDto paciente, Long  hcId){
        InformacionPacienteEntity entidad = new InformacionPacienteEntity();
        entidad.setHcId(hcId);
        Integer  edad = java.time.Period.between(paciente.getInformacionAdicional().getFechaNacimiento(), java.time.LocalDate.now()).getYears();
        entidad.setNombreCompleto(paciente.getUser().firstname()+" "+paciente.getUser().firstname());
        entidad.setGenero(paciente.getInformacionAdicional().getGenero().name());
        entidad.setEstadoCivil(paciente.getInformacionAdicional().getEstadoCivil().name());
        entidad.setOcupacion(paciente.getInformacionAdicional().getOcupacion());
        entidad.setNivelEducativo(paciente.getInformacionAdicional().getNivelEducativo().name());
        entidad.setDireccion(paciente.getInformacionAdicional().getDireccion());
        entidad.setEmail(paciente.getUser().email());
        entidad.setPersonaContacto(paciente.getInformacionAdicional().getPersonaContacto());
        entidad.setParentescoContacto(paciente.getInformacionAdicional().getParentesco().name());
        entidad.setTelefonoContacto(paciente.getInformacionAdicional().getTelefonoContacto());
        entidad.setProcedencia(" ");
        entidad.setMotivoConsulta( " ");
        this.info.save(entidad);
    }

}
