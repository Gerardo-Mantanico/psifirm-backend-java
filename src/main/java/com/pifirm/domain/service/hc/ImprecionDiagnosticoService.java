package com.pifirm.domain.service.hc;

import com.pifirm.domain.dto.hc.data.HistorialPersonalDto;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.diagnostico.DiagnosticoC11Repository;
import com.pifirm.domain.repository.diagnostico.DiagnosticoDsm5Repository;
import com.pifirm.domain.repository.diagnostico.ImpresionDiagnosticoRepository;
import com.pifirm.persistence.entity.HistorialPersonalEntity;
import com.pifirm.persistence.entity.diagnostico.DiagnosticoCie11Entity;
import com.pifirm.persistence.entity.diagnostico.DiagnosticoDsm5Entity;
import com.pifirm.persistence.entity.diagnostico.ImpresionDiagnosticaEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImprecionDiagnosticoService {
    private final DiagnosticoC11Repository diagnosticoC11Repository;
    private final DiagnosticoDsm5Repository diagnosticoDsm5Repository;
    private final ImpresionDiagnosticoRepository diagnosticoRepository;


    public List<DiagnosticoCie11Entity> getC11(){
        return this.diagnosticoC11Repository.findAll();
    }

    public List<DiagnosticoDsm5Entity> getDsm5(){
        return this.diagnosticoDsm5Repository.findAll();
    }

    public ImpresionDiagnosticaEntity save(ImpresionDiagnosticaEntity entidad) {
        entidad.setId(null);
        return diagnosticoRepository.save(entidad);
    }

    public ImpresionDiagnosticaEntity findByIdHc(Long hc) {
        return diagnosticoRepository.findByHcId(hc).orElseThrow(()-> new GeneralException("error" ,"No se encontraron antecedentes familiares para el paciente con ID: " + hc));
    }
}
