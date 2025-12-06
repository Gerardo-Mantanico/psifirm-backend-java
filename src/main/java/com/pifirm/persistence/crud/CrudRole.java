package com.pifirm.persistence.crud;

import com.pifirm.persistence.entity.RoleEntity;
import com.pifirm.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface CrudRole extends CrudRepository<RoleEntity, Long> {

    @Query(value = "SELECT role FROM UserRoleEntity userRole INNER JOIN userRole.role role WHERE userRole.user = :user")
    Set<RoleEntity> findAllRolesByUserId(@Param("user") UserEntity userId);
}
