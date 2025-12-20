package com.pifirm.domain.service;

import com.pifirm.domain.dto.clinica.ClinicaDto;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.ClinicaRepository;
import com.pifirm.persistence.entity.CitaMedicaEntity;
import com.pifirm.persistence.entity.ClinicaEntity;
import com.pifirm.persistence.mapper.ClinicaMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ClinicaService {
    private final ClinicaRepository clinicaRepository;
    private final ClinicaMapper clinicaMapper;


    @Transactional(readOnly = true)
    public ClinicaDto getById() {
        ClinicaEntity entity = this.clinicaRepository.findById(1L)
                .orElseThrow(() -> new GeneralException("clinica-not-found", "Clínica no encontrada"));
        return clinicaMapper.toDto(entity);
    }

    @Transactional
    public ClinicaDto update(ClinicaDto dto) {
        ClinicaEntity entity = this.clinicaRepository.findById(1L)
                .orElseThrow(() -> new GeneralException("clinica-not-found", "Clínica no encontrada"));

        // actualizar campos permitidos
        if (dto.getNombre() != null) entity.setNombre(dto.getNombre());
        if (dto.getCorreo() != null) entity.setCorreo(dto.getCorreo());
        if (dto.getTelefono() != null) entity.setTelefono(dto.getTelefono());
        if (dto.getDireccion() != null) entity.setDireccion(dto.getDireccion());
        if (dto.getHorasAtencion() != null) entity.setHorasAtencion(dto.getHorasAtencion());
        if (dto.getHoraCierre() != null) entity.setHoraCierre(dto.getHoraCierre());
        if (dto.getNit() != null) entity.setNit(dto.getNit());
        if (dto.getRegistroSanitario() != null) entity.setRegistroSanitario(dto.getRegistroSanitario());

        ClinicaEntity updated = this.clinicaRepository.save(entity);
        return clinicaMapper.toDto(updated);
    }
}

