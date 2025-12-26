package com.pifirm.web.controller;

import com.pifirm.domain.dto.nomina.NominaPagar;
import com.pifirm.domain.service.NominaPagadaService;
import com.pifirm.persistence.entity.NominaPagadaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nomina-pagada")
public class NominaPagadaController {
    @Autowired
    private NominaPagadaService nominaPagadaService;

    @GetMapping
    public ResponseEntity<List<NominaPagadaEntity>> getAll(){
        return ResponseEntity.ok(this.nominaPagadaService.getAll());
    }

   @PostMapping
    public ResponseEntity<Void> create(@RequestBody NominaPagar email){
        System.out.println("el emial es " +email);
        this.nominaPagadaService.create(email.email());
        return ResponseEntity.ok().build();
    }
}
