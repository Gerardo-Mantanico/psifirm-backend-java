package com.pifirm.domain.service.hc;

import com.pifirm.domain.repository.AntecedentesPersonalesRepository;
import com.pifirm.domain.repository.HistorialPersonalRepository;
import com.pifirm.persistence.entity.AntecedentesPersonalesEntity;
import com.pifirm.persistence.entity.HistorialPersonalEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AntecedentesFPService {
    private final AntecedentesPersonalesRepository antecedentesPersonalesRepository;
    private final HistorialPersonalRepository historialPersonalRepository;

    public void  registerAntecedentesPersonales(AntecedentesPersonalesEntity entity){
            this.antecedentesPersonalesRepository.save(entity);
    }

    public void registrarHistoriaPersonal(HistorialPersonalEntity entity){
        this.historialPersonalRepository.save(entity);
    }
}
