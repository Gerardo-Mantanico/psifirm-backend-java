package com.pifirm.domain.repository.cita;

import com.pifirm.domain.repository.ILEmpleadoRepository;
import com.pifirm.persistence.entity.ILEmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InformacionLaboralEmpleadoRepository extends JpaRepository<ILEmpleadoEntity, Long> {

    @Query(value = "select e.user_id from servicios s " +
            "join area a on a.servicio_id = s.id " +
            "join informacion_laboral_empleados e on e.area_id = a.id " +
            "where s.id = :servicioId", nativeQuery = true)
    List<Long> findUserIdsByServicioId(@Param("servicioId") Long servicioId);

}