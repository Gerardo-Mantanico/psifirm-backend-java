package com.pifirm.domain.service.inventario.impl;

import com.pifirm.persistence.entity.inventario.CategoriaProductoEntidad;
import com.pifirm.domain.repository.CategoriaProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CategoriaProductoService {

    private final CategoriaProductoRepository categoriaProductoRepository;

    public CategoriaProductoEntidad create(CategoriaProductoEntidad entidad) {
        return categoriaProductoRepository.save(entidad);
    }

    public CategoriaProductoEntidad update(Long id, CategoriaProductoEntidad entidad) {
        CategoriaProductoEntidad existing = categoriaProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría de Producto no encontrada"));
        existing.setNombre(entidad.getNombre());
        return categoriaProductoRepository.save(existing);
    }

    public void delete(Long id) {
        categoriaProductoRepository.deleteById(id);
    }

    public Optional<CategoriaProductoEntidad> findById(Long id) {
        return categoriaProductoRepository.findById(id);
    }

    public List<CategoriaProductoEntidad> findAll() {
        return categoriaProductoRepository.findAll();
    }
}

