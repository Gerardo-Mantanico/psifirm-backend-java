package com.pifirm.domain.service.inventario.impl;

import com.pifirm.persistence.entity.inventario.UnidadMedidaEntidad;
import com.pifirm.domain.repository.UnidadMedidaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UnidadMedidaService {

    private final UnidadMedidaRepository unidadMedidaRepository;

    public UnidadMedidaEntidad create(UnidadMedidaEntidad entidad) {
        return unidadMedidaRepository.save(entidad);
    }

    public UnidadMedidaEntidad update(Long id, UnidadMedidaEntidad entidad) {
        UnidadMedidaEntidad existing = unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidad de Medida no encontrada"));
        existing.setNombre(entidad.getNombre());
        existing.setSimbolo(entidad.getSimbolo());
        return unidadMedidaRepository.save(existing);
    }

    public void delete(Long id) {
        unidadMedidaRepository.deleteById(id);
    }

    public Optional<UnidadMedidaEntidad> findById(Long id) {
        return unidadMedidaRepository.findById(id);
    }

    public List<UnidadMedidaEntidad> findAll() {
        return unidadMedidaRepository.findAll();
    }
}

