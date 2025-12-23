package com.pifirm.domain.service.hc;

import com.pifirm.domain.dto.hc.data.AntecedentesPersonalesDto;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.AntecedentesPersonalesRepository;
import com.pifirm.persistence.entity.AntecedentesPersonalesEntity;
import com.pifirm.persistence.mapper.AntecedentesPersonalesMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AntecedentesFamiliaresService {
  private final AntecedentesPersonalesRepository antecedentesPersonalesRepository;
  private  final AntecedentesPersonalesMapper mapper;

    public AntecedentesPersonalesEntity save(AntecedentesPersonalesDto entidad) {

        return antecedentesPersonalesRepository.save(mapper.toDo(entidad));
    }

    public AntecedentesPersonalesEntity findByIdHc(Long hc) {
        return antecedentesPersonalesRepository.findByHcId(hc).orElseThrow(()-> new GeneralException("error" ,"No se encontraron antecedentes familiares para el paciente con ID: " + hc));
    }
}
