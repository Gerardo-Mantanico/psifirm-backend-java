package com.pifirm.persistence;

import com.pifirm.domain.repository.RoleRepository;
import com.pifirm.persistence.crud.CrudRole;
import com.pifirm.persistence.entity.RoleEntity;
import com.pifirm.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class RoleEntityRepository implements RoleRepository {
    private final CrudRole crudRole;

    public RoleEntityRepository(CrudRole crudRole) {
        this.crudRole = crudRole;
    }

    @Override
    public Set<RoleEntity> findAllRolesByUserId(UserEntity user) {
        return this.crudRole.findAllRolesByUserId(user);
    }

    @Override
    public List<RoleEntity> findAll() {
        return (List<RoleEntity>) this.crudRole.findAll();
    }

    @Override
    public Optional<RoleEntity> findById(Long id) {
        return this.crudRole.findById(id);
    }

}
