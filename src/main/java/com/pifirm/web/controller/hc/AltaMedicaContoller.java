package com.pifirm.web.controller.hc;

import com.pifirm.domain.service.hc.AltaTerapeuticaService;
import com.pifirm.persistence.entity.notasProgreso.AltaTerapeuticaEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alta-medica")
public class AltaMedicaContoller {
    @Autowired
    private AltaTerapeuticaService altaTerapeuticaService;

    @PostMapping
    public ResponseEntity<?>  crearAltaMedica(@Valid @RequestBody AltaTerapeuticaEntity body) {
        this.altaTerapeuticaService.registrar(body);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AltaTerapeuticaEntity> obtenerAltaMedica(@PathVariable Long id) {
        return ResponseEntity.ok(this.altaTerapeuticaService.obtenerPorId(id));
    }
}
