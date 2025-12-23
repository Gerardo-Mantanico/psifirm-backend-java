package com.pifirm.domain.service.hc;

import com.pifirm.domain.repository.notasProgreso.SessionRepository;
import com.pifirm.persistence.entity.notasProgreso.SessionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SesionesService {
    private final SessionRepository sessionRepository;

    public void registerSession(SessionEntity sessionData) {
        sessionData.setId(null);
        sessionData.setNumeroSesiones(null);
        sessionData.setFecha(LocalDateTime.now());

        sessionRepository.save(sessionData);
    }

    public List<SessionEntity> getAllSessions(Long hcId) {
        return sessionRepository.findAllByHcId(hcId);
    }

    public void deleteSession(Long sessionId) {
        sessionRepository.deleteById(sessionId);
    }
}
