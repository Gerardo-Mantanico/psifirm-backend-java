package com.pifirm.web.controller.inventario;

import com.pifirm.domain.dto.inventario.MedicamentoDTO;
import com.pifirm.domain.dto.inventario.MedicamentoDetalleDTO;
import com.pifirm.domain.service.inventario.impl.MedicamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @PostMapping
    public ResponseEntity<MedicamentoDTO> create(@RequestBody MedicamentoDTO dto) {
        return ResponseEntity.ok(medicamentoService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoDTO> update(@PathVariable Long id, @RequestBody MedicamentoDTO dto) {
        return ResponseEntity.ok(medicamentoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDTO> getById(@PathVariable Long id) {
        MedicamentoDTO d = medicamentoService.findById(id);
        if (d == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(d);
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoDTO>> list() {
        return ResponseEntity.ok(medicamentoService.findAll());
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<MedicamentoDetalleDTO> getByIdDetalle(@PathVariable Long id) {
        MedicamentoDetalleDTO d = medicamentoService.findByIdDetalle(id);
        if (d == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(d);
    }

    @GetMapping("/detalle")
    public ResponseEntity<List<MedicamentoDetalleDTO>> listDetalle() {
        return ResponseEntity.ok(medicamentoService.findAllDetalle());
    }
}
