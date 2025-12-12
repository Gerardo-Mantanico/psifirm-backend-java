package com.pifirm.web.controller;

import com.pifirm.domain.dto.nomina.NominaDTO;
import com.pifirm.domain.dto.nomina.NominaDetalleDTO;
import com.pifirm.domain.dto.nomina.NominaDetalleReqDTO;
import com.pifirm.domain.dto.nomina.NominaSimpleDTO;
import com.pifirm.domain.service.NominaService;
import com.pifirm.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nomina")
public class NominaController {
    @Autowired
    private  NominaService nominaService;

    // CRUD Nomina

    @PostMapping
    public ResponseEntity<NominaEntity> create(@RequestBody NominaSimpleDTO payload) {
        return ResponseEntity.ok(nominaService.create(payload));
    }


    @GetMapping
    public List<NominaDTO> list() {
        return nominaService.list();
    }


    @PutMapping("/{id}")
    public NominaEntity update(@PathVariable Long id, @RequestBody NominaSimpleDTO payload) {
        return nominaService.update(id, payload);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        nominaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Sub-CRUDs
    @PostMapping("/{nominaId}/bonos")
    public NominaDetalleDTO addBono(@PathVariable Long nominaId, @RequestBody NominaDetalleReqDTO bono) {
        return nominaService.addBonoFromDto(nominaId, bono);
    }

    @DeleteMapping("/bonos/{id}")
    public ResponseEntity<Void> deleteBono(@PathVariable Long id) {
        nominaService.deleteBono(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{nominaId}/retenciones")
    public NominaDetalleDTO addRetencion(@PathVariable Long nominaId, @RequestBody NominaDetalleReqDTO ret) {
        return nominaService.addRetencionFromDto(nominaId, ret);
    }

    @DeleteMapping("/retenciones/{id}")
    public ResponseEntity<Void> deleteRetencion(@PathVariable Long id) {
        nominaService.deleteRetencion(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{nominaId}/descuentos")
    public NominaDetalleDTO addDescuento(@PathVariable Long nominaId, @RequestBody NominaDetalleReqDTO des) {
        return nominaService.addDescuentoFromDto(nominaId, des);
    }

    @DeleteMapping("/descuentos/{id}")
    public ResponseEntity<Void> deleteDescuento(@PathVariable Long id) {
        nominaService.deleteDescuento(id);
        return ResponseEntity.noContent().build();
    }

}
