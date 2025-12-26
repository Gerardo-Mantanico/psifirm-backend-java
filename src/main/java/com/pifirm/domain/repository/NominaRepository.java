package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.NominaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NominaRepository extends JpaRepository<NominaEntity, Long> {
    Optional<NominaEntity> findByUser_Id(Long userId);

    @Query("SELECT DISTINCT n FROM NominaEntity n " +
           "LEFT JOIN FETCH n.bonos b " +
           "LEFT JOIN FETCH b.tipoBono " +
           "LEFT JOIN FETCH n.retenciones r " +
           "LEFT JOIN FETCH r.tipoRetencion " +
           "LEFT JOIN FETCH n.descuentos d " +
           "LEFT JOIN FETCH d.tipoDescuento " +
           "LEFT JOIN FETCH n.user " +
           "LEFT JOIN FETCH n.metodoPago")
    List<NominaEntity> findAllWithRelations();
    Optional<NominaEntity> findByUser_Email(String userEmail);

    Long id(Long id);
}
