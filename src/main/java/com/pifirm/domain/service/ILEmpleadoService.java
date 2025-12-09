package com.pifirm.domain.service;

import com.pifirm.domain.dto.ilempleado.ILEmpleadoReqDto;
import com.pifirm.domain.repository.ILEmpleadoRepository;
import com.pifirm.persistence.entity.ILEmpleadoEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ILEmpleadoService {
    private final ILEmpleadoRepository ilempleadoRepository;
    private final AreaService areaService;
    private final EspecialidadService especialidadService;

    @Transactional
    public  void add(ILEmpleadoEntity entity) {
        this.areaService.getById(entity.getArea().getId());
        this.especialidadService.getById(entity.getEspecialidad().getId());
        this.ilempleadoRepository.save(entity);
    }
}
