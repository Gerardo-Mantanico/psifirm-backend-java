package com.pifirm.domain.repository.planintervencion;

import com.pifirm.persistence.entity.planintervencion.TipoObjetivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoObjetivoRepository extends JpaRepository<TipoObjetivoEntity, Long> {
}



