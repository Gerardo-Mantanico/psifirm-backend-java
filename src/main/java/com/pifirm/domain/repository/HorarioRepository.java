package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.HorarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioRepository extends JpaRepository<HorarioEntity, Long> {
    void existsByUser_Id(Long userId);

    List<HorarioEntity> findByUser_Id(Long userId);
}
