package com.pifirm.domain.service.hc;

import com.pifirm.domain.enums.EstadoCita;
import com.pifirm.domain.repository.CitamedicaRepository;
import com.pifirm.domain.repository.NominaRepository;
import com.pifirm.domain.repository.PagoSesionRepository;
import com.pifirm.domain.repository.notasProgreso.SessionRepository;
import com.pifirm.domain.service.CitaService;
import com.pifirm.persistence.entity.PagoSesionEntity;
import com.pifirm.persistence.entity.notasProgreso.SessionEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SesionesService {
    private final SessionRepository sessionRepository;
    private final CitaService citaService;
    private final NominaRepository nominaRepository;
    private  final PagoSesionRepository pagoSesionRepository;

    @Transactional
    public void registerSession(SessionEntity sessionData) {
        sessionData.setId(null);
        sessionData.setNumeroSesiones(null);
        sessionData.setFecha(LocalDateTime.now());

        try {
            sessionData.setId(null);
            sessionData.setNumeroSesiones(null);
            sessionData.setFecha(LocalDateTime.now());

            var sesion = sessionRepository.saveAndFlush(sessionData);
            System.out.println("Sesión guardada id=" + sesion.getId());

            var cita = citaService.updataCita(sessionData.getHcId(), EstadoCita.PROGRAMADA);
            if (cita == null) {
                System.out.println("citaService.updataCita devolvió null para hcId=" + sessionData.getHcId());
                return;
            }

            var nominaOpt = this.nominaRepository.findByUser_Id(cita.getMedico().getId());
            if (nominaOpt.isEmpty()) {
                System.out.println("No se encontró nomina para medico id=" + cita.getMedico().getId());
            } else {
                var nomina = nominaOpt.get();
                nomina.setSesionesTrabajadas(nomina.getSesionesTrabajadas() + 1);
                this.nominaRepository.save(nomina);
                System.out.println("Nomina actualizada para medico id=" + cita.getMedico().getId());
            }

            PagoSesionEntity pagoSesion = new PagoSesionEntity();
            pagoSesion.setPacienteId(cita.getPaciente().getFirstname() + " " + cita.getPaciente().getLastname());
            pagoSesion.setSessionId(sesion.getId());
            pagoSesion.setMonto(new BigDecimal(100));
            pagoSesion.setPagado(false);

            this.pagoSesionRepository.saveAndFlush(pagoSesion);
            System.out.println("Pago por sesión generado para el paciente: " + pagoSesion.getPacienteId());

        } catch (Exception ex) {
            System.out.println("Error al registrar sesión y generar pago: " + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }

    }

    public List<SessionEntity> getAllSessions(Long hcId) {
        return sessionRepository.findAllByHcId(hcId);
    }

    public void deleteSession(Long sessionId) {
        sessionRepository.deleteById(sessionId);
    }
}
