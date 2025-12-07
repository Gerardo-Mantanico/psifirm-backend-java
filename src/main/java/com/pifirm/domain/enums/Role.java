package com.pifirm.domain.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN(1L),
    MANTENIMIENTO(2L),
    CLIENTE(3L),
    PSM(4L);
    private final Long id;

    private Role(Long id) {
        this.id = id;
    }
}

