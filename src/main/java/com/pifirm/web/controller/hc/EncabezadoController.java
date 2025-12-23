package com.pifirm.web.controller.hc;

import com.pifirm.domain.dto.hc.HcResDto;
import com.pifirm.domain.service.hc.HcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hc/encabezado")
public class EncabezadoController {
    @Autowired
    private HcService hcService;

    @GetMapping("/{id}")
    public ResponseEntity<HcResDto> encabezado(@PathVariable Long id){
        return ResponseEntity.ok(this.hcService.encabezado(id));
    }
}
