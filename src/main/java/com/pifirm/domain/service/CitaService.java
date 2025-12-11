package com.pifirm.domain.service;

import com.pifirm.domain.dto.cita.CitaDto;
import com.pifirm.domain.dto.cita.CitaResDto;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.CitamedicaRepository;
import com.pifirm.domain.repository.HistorialClinicaRepository;
import com.pifirm.domain.repository.ServicioRepository;
import com.pifirm.domain.repository.UserRepository;
import com.pifirm.persistence.entity.*;
import com.pifirm.persistence.mapper.CitaMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CitaService {
    private final CitamedicaRepository citaRepository;
    private final CitaMapper citaMapper;
    private final UserRepository userRepository;
    private final HistorialClinicaRepository hcRepository;
    private final ServicioRepository servicioRepository;
    private final UserUtilsService userUtilsService;
    private final EmailService emailService;


    @Transactional
    public CitaResDto add(CitaDto dto) {
        CitaMedicaEntity entity = citaMapper.toEntity(dto);

        UserEntity paciente = userRepository.findById(userUtilsService.getCurrent().getId())
                .orElseThrow(() -> new GeneralException("paciente-not-found", "Paciente no encontrado"));
        UserEntity medico = null;

        ServicioEntity servicio = servicioRepository.findById(1L)
                .orElseThrow(() -> new GeneralException("servicio-not-found", "Servicio no encontrado"));
        entity.setPaciente(paciente);
        entity.setMedico(medico);
        entity.setServicioMedico(servicio);

        CitaMedicaEntity saved = citaRepository.save(entity);

        // Enviar notificación por correo al paciente (asíncrono)
        try {
            if (paciente.getEmail() != null && !paciente.getEmail().isBlank()) {
                String subject = "Confirmación de cita médica";
                java.time.format.DateTimeFormatter dateFormatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
                java.time.format.DateTimeFormatter timeFormatter = java.time.format.DateTimeFormatter.ofPattern("HH:mm");
                String fecha = saved.getFechaCita() != null ? saved.getFechaCita().format(dateFormatter) : "(fecha no especificada)";
                String hora = saved.getFechaCita() != null ? saved.getFechaCita().format(timeFormatter) : "(hora no especificada)";

                String htmlBody = "<html>" +
                        "<body style=\"font-family:Arial,Helvetica,sans-serif;color:#333;\">" +
                        "<div style=\"max-width:600px;margin:0 auto;padding:20px;border:1px solid #eaeaea;background:#fff;\">" +
                        "<h2 style=\"color:#2d6cdf;\">Confirmación de cita médica</h2>" +
                        "<p>Hola <strong>" + paciente.getFirstname() + " " + paciente.getLastname() + "</strong>,</p>" +
                        "<p>Tu cita ha sido <strong>programada</strong> correctamente.</p>" +
                        "<table style=\"width:100%;border-collapse:collapse;margin-top:10px;\">" +
                        "<tr><td style=\"padding:8px;border:1px solid #eee;\"><strong>Servicio</strong></td><td style=\"padding:8px;border:1px solid #eee;\">" + (servicio.getNombre() != null ? servicio.getNombre() : "(servicio)") + "</td></tr>" +
                        "<tr><td style=\"padding:8px;border:1px solid #eee;\"><strong>Fecha</strong></td><td style=\"padding:8px;border:1px solid #eee;\">" + fecha + "</td></tr>" +
                        "<tr><td style=\"padding:8px;border:1px solid #eee;\"><strong>Hora</strong></td><td style=\"padding:8px;border:1px solid #eee;\">" + hora + "</td></tr>" +
                        "</table>" +
                        "<p style=\"margin-top:16px;\">Si necesitas cambiar o cancelar la cita, por favor contacta con nosotros.</p>" +
                        "<p style=\"color:#888;font-size:12px;\">Gracias,<br/>Equipo PsiFirm</p>" +
                        "</div></body></html>";

                emailService.sendHtmlEmailAsync(paciente.getEmail(), subject, htmlBody);
            }
        } catch (Exception ex) {
            // No interrumpimos la creación si falla el envío; el servicio de email ya registra errores internamente
        }

        return citaMapper.toDto(saved);
    }

    @Transactional
    public CitaResDto update(Long id, CitaDto dto) {

        CitaMedicaEntity entity = citaRepository.findById(id)
                .orElseThrow(() -> new GeneralException("cita-not-found", "Cita no encontrada"));

        if (dto.fechaCita() != null) entity.setFechaCita(dto.fechaCita());
        if (dto.nota() != null) entity.setNota(dto.nota());
        if (userUtilsService.getCurrent().getId() != null) {
            var medico = userRepository.findById(userUtilsService.getCurrent().getId()).orElseThrow(() -> new GeneralException("medico-not-found", "Medico no encontrado"));
            entity.setMedico(medico);
        }
        if (dto.servicioMedicoId() != null) {
            var servicio = servicioRepository.findById(dto.servicioMedicoId()).orElseThrow(() -> new GeneralException("servicio-not-found", "Servicio no encontrado"));
            entity.setServicioMedico(servicio);
        }

        CitaMedicaEntity updated = citaRepository.save(entity);
        return citaMapper.toDto(updated);
    }

    @Transactional
    public CitaResDto getById(Long id) {
        CitaMedicaEntity entity = citaRepository.findById(id)
                .orElseThrow(() -> new GeneralException("cita-not-found", "Cita no encontrada"));
        return citaMapper.toDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        if (!citaRepository.existsById(id)) throw new GeneralException("cita-not-found", "Cita no encontrada");
        citaRepository.deleteById(id);
    }

    @Transactional
    public Page<CitaResDto> all(Pageable pageable) {
        return citaRepository.findAll(pageable).map(entity -> citaMapper.toDto(entity));
    }

    @Transactional
    public CitaResDto asignarPsicologo(Long psmId,  Long citaId){
        CitaMedicaEntity cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new GeneralException("cita-not-found", "Cita no encontrada"));
        var psm = userRepository.findById(psmId).orElseThrow(() -> new GeneralException("medico-not-found", "Medico no encontrado"));
        cita.setMedico(psm);
        this.citaRepository.save(cita);
        return citaMapper.toDto(cita);
    }
}
