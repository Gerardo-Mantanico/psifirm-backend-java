package com.pifirm.domain.service;

import com.pifirm.domain.dto.paciente.InfPacienteReqDto;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.InfoPacienteRepository;
import com.pifirm.persistence.entity.UserEntity;
import com.pifirm.persistence.mapper.InfPacienteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InfoPacienteService {
    private final InfoPacienteRepository infoPacienteRepository;
    private final UserUtilsService userUtilsService;
    private final InfPacienteMapper infPacienteMapper;

    public void add(InfPacienteReqDto dto){
        var entidad = this.infPacienteMapper.toPacienteEntity(dto);
        entidad.setUser(new UserEntity());
        entidad.getUser().setId(userUtilsService.getCurrent().getId());
        this.infoPacienteRepository.save(entidad);
    }

    public InfPacienteReqDto infoMe() {
        var entidad = this.infoPacienteRepository.findByUser_Id(userUtilsService.getCurrent().getId()).orElseThrow(() -> new GeneralException("error", "No existen informacion adcional para este usuario"));
        return this.infPacienteMapper.toDO(entidad);
    }

    public void update(InfPacienteReqDto dto){
         var existing = this.infoPacienteRepository.findByUser_Id(userUtilsService.getCurrent().getId()).orElseThrow(() -> new GeneralException("error", "No existen informacion adcional para este usuario"));
         var updated = this.infPacienteMapper.toPacienteEntity(dto);
         updated.setId(existing.getId());
         updated.setUser(existing.getUser());
         this.infoPacienteRepository.save(updated);
    }

}
