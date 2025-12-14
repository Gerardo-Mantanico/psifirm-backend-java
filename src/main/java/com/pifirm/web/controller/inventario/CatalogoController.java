package com.pifirm.web.controller.inventario;

import com.pifirm.persistence.entity.inventario.*;
import com.pifirm.domain.repository.*;
import com.pifirm.domain.service.inventario.impl.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class CatalogoController {

    private final ProveedorRepository proveedorRepo;
    private final UbicacionRepository ubicacionRepo;

    private final CategoriaProductoService categoriaService;
    private final UnidadMedidaService unidadService;
    private final FormaFarmaceuticaService formaService;
    private final PrincipioActivoService principioService;

    public CatalogoController(ProveedorRepository proveedorRepo,
                             UbicacionRepository ubicacionRepo,
                             CategoriaProductoService categoriaService, UnidadMedidaService unidadService,
                             FormaFarmaceuticaService formaService, PrincipioActivoService principioService) {
        this.proveedorRepo = proveedorRepo;
        this.ubicacionRepo = ubicacionRepo;
        this.categoriaService = categoriaService;
        this.unidadService = unidadService;
        this.formaService = formaService;
        this.principioService = principioService;
    }

    // ===================== CATEGORÍAS =====================
    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaProductoEntidad>> getCategorias() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaProductoEntidad> getCategoriaById(@PathVariable Long id) {
        return categoriaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaProductoEntidad> createCategoria(@RequestBody CategoriaProductoEntidad entidad) {
        CategoriaProductoEntidad created = categoriaService.create(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<CategoriaProductoEntidad> updateCategoria(@PathVariable Long id, @RequestBody CategoriaProductoEntidad entidad) {
        CategoriaProductoEntidad updated = categoriaService.update(id, entidad);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ===================== UNIDADES DE MEDIDA =====================
    @GetMapping("/unidades")
    public ResponseEntity<List<UnidadMedidaEntidad>> getUnidades() {
        return ResponseEntity.ok(unidadService.findAll());
    }

    @GetMapping("/unidades/{id}")
    public ResponseEntity<UnidadMedidaEntidad> getUnidadById(@PathVariable Long id) {
        return unidadService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/unidades")
    public ResponseEntity<UnidadMedidaEntidad> createUnidad(@RequestBody UnidadMedidaEntidad entidad) {
        UnidadMedidaEntidad created = unidadService.create(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/unidades/{id}")
    public ResponseEntity<UnidadMedidaEntidad> updateUnidad(@PathVariable Long id, @RequestBody UnidadMedidaEntidad entidad) {
        UnidadMedidaEntidad updated = unidadService.update(id, entidad);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/unidades/{id}")
    public ResponseEntity<Void> deleteUnidad(@PathVariable Long id) {
        unidadService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ===================== FORMAS FARMACÉUTICAS =====================
    @GetMapping("/formas")
    public ResponseEntity<List<FormaFarmaceuticaEntidad>> getFormas() {
        return ResponseEntity.ok(formaService.findAll());
    }

    @GetMapping("/formas/{id}")
    public ResponseEntity<FormaFarmaceuticaEntidad> getFormaById(@PathVariable Long id) {
        return formaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/formas")
    public ResponseEntity<FormaFarmaceuticaEntidad> createForma(@RequestBody FormaFarmaceuticaEntidad entidad) {
        FormaFarmaceuticaEntidad created = formaService.create(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/formas/{id}")
    public ResponseEntity<FormaFarmaceuticaEntidad> updateForma(@PathVariable Long id, @RequestBody FormaFarmaceuticaEntidad entidad) {
        FormaFarmaceuticaEntidad updated = formaService.update(id, entidad);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/formas/{id}")
    public ResponseEntity<Void> deleteForma(@PathVariable Long id) {
        formaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ===================== PRINCIPIOS ACTIVOS =====================
    @GetMapping("/principios")
    public ResponseEntity<List<PrincipioActivoEntidad>> getPrincipios() {
        return ResponseEntity.ok(principioService.findAll());
    }

    @GetMapping("/principios/{id}")
    public ResponseEntity<PrincipioActivoEntidad> getPrincipioById(@PathVariable Long id) {
        return principioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/principios")
    public ResponseEntity<PrincipioActivoEntidad> createPrincipio(@RequestBody PrincipioActivoEntidad entidad) {
        PrincipioActivoEntidad created = principioService.create(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/principios/{id}")
    public ResponseEntity<PrincipioActivoEntidad> updatePrincipio(@PathVariable Long id, @RequestBody PrincipioActivoEntidad entidad) {
        PrincipioActivoEntidad updated = principioService.update(id, entidad);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/principios/{id}")
    public ResponseEntity<Void> deletePrincipio(@PathVariable Long id) {
        principioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ===================== OTROS CATÁLOGOS (sin CRUD completo) =====================
    @GetMapping("/proveedores")
    public ResponseEntity<List<ProveedorEntidad>> proveedores() {
        return ResponseEntity.ok(proveedorRepo.findAll());
    }

    @GetMapping("/ubicaciones")
    public ResponseEntity<List<UbicacionEntidad>> ubicaciones() {
        return ResponseEntity.ok(ubicacionRepo.findAll());
    }
}

