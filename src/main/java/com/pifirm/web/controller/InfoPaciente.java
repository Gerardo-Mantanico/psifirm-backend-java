package com.pifirm.web.controller;

import com.pifirm.domain.dto.paciente.InfPacienteReqDto;
import com.pifirm.domain.service.InfoPacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/infoPaciente")
public class InfoPaciente {

    @Autowired
    private InfoPacienteService infoPacienteService;

   @GetMapping("/me")
    public ResponseEntity<InfPacienteReqDto> info(){
       return ResponseEntity.ok(this.infoPacienteService.infoMe());
   }

   @PostMapping
    public ResponseEntity<?> register (@Valid @RequestBody InfPacienteReqDto body){
       this.infoPacienteService.add(body);
       return ResponseEntity.ok().build();
   }

   @PutMapping
    public ResponseEntity<?> update (@Valid @RequestBody InfPacienteReqDto body){
       this.infoPacienteService.update(body);
       return ResponseEntity.ok().build();
   }

}
