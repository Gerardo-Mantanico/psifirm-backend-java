package com.pifirm.web.controller;


import com.pifirm.domain.dto.especialidad.EspecialidadDto;
import com.pifirm.domain.dto.especialidad.EspecialidadResDto;
import com.pifirm.domain.service.EspecialidadService;
import com.pifirm.web.response.GenericResponse;
import com.pifirm.web.response.PageResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/especialidad")
@AllArgsConstructor
public class EspecialidadController {
    private final EspecialidadService especialidadService;


    @GetMapping
    public PageResponse<EspecialidadResDto> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<EspecialidadResDto> pageResult = especialidadService.all(pageable);
        return PageResponse.fromPage(pageResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadResDto> getById(@PathVariable Long id) {
        EspecialidadResDto dto = especialidadService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<EspecialidadResDto> create(@RequestBody @Valid EspecialidadDto EspecialidadDto) {
        EspecialidadResDto created = especialidadService.add(EspecialidadDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<EspecialidadResDto> update(@PathVariable Long id, @RequestBody @Valid EspecialidadDto EspecialidadDto) {
        EspecialidadResDto updated = especialidadService.update(id, EspecialidadDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GenericResponse> delete(@PathVariable Long id) {
        especialidadService.delete(id);
        return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.value(), "Eliminado correctamente"));
    }
}

