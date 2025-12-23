package com.pifirm.domain.service.hc;

import com.pifirm.domain.repository.diagnostico.PruebasAplicadasRepository;
import com.pifirm.domain.service.S3Service;
import com.pifirm.persistence.entity.diagnostico.PruebasAplicadasEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class EscalaPruebasService {
    private final PruebasAplicadasRepository  pruebasAplicadasRepository;
    private final S3Service s3Service;


    public PruebasAplicadasEntity guardarPruebaAplicada(PruebasAplicadasEntity entity, MultipartFile file) {
        entity.setId(null);
        entity.setArchivo(this.s3Service.uploadFile(file, "pruebas_aplicadas/" + file.getOriginalFilename()));
        return pruebasAplicadasRepository.save(entity);
    }

    public List<PruebasAplicadasEntity> getPruebaAplicadaById(Long hcId) {
        return this.pruebasAplicadasRepository.findAllByHcId(hcId);
    }
}
