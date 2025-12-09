package com.pifirm.domain.repository;

import com.pifirm.persistence.entity.ServicioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicioRepository  extends JpaRepository<ServicioEntity, Long> {

    Optional<Object> findByNombre(String nombre);
}

