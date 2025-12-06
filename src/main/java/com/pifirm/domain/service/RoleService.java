package com.pifirm.domain.service;

import com.pifirm.domain.dto.role.RoleDto;
import com.pifirm.domain.repository.RoleRepository;
import com.pifirm.persistence.entity.RoleEntity;
import com.pifirm.persistence.entity.UserEntity;
import com.pifirm.persistence.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public Set<RoleEntity> findRolesByUser(UserEntity user) {
        return roleRepository.findAllRolesByUserId(user);
    }

    public List<RoleDto> findAllRoles() {
        return this.roleMapper.toDto(this.roleRepository.findAll());
    }
}
