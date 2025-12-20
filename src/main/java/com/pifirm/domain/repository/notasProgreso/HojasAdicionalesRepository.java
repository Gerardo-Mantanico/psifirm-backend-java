package com.pifirm.domain.repository.notasProgreso;

import com.pifirm.persistence.entity.notasProgreso.HojasAdicionalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HojasAdicionalesRepository extends JpaRepository<HojasAdicionalesEntity, Long> {
}

