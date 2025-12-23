package com.pifirm.domain.service.hc;

import com.pifirm.domain.enums.EstadoCita;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.CitamedicaRepository;
import com.pifirm.domain.repository.notasProgreso.AltaTerapeuticaRepository;
import com.pifirm.domain.service.S3Service;
import com.pifirm.domain.utils.Base64ToImage;
import com.pifirm.persistence.entity.CitaMedicaEntity;
import com.pifirm.persistence.entity.notasProgreso.AltaTerapeuticaEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor

public class AltaTerapeuticaService {

    private final AltaTerapeuticaRepository altaTerapeuticaRepository;
    private final CitamedicaRepository citamedicaRepository;
    private final Base64ToImage base64ToImage;
    private final S3Service s3Service;

    public void registrar(AltaTerapeuticaEntity entity) {
        entity.setId(null);

        try {
            MultipartFile Img = this.base64ToImage.convert(entity.getFirmaPaciente());
            var url = this.s3Service.uploadFile(Img, "firmas/alta_terapeutica_paciente"+entity.getHcId() + LocalDateTime.now() + ".png");
            entity.setFirmaPaciente(url);


            MultipartFile ImgPsicolog = this.base64ToImage.convert(entity.getFirmaPsicologo());
            var urlp = this.s3Service.uploadFile(Img, "firmas/alta_terapeutica_psicologo"+entity.getHcId() + LocalDateTime.now() + ".png");
            entity.setFirmaPsicologo(urlp);


        }catch (Exception e) {
            throw new GeneralException("Error al convertir la firma del paciente", e.getMessage());
        }





        this.altaTerapeuticaRepository.save(entity);

        CitaMedicaEntity cita = this.citamedicaRepository.findByHistoriaClinica_Id(entity.getHcId());
        if (cita != null) {
            cita.setEstadoCita(EstadoCita.COMPLETADA);
            this.citamedicaRepository.save(cita);
        }
    }


public AltaTerapeuticaEntity obtenerPorId(Long HcId) {
    AltaTerapeuticaEntity alta = this.altaTerapeuticaRepository
        .findByHcId(HcId)
        .orElseThrow(() -> new GeneralException(" ", "Alta terapéutica no encontrada"));

    return alta;
    }
}
