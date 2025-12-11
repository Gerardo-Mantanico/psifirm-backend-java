package com.pifirm.domain.service;

import com.pifirm.domain.dto.ilempleado.ILEmpleadoReqDto;
import com.pifirm.domain.dto.ilempleado.ILEmpleadoResDto;
import com.pifirm.domain.repository.ILEmpleadoRepository;
import com.pifirm.persistence.entity.ILEmpleadoEntity;
import com.pifirm.persistence.mapper.ILEmpleadoMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ILEmpleadoService {
    private final ILEmpleadoRepository ilempleadoRepository;
    private final AreaService areaService;
    private final EspecialidadService especialidadService;
    private final ILEmpleadoMapper ilempleadoMapper;

    @Transactional
    public  void add(ILEmpleadoEntity entity) {
        this.areaService.getById(entity.getArea().getId());
        this.especialidadService.getById(entity.getEspecialidad().getId());
        this.ilempleadoRepository.save(entity);
    }

    @Transactional
    public void update(ILEmpleadoReqDto dto, Long id) {
        ILEmpleadoEntity existingEntity = this.ilempleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ILEmpleadoEntity not found with id: " + id));

        this.areaService.getById(dto.areaId());
        this.especialidadService.getById(dto.especialidadId());

        existingEntity.setColegiado(dto.colegiado());
        this.ilempleadoRepository.save(existingEntity);
    }

    @Transactional
    public ILEmpleadoResDto getByUserId(Long userId) {
        return  ilempleadoMapper.toDO( this.ilempleadoRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("ILEmpleadoEntity not found with userId: " + userId)));
    }

}
