package com.pifirm.domain.service;

import com.pifirm.domain.dto.servicio.ServicioDto;
import com.pifirm.domain.dto.servicio.ServicioResDto;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.ServicioRepository;
import com.pifirm.persistence.entity.ServicioEntity;
import com.pifirm.persistence.mapper.ServicioMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServicioService {
    private final ServicioRepository servicioRepository;
    private final ServicioMapper servicioMapper;

    public ServicioService(ServicioRepository servicioRepository, ServicioMapper servicioMapper) {
        this.servicioRepository = servicioRepository;
        this.servicioMapper = servicioMapper;
    }

    @Transactional
    public ServicioResDto add(ServicioDto dto) {
        ServicioEntity entity = servicioMapper.toEntity(dto);
        ServicioEntity saved = servicioRepository.save(entity);
        return servicioMapper.toDto(saved);
    }

    @Transactional
    public ServicioResDto update(Long id, ServicioDto dto) {
        ServicioEntity entity = servicioRepository.findById(id)
                .orElseThrow(() -> new GeneralException("servicio-not-found", "Servicio no encontrado"));

        if (dto.nombre() != null) entity.setNombre(dto.nombre());
        if (dto.descripcion() != null) entity.setDescripcion(dto.descripcion());
        if (dto.precio() != null) entity.setPrecio(dto.precio());

        ServicioEntity updated = servicioRepository.save(entity);
        return servicioMapper.toDto(updated);
    }

    @Transactional
    public ServicioResDto getById(Long id) {
        ServicioEntity entity = servicioRepository.findById(id)
                .orElseThrow(() -> new GeneralException("servicio-not-found", "Servicio no encontrado"));
        return servicioMapper.toDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        ServicioEntity entity = servicioRepository.findById(id)
                .orElseThrow(() -> new GeneralException("servicio-not-found", "Servicio no encontrado"));
        servicioRepository.deleteById(id);
    }

    @Transactional
    public Page<ServicioResDto> all(Pageable pageable) {
        return servicioRepository.findAll(pageable).map(servicioMapper::toDto);
    }
}

