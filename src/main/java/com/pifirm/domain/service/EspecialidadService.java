package com.pifirm.domain.service;


import com.pifirm.domain.dto.especialidad.EspecialidadDto;
import com.pifirm.domain.dto.especialidad.EspecialidadResDto;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.EspecialidadRepository;
import com.pifirm.persistence.entity.EspecialidadEntity;
import com.pifirm.persistence.mapper.EspecialidadMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EspecialidadService {
    private final EspecialidadRepository especialidadRepository;
    private final EspecialidadMapper especialidadMapper;


    @Transactional
    public EspecialidadResDto add(EspecialidadDto dto) {
        this.especialidadRepository.findByNombre(dto.nombre()).ifPresent(e -> {
            throw new GeneralException("Especialidad-exists", "Ya existe una especialidad con ese nombre");
        });
        EspecialidadEntity entity = especialidadMapper.toEntity(dto);
        EspecialidadEntity saved = especialidadRepository.save(entity);
        return especialidadMapper.toDto(saved);
    }

    @Transactional
    public EspecialidadResDto update(Long id, EspecialidadDto dto) {
        EspecialidadEntity entity = especialidadRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Especialidad-not-found", "Especialidad no encontrado"));

        var entityb = this.especialidadRepository.findByNombre(dto.nombre());
        if (entityb.isPresent() && !entityb.get().getId() .equals(id)) {
            throw new GeneralException("Especialidad-exists", "Ya existe una especialidad con ese nombre");
        }


        if (dto.nombre() != null) entity.setNombre(dto.nombre());
        if (dto.descripcion() != null) entity.setDescripcion(dto.descripcion());

        EspecialidadEntity updated = especialidadRepository.save(entity);
        return especialidadMapper.toDto(updated);
    }

    @Transactional
    public EspecialidadResDto getById(Long id) {
        EspecialidadEntity entity = especialidadRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Especialidad-not-found", "Especialidad no encontrado"));
        return especialidadMapper.toDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        EspecialidadEntity entity = especialidadRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Especialidad-not-found", "Especialidad no encontrado"));
        especialidadRepository.deleteById(id);
    }

    @Transactional
    public Page<EspecialidadResDto> all(Pageable pageable) {
        return especialidadRepository.findAll(pageable).map(especialidadMapper::toDto);
    }
}

