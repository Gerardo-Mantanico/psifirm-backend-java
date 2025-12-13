package com.pifirm.web.controller.inventario;

import com.pifirm.domain.dto.inventario.LoteDTO;
import com.pifirm.domain.service.inventario.impl.LoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario/lotes")
public class LoteController {

    private final LoteService loteService;

    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @PostMapping
    public ResponseEntity<LoteDTO> create(@RequestBody LoteDTO dto) {
        return ResponseEntity.ok(loteService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoteDTO> update(@PathVariable Long id, @RequestBody LoteDTO dto) {
        return ResponseEntity.ok(loteService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteDTO> getById(@PathVariable Long id) {
        LoteDTO d = loteService.findById(id);
        if (d == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(d);
    }

    @GetMapping
    public ResponseEntity<List<LoteDTO>> list() {
        return ResponseEntity.ok(loteService.findAll());
    }
}
