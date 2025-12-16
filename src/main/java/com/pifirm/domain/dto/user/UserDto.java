package com.pifirm.domain.dto.user;

import com.pifirm.domain.dto.role.RoleDto;

import java.time.Instant;

public record UserDto(
        Long id,
        String firstname,
        String lastname,
        String email,
        String phoneNumber,
        Long dpi,
        Instant createdAt,
        Instant updatedAt,
        Boolean active,
        RoleDto role,
        Boolean use2fa
) {
}
