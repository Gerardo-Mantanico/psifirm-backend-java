package com.pifirm.web.controller;

import com.pifirm.domain.dto.paciente.InfPacienteReqDto;
import com.pifirm.domain.service.InfoPacienteService;
import com.pifirm.persistence.entity.InformacionPacienteEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/infoPaciente")
public class InfoPaciente {

    @Autowired
    private InfoPacienteService infoPacienteService;


   @PostMapping
    public ResponseEntity<?> register (@Valid @RequestBody InfPacienteReqDto body){
       this.infoPacienteService.add(body);
       return ResponseEntity.ok().build();
   }

   @GetMapping("/{id}")
      public ResponseEntity<InformacionPacienteEntity> getInfoPaciente(@PathVariable Long id) {
       return ResponseEntity.ok(this.infoPacienteService.getInfoPaciente(id));
   }
}
