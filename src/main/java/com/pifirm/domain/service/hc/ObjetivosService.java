package com.pifirm.domain.service.hc;

import com.pifirm.domain.dto.hc.data.AntecedentesPersonalesDto;
import com.pifirm.domain.dto.hc.data.ObjetivosTerapeuticos;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.AntecedentesPersonalesRepository;
import com.pifirm.domain.repository.planintervencion.ConfiguracionTratamientoRepository;
import com.pifirm.domain.repository.planintervencion.EnfoqueRepository;
import com.pifirm.domain.repository.planintervencion.FrecuenciaRepository;
import com.pifirm.domain.repository.planintervencion.ModalidadRepository;
import com.pifirm.persistence.entity.AntecedentesPersonalesEntity;
import com.pifirm.persistence.entity.planintervencion.ConfiguracionTratamientoEntity;
import com.pifirm.persistence.entity.planintervencion.EnfoqueEntity;
import com.pifirm.persistence.entity.planintervencion.ModalidadEntity;
import com.pifirm.persistence.mapper.AntecedentesPersonalesMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ObjetivosService {

  private final ConfiguracionTratamientoRepository configuracionTratamientoRepository;
  private final FrecuenciaRepository frecuenciaRepository;
  private final ModalidadRepository modalidadRepository;
  private final EnfoqueRepository enfoqueRepository;



    public ObjetivosTerapeuticos save( ObjetivosTerapeuticos entidad) {

        ConfiguracionTratamientoEntity  nuevo  =  new  ConfiguracionTratamientoEntity();
        nuevo.setHcId(entidad.hcId());
        nuevo.setObjetivoCortoplazo(entidad.objetivoCortoplazo());
        nuevo.setObjetivoMedioplazo(entidad.objetivoMedioplazo());
        nuevo.setObjetivoLargoplazo(entidad.objetivoLargoplazo());
        nuevo.setFrecuenciaId(entidad.frecuencia());
        nuevo.setSesionesPorSemana(entidad.sesionesPorSemana());
        nuevo.setDuracionEstimada(entidad.duracionEstimada());
        nuevo.setCostosPorSession(entidad.costoPorSesion());

        var objetivos =  configuracionTratamientoRepository.save(nuevo);

        for(Long m : entidad.modalidad()){
            ModalidadEntity nn = new ModalidadEntity();
            nn.setConfiguracionTratamiento(objetivos.getId());
            nn.setModalidadIntervencionId(m);
            this.modalidadRepository.save(nn);
        }

        for (Long e : entidad.enfoqueTerapeutico()){
            EnfoqueEntity ee = new EnfoqueEntity();
            ee.setConfiguracionTratamiento(objetivos.getId());
            ee.setEnfoqueTerapeuticoId(e);
            this.enfoqueRepository.save(ee);
        }

        return entidad;
    }

//   public ObjetivosTerapeuticos findByIdHc(Long hc) {
//         var objetivos =    this.configuracionTratamientoRepository.findByHcId(hc).orElseThrow(()-> new GeneralException("error","no existen registros"));
//         var modalidades = this.modalidadRepository.findByConfiguracionTratamiento(objetivos.getId());
//         var enfoque = this.enfoqueRepository.findByConfiguracionTratamiento(objetivos.getId());
//
//        return null;
//    }


   public ObjetivosTerapeuticos findByIdHc(Long hc) {
       var objetivos = this.configuracionTratamientoRepository.findByHcId(hc)
           .orElseThrow(() -> new GeneralException("error", "no existen registros"));

       var modalidades = this.modalidadRepository.findByConfiguracionTratamiento(objetivos.getId());
       var enfoques = this.enfoqueRepository.findByConfiguracionTratamiento(objetivos.getId());

       java.util.List<Long> modalidadIds = modalidades.stream()
           .map(ModalidadEntity::getModalidadIntervencionId)
           .collect(java.util.stream.Collectors.toList());

       java.util.List<Long> enfoqueIds = enfoques.stream()
           .map(EnfoqueEntity::getEnfoqueTerapeuticoId)
           .collect(java.util.stream.Collectors.toList());

       return new ObjetivosTerapeuticos(
           objetivos.getHcId(),
           objetivos.getObjetivoCortoplazo(),
           objetivos.getObjetivoMedioplazo(),
           objetivos.getObjetivoLargoplazo(),
           modalidadIds,
           enfoqueIds,
           objetivos.getFrecuenciaId(),
           objetivos.getSesionesPorSemana(),
           objetivos.getDuracionEstimada(),
           objetivos.getCostosPorSession()
       );
   }
}
