package com.pifirm.domain.service;

import com.pifirm.domain.dto.cita.CitaDto;
import com.pifirm.domain.dto.cita.CitaResDto;
import com.pifirm.domain.dto.cita.CitaUpdateDto;
import com.pifirm.domain.enums.EstadoCita;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.CitamedicaRepository;
import com.pifirm.domain.repository.HistorialClinicaRepository;
import com.pifirm.domain.repository.ServicioRepository;
import com.pifirm.domain.repository.UserRepository;
import com.pifirm.domain.repository.cita.InformacionLaboralEmpleadoRepository;
import com.pifirm.persistence.entity.*;
import com.pifirm.persistence.mapper.CitaMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private final InformacionLaboralEmpleadoRepository informacionLaboralEmpleadoRepository;


    @Transactional
    public CitaResDto add(CitaDto dto) {
        CitaMedicaEntity entity = citaMapper.toEntity(dto);

        UserEntity paciente = userRepository.findById(userUtilsService.getCurrent().getId())
                .orElseThrow(() -> new GeneralException("paciente-not-found", "Paciente no encontrado"));

        ServicioEntity servicio = servicioRepository.findById(dto.servicioMedicoId())
                .orElseThrow(() -> new GeneralException("servicio-not-found", "Servicio no encontrado"));
        entity.setPaciente(paciente);


        //buscar psicologo disponible
        UserEntity medico = new UserEntity();
        medico.setId(this.buscarPsicologo(dto.servicioMedicoId(), dto.fechaCita()));
        entity.setMedico(medico);
        entity.setServicioMedico(servicio);
        entity.setId(null);

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
    public CitaResDto update(Long id, CitaUpdateDto dto) {

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

        if(dto.estado() != null ){
            entity.setEstadoCita(dto.estado());
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
    public Page<CitaResDto> all(Pageable pageable , LocalDate date, String state) {
        var page = citaRepository.findAll(pageable);
        var filtered = page.getContent().stream()
                .filter(e -> {
                    if (date == null) return true;
                    var fecha = e.getFechaCita();
                    if (fecha == null) return false;
                    return fecha.toLocalDate().equals(date); // asume que fechaCita es LocalDateTime
                })
                .filter(e -> {
                    var currentUser = userUtilsService.getCurrent();
                    if (currentUser == null) return false;

                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

                    boolean isAdmin = auth != null && auth.getAuthorities().stream()
                            .anyMatch(a -> "ADMIN".equals(a.getAuthority()) || "ROLE_ADMIN".equals(a.getAuthority()));
                    if (isAdmin) return true;

                    boolean isMedico = auth != null && auth.getAuthorities().stream()
                            .anyMatch(a -> "PSM".equals(a.getAuthority()) || "ROLE_MEDICO".equals(a.getAuthority()));
                    if (isMedico) return e.getMedico() != null && e.getMedico().getId().equals(currentUser.getId());

                    boolean isCliente = auth != null && auth.getAuthorities().stream()
                            .anyMatch(a -> "CLIENTE".equals(a.getAuthority()) || "ROLE_CLIENTE".equals(a.getAuthority()));
                    if (isCliente) return e.getPaciente() != null && e.getPaciente().getId().equals(currentUser.getId());
                    return false;
                })
                .map(citaMapper::toDto)
                .collect(java.util.stream.Collectors.toList());

        return new org.springframework.data.domain.PageImpl<>(filtered, pageable, filtered.size());
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

   @Transactional
    public CitaMedicaEntity existCita(Long id){
        return this.citaRepository.findById(id).orElseThrow(()-> new  GeneralException("error","no existe una cita con este registro"));
    }

    @Transactional
    public void addHcToCita(Long citaId, Long hcId){
        CitaMedicaEntity cita = this.existCita(citaId);
        HistoriaClinicaEntity hc = this.hcRepository.findById(hcId)
                .orElseThrow(() -> new GeneralException("hc-not-found", "Historia Clinica no encontrada"));
        cita.setHistoriaClinica(hc);
        this.citaRepository.save(cita);
    }


public Long buscarPsicologo(Long service, LocalDateTime date) {
    for (Long userId : this.informacionLaboralEmpleadoRepository.findUserIdsByServicioId(service)) {
        boolean ocupado = this.citaRepository.existsByMedicoIdAndFechaCita(userId, date);
        if (!ocupado) {
            return userId;
        }
    }
    throw new GeneralException("no-psicologo-available", "No hay psicologos disponibles en esa fecha/hora");
}

public CitaMedicaEntity  updataCita(Long id, EstadoCita estado){
    CitaMedicaEntity cita = this.citaRepository.findByHistoriaClinica_Id(id);
    cita.setEstadoCita(estado);
    this.emailService.sendEmailAsync("Cita actualizada", "Su cita ha sido actualizada al estado: " + estado.name(), cita.getPaciente().getEmail());
    this.emailService.sendEmailAsync("Cita actualizada", "La cita del paciente " + cita.getPaciente().getFirstname() + " " + cita.getPaciente().getLastname() + " ha sido actualizada al estado: " + estado.name(), cita.getMedico().getEmail());
        return    this.citaRepository.save(cita);
    }

}
