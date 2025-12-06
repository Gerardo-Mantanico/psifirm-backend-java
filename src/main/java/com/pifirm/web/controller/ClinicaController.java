package com.pifirm.web.controller;

import com.pifirm.domain.dto.clinica.ClinicaDto;
import com.pifirm.domain.service.ClinicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/clinica")
@RestController
public class ClinicaController {
    private final ClinicaService clinicaService;

    public ClinicaController(ClinicaService clinicaService) {
        this.clinicaService = clinicaService;
    }

    @GetMapping()
    public ResponseEntity<ClinicaDto> getById() {
        ClinicaDto dto = this.clinicaService.getById();
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping()
    public ResponseEntity<ClinicaDto> update( @RequestBody ClinicaDto clinicaDto) {
        ClinicaDto updated = this.clinicaService.update( clinicaDto);
        return ResponseEntity.ok(updated);
    }
}
