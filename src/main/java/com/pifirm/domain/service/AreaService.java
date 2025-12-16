package com.pifirm.domain.service;

import com.pifirm.domain.dto.area.AreaDto;
import com.pifirm.domain.dto.area.AreaResDto;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.AreaRepository;
import com.pifirm.domain.repository.ServicioRepository;
import com.pifirm.persistence.entity.AreaEntity;
import com.pifirm.persistence.entity.ServicioEntity;
import com.pifirm.persistence.mapper.AreaMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AreaService {
    private final AreaRepository areaRepository;
    private final AreaMapper areaMapper;
    private final ServicioRepository servicioRepository;


    @Transactional
    public AreaResDto add(AreaDto dto) {
        AreaEntity entity = areaMapper.toEntity(dto);
        
        // Establecer la relación con el servicio
        ServicioEntity servicio = servicioRepository.findById(dto.servicioId())
                .orElseThrow(() -> new GeneralException("servicio-not-found", "Servicio no encontrado"));
        entity.setServicio(servicio);
        
        AreaEntity saved = areaRepository.save(entity);
        return areaMapper.toDto(saved);
    }

    @Transactional
    public AreaResDto update(Long id, AreaDto dto) {
        AreaEntity entity = areaRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Area-not-found", "Area no encontrado"));

        if (dto.nombre() != null) entity.setNombre(dto.nombre());
        if (dto.descripcion() != null) entity.setDescripcion(dto.descripcion());
        
        // Actualizar la relación con el servicio si se proporciona
        if (dto.servicioId() != null) {
            ServicioEntity servicio = servicioRepository.findById(dto.servicioId())
                    .orElseThrow(() -> new GeneralException("servicio-not-found", "Servicio no encontrado"));
            entity.setServicio(servicio);
        }

        AreaEntity updated = areaRepository.save(entity);
        return areaMapper.toDto(updated);
    }

    @Transactional
    public AreaResDto getById(Long id) {
        AreaEntity entity = areaRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Area-not-found", "Area no encontrado"));
        return areaMapper.toDto(entity);
    }


    @Transactional
    public AreaEntity getByIdEntidad(Long id) {
        return areaRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Area-not-found", "Area no encontrado"));

    }

    @Transactional
    public void delete(Long id) {
        if (!areaRepository.existsById(id)) {
            throw new GeneralException("Area-not-found", "Area no encontrado");
        }
        areaRepository.deleteById(id);
    }

    @Transactional
    public Page<AreaResDto> all(Pageable pageable) {
        return areaRepository.findAll(pageable).map(areaMapper::toDto);
    }
}

