package com.pifirm.web.controller;

import com.pifirm.domain.dto.cita.CitaDto;
import com.pifirm.domain.dto.cita.CitaResDto;
import com.pifirm.domain.service.CitaService;
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
@RequestMapping("/citas")
public class CitaController {
    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public PageResponse<CitaResDto> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CitaResDto> pageResult = citaService.all(pageable);
        return PageResponse.fromPage(pageResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaResDto> getById(@PathVariable Long id) {
        CitaResDto dto = citaService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CLIENTE')")

    public ResponseEntity<CitaResDto> create(@RequestBody @Valid CitaDto dto) {
        CitaResDto created = citaService.add(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CitaResDto> update(@PathVariable Long id, @RequestBody @Valid CitaDto dto) {
        CitaResDto updated = citaService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GenericResponse> delete(@PathVariable Long id) {
        citaService.delete(id);
        return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.value(), "Eliminado correctamente"));
    }

//    @PutMapping("/{id}")
//   // @PreAuthorize("hasAuthority('ADMIN')")
//    public ResponseEntity<CitaResDto> asignarPsicologo(@PathVariable Long id, @RequestBody @Valid CitaDto dto) {
//        CitaResDto updated = citaService.asignarPsicologo(id, id);
//        return ResponseEntity.ok(updated);
//    }

}

