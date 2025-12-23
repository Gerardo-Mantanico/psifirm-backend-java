package com.pifirm.web.controller.hc;

import com.pifirm.domain.dto.diagnostico.EvaluacionPsicologicaDto;
import com.pifirm.domain.dto.hc.data.AntecedentesPersonalesDto;
import com.pifirm.domain.repository.diagnostico.EvaluacionPsicologicaRepository;
import com.pifirm.domain.service.hc.AntecedentesFamiliaresService;
import com.pifirm.domain.service.hc.EvaluacionPacienteService;
import com.pifirm.persistence.entity.AntecedentesPersonalesEntity;
import com.pifirm.persistence.entity.diagnostico.EvaluacionPsicologicaEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hc/estado-actual")
public class EstadoActualController {

    @Autowired
    private EvaluacionPacienteService evaluacionPacienteService;


    @PostMapping
    public ResponseEntity<?> register (@Valid @RequestBody EvaluacionPsicologicaEntity body){
        this.evaluacionPacienteService.save(body);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionPsicologicaEntity> getInfoPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(this.evaluacionPacienteService.findByIdHc(id));
    }


}
