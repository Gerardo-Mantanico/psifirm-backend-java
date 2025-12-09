package com.pifirm.domain.service;

import com.pifirm.domain.dto.horario.HorarioReqDto;
import com.pifirm.domain.dto.horario.HorarioResDto;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.HorarioRepository;
import com.pifirm.domain.repository.UserRepository;
import com.pifirm.persistence.entity.HorarioEntity;
import com.pifirm.persistence.entity.UserEntity;
import com.pifirm.persistence.mapper.HorarioMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HorarioService {
    private final HorarioRepository horarioRepository;
    private final HorarioMapper horarioMapper;

    public void save(List<HorarioEntity> LIstHorario, UserEntity user){

        for (HorarioEntity horario : LIstHorario) {
            horario.setUser(user);
            if(horario.getHoraInicio().isAfter(horario.getHoraFin())){
                throw new GeneralException("error-hora","La hora de inicio no puede ser posterior a la hora de fin");
            }
            this.horarioRepository.save(horario);
        }

    }


    public void delete(Long id){
        this.horarioRepository.findById(id).ifPresent(this.horarioRepository::delete);
    }

    public void update(Long userId, HorarioReqDto horario){
      this.horarioRepository.existsByUser_Id(userId);

    }

    public List<HorarioReqDto> getByUserId(Long userId) {
        List<HorarioEntity> horarios = this.horarioRepository.findByUser_Id(userId);
        return this.horarioMapper.listToDo(horarios);
    }

}
