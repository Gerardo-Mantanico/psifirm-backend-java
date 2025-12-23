package com.pifirm.web.controller.hc;

import com.pifirm.domain.dto.hc.data.AntecedentesPersonalesDto;
import com.pifirm.domain.dto.paciente.InfPacienteReqDto;
import com.pifirm.domain.service.hc.AntecedentesFamiliaresService;
import com.pifirm.persistence.entity.AntecedentesPersonalesEntity;
import com.pifirm.persistence.entity.InformacionPacienteEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hc/antecedentes-familiares")
public class AntecedentesFamiliaresController {

    @Autowired
    private AntecedentesFamiliaresService antecedentesFamiliaresService;


    @PostMapping
    public ResponseEntity<?> register (@Valid @RequestBody AntecedentesPersonalesDto body){
        this.antecedentesFamiliaresService.save(body);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AntecedentesPersonalesEntity> getInfoPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(this.antecedentesFamiliaresService.findByIdHc(id));
    }


}
