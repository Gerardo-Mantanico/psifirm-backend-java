package com.pifirm.domain.service;

import com.pifirm.domain.dto.horario.HorarioReqDto;
import com.pifirm.domain.dto.horario.HorarioResDto;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.ClinicaRepository;
import com.pifirm.domain.repository.HorarioRepository;
import com.pifirm.domain.repository.UserRepository;
import com.pifirm.persistence.entity.HorarioEntity;
import com.pifirm.persistence.entity.UserEntity;
import com.pifirm.persistence.mapper.HorarioMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class HorarioService {
    private final HorarioRepository horarioRepository;
    private final HorarioMapper horarioMapper;
    private final ClinicaService clinicaService;
    private final UserRepository userRepository;

    public void save(List<HorarioEntity> LIstHorario, UserEntity user){

        for (HorarioEntity horario : LIstHorario) {
            horario.setUser(user);
            if(horario.getHoraInicio().isAfter(horario.getHoraFin())){
                throw new GeneralException("error-hora","La hora de inicio no puede ser posterior a la hora de fin");
            }
            else if(horario.getHoraInicio().isBefore(clinicaService.getById().getHorasAtencion())){
                throw new GeneralException("error-hora","La hora de inicio no puede ser posterior a la hora de atencion de la clinica");
            }
            else  if (horario.getHoraFin().isAfter(clinicaService.getById().getHoraCierre())){
                throw new GeneralException("error-hora","La hora de fin no puede ser posterior a la hora de cierre de la clinica");
            }
            this.horarioRepository.save(horario);
        }

    }


    public void delete(Long id){
        this.horarioRepository.findById(id).ifPresent(this.horarioRepository::delete);
    }

    @Transactional
    public void update(Long userId, List<HorarioReqDto> horarioDtos){
        UserEntity user = this.userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException("user-not-found", "Usuario no encontrado"));

        var clinica = clinicaService.getById();
        LocalTime apertura = clinica.getHorasAtencion();
        LocalTime cierre = clinica.getHoraCierre();

        List<HorarioEntity> nuevos = new ArrayList<>();
        for (HorarioReqDto dto : horarioDtos) {
            HorarioEntity entity = horarioMapper.ToEntity(dto);
            if (entity.getHoraInicio().isAfter(entity.getHoraFin())) {
                throw new GeneralException("error-hora","La hora de inicio no puede ser posterior a la hora de fin");
            }
            if (entity.getHoraInicio().isBefore(apertura)) {
                throw new GeneralException("error-hora","La hora de inicio no puede ser anterior a la hora de apertura de la clínica");
            }
            if (entity.getHoraFin().isAfter(cierre)) {
                throw new GeneralException("error-hora","La hora de fin no puede ser posterior a la hora de cierre de la clínica");
            }
            entity.setUser(user);
            nuevos.add(entity);
        }

        // Eliminar los horarios actuales del usuario y guardar los nuevos
        List<HorarioEntity> existentes = this.horarioRepository.findByUser_Id(userId);
        if (!existentes.isEmpty()) {
            this.horarioRepository.deleteAll(existentes);
        }
        if (!nuevos.isEmpty()) {
            this.horarioRepository.saveAll(nuevos);
        }
    }

    public List<HorarioReqDto> getByUserId(Long userId) {
        List<HorarioEntity> horarios = this.horarioRepository.findByUser_Id(userId);
        return this.horarioMapper.listToDo(horarios);
    }

}
