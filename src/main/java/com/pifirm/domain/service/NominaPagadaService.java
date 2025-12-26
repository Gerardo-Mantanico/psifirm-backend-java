
package com.pifirm.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pifirm.domain.repository.NominaPagadaRepository;
import com.pifirm.domain.repository.NominaRepository;
import com.pifirm.persistence.entity.NominaPagadaEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NominaPagadaService {
    private final NominaPagadaRepository nominaPagadaRepository;
    private final NominaRepository nominaRepository;
    private final NominaService nominaService;

    public void create(String data){
       var nomina = this.nominaService.search(data);

       if (nomina == null) {
           throw new IllegalArgumentException("No se encontró la nómina para los datos proporcionados");
       }

        NominaPagadaEntity entity = new NominaPagadaEntity();
         entity.setPagado(true);
         entity.setNominaId(nomina.id());
         entity.setFechaPago(LocalDateTime.now());
         var user = nomina.user();
         entity.setUserId(user != null ? user.id() : null);

         ObjectMapper mapper = new ObjectMapper();
         // registrar módulo para Java 8 date/time y serializar fechas como ISO strings
         mapper.registerModule(new JavaTimeModule());
         mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

         String json;
         try {
             json = mapper.writeValueAsString(nomina);
         } catch (JsonProcessingException e) {
             throw new IllegalStateException("Error al convertir la nómina a JSON", e);
         }

         entity.setDataJson(json);
      this.nominaPagadaRepository.save(entity);
    }

    public List<NominaPagadaEntity> getAll(){
       return this.nominaPagadaRepository.findAll();
    }
}