package com.pifirm.domain.service.hc;


import com.pifirm.domain.repository.*;
import com.pifirm.domain.service.*;
import com.pifirm.persistence.entity.HistoriaClinicaEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HcService {

     private final HistorialClinicaRepository historialClinicaRepository;
     private final CitaService citaService;
     private final UserService userService;
     private final ClinicaService clinicaService;
     private final ServicioRepository servicioRepository;
     private final DatosIdentificacionPacienteService datosIdentificacionPacienteService;

     //la cita ya tiene que estar confirmado con el psicologo y el paciente
     private final UserRepository userRepository;

     public void encabezado(Long citaId){
         //verificar que la cita existe
         var citaEntidad = this.citaService.existCita(citaId);

         //obtener la informacion del paciente y psicologo
         var paciente = this.userService.paciente(citaEntidad.getPaciente().getId());
         var psicologo = this.userService.psicologo(citaEntidad.getMedico().getId());

         var clinica = this.clinicaService.getById();

         //creacion de historia clinica

         HistoriaClinicaEntity  hc = new HistoriaClinicaEntity();

         hc.setNombreInstitucion(clinica.getNombre());

         hc.setServicio(this.servicioRepository.findById(citaEntidad.getId()).get());
         hc.setPsicologoId(psicologo.getUserSimpleDto().id());
         hc.setPsicologo(psicologo.getUserSimpleDto().firstname()+" "+psicologo.getUserSimpleDto().lastname());
         hc.setColegiadoPsicologo( Integer.valueOf(psicologo.getReqDto().ilempleadoResDto().colegiado()));

         var hcEntidad =  this.historialClinicaRepository.save(hc);
         datosIdentificacionPacienteService.registroPaciente(paciente,hc.getId());
     }



}
