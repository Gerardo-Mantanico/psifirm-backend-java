package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.role.RoleDto;
import com.pifirm.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(RoleEntity roleEntity);
    List<RoleDto> toDto(List<RoleEntity> roleEntity);
}
