package com.pifirm.domain.service;

import com.pifirm.domain.dto.paciente.InfPacienteReqDto;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.InfoPacienteRepository;
import com.pifirm.persistence.entity.InformacionPacienteEntity;
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
        this.infoPacienteRepository.save(this.infPacienteMapper.toDo(dto));
    }

    public  InformacionPacienteEntity getInfoPaciente(Long id) {
       return infoPacienteRepository.findByHcId(id).orElseThrow(()->
               new GeneralException("error", "No se encontro la informacion del paciente con id: " + id));

    }

}
