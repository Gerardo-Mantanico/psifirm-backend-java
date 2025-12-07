package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.geom.Area;

public interface AreaRepository extends JpaRepository<AreaEntity, Long> {
}
