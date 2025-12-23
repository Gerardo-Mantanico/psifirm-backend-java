package com.pifirm.web.controller.hc;

import com.pifirm.domain.dto.hc.data.AntecedentesPersonalesDto;
import com.pifirm.domain.service.hc.AntecedentesFamiliaresService;
import com.pifirm.domain.service.hc.EvaluacionService;
import com.pifirm.persistence.entity.AntecedentesPersonalesEntity;
import com.pifirm.persistence.entity.notasProgreso.EvaluacionesPeriodicasEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluacion")
public class NuevaEvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;


    @PostMapping
    public ResponseEntity<?> register (@Valid @RequestBody EvaluacionesPeriodicasEntity body){
        this.evaluacionService.save(body);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<EvaluacionesPeriodicasEntity>> getInfoPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(this.evaluacionService.findByIdHc(id));
    }
    @DeleteMapping
    public  ResponseEntity<?> delete (@RequestParam Long id){
        this.evaluacionService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
