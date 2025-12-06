package com.pifirm.domain.repository;


import com.pifirm.persistence.entity.RoleEntity;
import com.pifirm.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository {
    Set<RoleEntity> findAllRolesByUserId(UserEntity userEntity);
    List<RoleEntity> findAll();
    Optional<RoleEntity> findById(Long id);
}
