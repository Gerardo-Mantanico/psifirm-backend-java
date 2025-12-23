
package com.pifirm.domain.service.hc;

import com.pifirm.domain.dto.hc.HcResDto;
import com.pifirm.domain.repository.*;
import com.pifirm.domain.service.*;
import com.pifirm.persistence.entity.HistoriaClinicaEntity;
import com.pifirm.persistence.mapper.HcMapper;

import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HcService {

     private final HistorialClinicaRepository historialClinicaRepository;
     private final CitaService citaService;
     private final UserUtilsService userUtilsService;
     private final ClinicaService clinicaService;
     private final HcMapper hcMapper;
     private final ILEmpleadoRepository iLEmpleadoRepository;
     private final EntityManager entityManager; // inyectar EntityManager

    @Transactional
    public HcResDto encabezado(Long citaId){
         var citaEntidad = this.citaService.existCita(citaId);

         if(citaEntidad.getHistoriaClinica()!=null){
              var hc = this.historialClinicaRepository.findById(citaEntidad.getHistoriaClinica().getId()).get();
              return hcMapper.toDo(hc);

         } else {
                HistoriaClinicaEntity nuevaHc = new HistoriaClinicaEntity();
                nuevaHc.setNombreInstitucion(this.clinicaService.getById().getNombre());
                nuevaHc.setServicio(citaEntidad.getServicioMedico());
                nuevaHc.setServicioNombre(citaEntidad.getServicioMedico().getNombre());
                nuevaHc.setPsicologoId(userUtilsService.getCurrent().getId());
                nuevaHc.setPsicologo(userUtilsService.getCurrent().getFirstname()+ " "+userUtilsService.getCurrent().getLastname());
                nuevaHc.setColegiadoPsicologo(Integer.valueOf(iLEmpleadoRepository.findByUserId(userUtilsService.getCurrent().getId()).get().getColegiado()));
                var saved = historialClinicaRepository.saveAndFlush(nuevaHc);
                // Forzar recarga desde la BD para obtener el campo generado por el trigger
                entityManager.refresh(saved);
                // asociar la HC a la cita si es necesario
                 this.citaService.addHcToCita(citaId, saved.getId());
                return hcMapper.toDo(saved);
         }

     }

}