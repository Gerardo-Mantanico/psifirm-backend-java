package com.pifirm.web.controller;

import com.pifirm.domain.dto.nomina.NominaDTO;
import com.pifirm.domain.dto.nomina.NominaDetalleDTO;
import com.pifirm.domain.dto.nomina.NominaDetalleReqDTO;
import com.pifirm.domain.dto.nomina.NominaSimpleDTO;
import com.pifirm.domain.service.NominaService;
import jakarta.validation.Valid;
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
    public ResponseEntity<NominaDTO> create(@RequestBody NominaSimpleDTO payload) {
        return ResponseEntity.ok(nominaService.create(payload));
    }


    @GetMapping("/{id}")
    public ResponseEntity<NominaDTO> nomina(@PathVariable String id) {
        return ResponseEntity.ok(nominaService.search(id));
    }


    @PutMapping("/{id}")
    public NominaDTO update(@PathVariable Long id, @RequestBody NominaSimpleDTO payload) {
        return nominaService.update(id, payload);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        nominaService.delete(id);
        return ResponseEntity.noContent().build();
    }


    // Sub-CRUDs
    @PostMapping("/bonos")
    public NominaDetalleDTO addBono(@Valid @RequestBody NominaDetalleReqDTO bono) {
        return nominaService.addBonoFromDto( bono);
    }

    @DeleteMapping("/bonos/{id}")
    public ResponseEntity<Void> deleteBono(@PathVariable Long id) {
        nominaService.deleteBono(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/retenciones")
    public NominaDetalleDTO addRetencion(@Valid @RequestBody NominaDetalleReqDTO ret) {
        return nominaService.addRetencionFromDto(ret);
    }

    @DeleteMapping("/retenciones/{id}")
    public ResponseEntity<Void> deleteRetencion(@PathVariable Long id) {
        nominaService.deleteRetencion(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/descuentos")
    public NominaDetalleDTO addDescuento(@Valid @RequestBody NominaDetalleReqDTO des) {
        return nominaService.addDescuentoFromDto(des);
    }

    @DeleteMapping("/descuentos/{id}")
    public ResponseEntity<Void> deleteDescuento(@PathVariable Long id) {
        nominaService.deleteDescuento(id);
        return ResponseEntity.noContent().build();
    }

}
