package com.pifirm.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pifirm.persistence.entity.inventario.CategoriaProductoEntidad;
import java.util.Optional;

public interface CategoriaProductoRepository extends JpaRepository<CategoriaProductoEntidad, Long> {

    Optional<CategoriaProductoEntidad> findByNombre(String nombre);

}
