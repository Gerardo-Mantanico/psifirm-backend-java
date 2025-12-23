package com.pifirm.web.controller.hc;

import com.pifirm.domain.dto.hc.data.AntecedentesPersonalesDto;
import com.pifirm.domain.dto.hc.data.ObjetivosTerapeuticos;
import com.pifirm.domain.service.hc.AntecedentesFamiliaresService;
import com.pifirm.domain.service.hc.ObjetivosService;
import com.pifirm.persistence.entity.AntecedentesPersonalesEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/objetivos")
public class ObjetivosController {

    @Autowired
    private ObjetivosService objetivosService;


    @PostMapping
    public ResponseEntity<?> register (@Valid @RequestBody ObjetivosTerapeuticos body){
        this.objetivosService.save(body);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetivosTerapeuticos> getInfoPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(this.objetivosService.findByIdHc(id) );
    }


}
