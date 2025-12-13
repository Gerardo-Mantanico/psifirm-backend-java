package com.pifirm.domain.service.inventario.impl;

import com.pifirm.persistence.entity.inventario.PrincipioActivoEntidad;
import com.pifirm.domain.repository.PrincipioActivoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PrincipioActivoService {

    private final PrincipioActivoRepository principioActivoRepository;

    public PrincipioActivoEntidad create(PrincipioActivoEntidad entidad) {
        return principioActivoRepository.save(entidad);
    }

    public PrincipioActivoEntidad update(Long id, PrincipioActivoEntidad entidad) {
        PrincipioActivoEntidad existing = principioActivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Principio Activo no encontrado"));
        existing.setNombre(entidad.getNombre());
        return principioActivoRepository.save(existing);
    }

    public void delete(Long id) {
        principioActivoRepository.deleteById(id);
    }

    public Optional<PrincipioActivoEntidad> findById(Long id) {
        return principioActivoRepository.findById(id);
    }

    public List<PrincipioActivoEntidad> findAll() {
        return principioActivoRepository.findAll();
    }
}

