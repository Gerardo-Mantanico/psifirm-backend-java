package com.pifirm.domain.service.inventario.impl;

import com.pifirm.persistence.entity.inventario.FormaFarmaceuticaEntidad;
import com.pifirm.domain.repository.FormaFarmaceuticaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class FormaFarmaceuticaService {

    private final FormaFarmaceuticaRepository formaFarmaceuticaRepository;

    public FormaFarmaceuticaEntidad create(FormaFarmaceuticaEntidad entidad) {
        return formaFarmaceuticaRepository.save(entidad);
    }

    public FormaFarmaceuticaEntidad update(Long id, FormaFarmaceuticaEntidad entidad) {
        FormaFarmaceuticaEntidad existing = formaFarmaceuticaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forma Farmacéutica no encontrada"));
        existing.setNombre(entidad.getNombre());
        return formaFarmaceuticaRepository.save(existing);
    }

    public void delete(Long id) {
        formaFarmaceuticaRepository.deleteById(id);
    }

    public Optional<FormaFarmaceuticaEntidad> findById(Long id) {
        return formaFarmaceuticaRepository.findById(id);
    }

    public List<FormaFarmaceuticaEntidad> findAll() {
        return formaFarmaceuticaRepository.findAll();
    }
}

