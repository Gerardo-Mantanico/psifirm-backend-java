package com.pifirm.persistence.crud;

import com.pifirm.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CrudUser extends JpaRepository<UserEntity, Long> {
    @EntityGraph(attributePaths = {"userRoles", "userRoles.role"})
    Optional<UserEntity> findFirstByEmail(String email);

    @EntityGraph(attributePaths = {"userRoles", "userRoles.role"})
    Optional<UserEntity> findFirstByEmailAndActiveTrue(String email);
    Page<UserEntity> findAllByActiveTrue(Pageable pageable);

    Optional<UserEntity> findByIdAndActiveTrue(Long id);
}
