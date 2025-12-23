package com.pifirm.domain.service.hc;

import com.pifirm.domain.dto.hc.data.HistorialPersonalDto;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.HistorialPersonalRepository;
import com.pifirm.persistence.entity.HistorialPersonalEntity;
import com.pifirm.persistence.mapper.HistorialPersonalesMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HistoriaPersonalService {

    private final HistorialPersonalRepository historialPersonalRepository;
    private  final HistorialPersonalesMapper mapper;

    public HistorialPersonalEntity save(HistorialPersonalDto entidad) {
        return historialPersonalRepository.save(mapper.toDo(entidad));
    }

    public HistorialPersonalEntity findByIdHc(Long hc) {
        return historialPersonalRepository.findByHcId(hc).orElseThrow(()-> new GeneralException("error" ,"No se encontraron antecedentes familiares para el paciente con ID: " + hc));
    }
}
