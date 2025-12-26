package com.pifirm.web.controller;

import com.pifirm.domain.dto.pagoSesion.PagoSesionDto;
import com.pifirm.domain.repository.PagoSesionRepository;
import com.pifirm.domain.service.PagoSesionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pago-sesiones")
public class PagoSesionesController {
    @Autowired
    private  PagoSesionService pagoSesionService;

    @GetMapping
    public ResponseEntity<?> getAllPagoSesiones(@RequestParam(value = "estado", required = false) String estado) {
        return ResponseEntity.ok(pagoSesionService.getAllPagoSesiones(estado));
    }

     @PostMapping
        public ResponseEntity<?> createPagoSesion(@Valid @RequestBody PagoSesionDto dto) {
            this.pagoSesionService.updateStatusPagoSesion(dto);
            return ResponseEntity.ok().build();
     }

}
