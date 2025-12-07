package com.pifirm.web.controller;

import com.pifirm.domain.dto.area.AreaDto;
import com.pifirm.domain.dto.area.AreaResDto;
import com.pifirm.domain.service.AreaService;
import com.pifirm.web.response.GenericResponse;
import com.pifirm.web.response.PageResponse;
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
@RequestMapping("/areas")
public class AreaController {
    private final AreaService AreaService;

    public AreaController(AreaService AreaService) {
        this.AreaService = AreaService;
    }

    @GetMapping
    public PageResponse<AreaResDto> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<AreaResDto> pageResult = AreaService.all(pageable);
        return PageResponse.fromPage(pageResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaResDto> getById(@PathVariable Long id) {
        AreaResDto dto = AreaService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AreaResDto> create(@RequestBody @Valid AreaDto AreaDto) {
        AreaResDto created = AreaService.add(AreaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AreaResDto> update(@PathVariable Long id, @RequestBody @Valid AreaDto AreaDto) {
        AreaResDto updated = AreaService.update(id, AreaDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GenericResponse> delete(@PathVariable Long id) {
        AreaService.delete(id);
        return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.value(), "Eliminado correctamente"));
    }
}

