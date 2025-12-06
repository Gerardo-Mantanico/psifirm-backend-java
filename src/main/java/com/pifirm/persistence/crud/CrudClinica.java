package com.pifirm.persistence.crud;

import com.pifirm.persistence.entity.ClinicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudClinica extends JpaRepository<ClinicaEntity, Long> {
}

