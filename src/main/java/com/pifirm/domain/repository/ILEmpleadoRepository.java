package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.ILEmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ILEmpleadoRepository extends JpaRepository<ILEmpleadoEntity, Long> {
    Optional<ILEmpleadoEntity> findByUserId(Long userId);
}
