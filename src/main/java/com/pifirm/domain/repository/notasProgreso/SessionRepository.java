package com.pifirm.domain.repository.notasProgreso;

import com.pifirm.persistence.entity.notasProgreso.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
    List<SessionEntity> findAllByHcId( Long hcId);

}



