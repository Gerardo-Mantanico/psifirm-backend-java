package com.pifirm.web.controller;

import com.pifirm.domain.dto.servicio.ServicioDto;
import com.pifirm.domain.dto.servicio.ServicioResDto;
import com.pifirm.domain.service.ServicioService;
import com.pifirm.web.response.PageResponse;
import com.pifirm.web.response.GenericResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servicios")
public class ServicioController {
    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    public PageResponse<ServicioResDto> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ServicioResDto> pageResult = servicioService.all(pageable);
        return PageResponse.fromPage(pageResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioResDto> getById(@PathVariable Long id) {
        ServicioResDto dto = servicioService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ServicioResDto> create(@RequestBody @Valid ServicioDto servicioDto) {
        ServicioResDto created = servicioService.add(servicioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ServicioResDto> update(@PathVariable Long id, @RequestBody @Valid ServicioDto servicioDto) {
        ServicioResDto updated = servicioService.update(id, servicioDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GenericResponse> delete(@PathVariable Long id) {
        servicioService.delete(id);
        return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.value(), "Eliminado correctamente"));
    }
}

