package com.pifirm.web.controller.inventario;

import com.pifirm.domain.dto.inventario.MovimientoInventarioDTO;
import com.pifirm.domain.service.inventario.impl.MovimientoInventarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario/movimientos")
public class MovimientoInventarioController {

    private final MovimientoInventarioService movimientoService;

    public MovimientoInventarioController(MovimientoInventarioService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<MovimientoInventarioDTO> create(@RequestBody MovimientoInventarioDTO dto) {
        return ResponseEntity.ok(movimientoService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoInventarioDTO> getById(@PathVariable Long id) {
        MovimientoInventarioDTO d = movimientoService.findById(id);
        if (d == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(d);
    }

    @GetMapping("/lote/{loteId}")
    public ResponseEntity<List<MovimientoInventarioDTO>> getByLote(@PathVariable Long loteId) {
        return ResponseEntity.ok(movimientoService.findByLoteId(loteId));
    }
}
