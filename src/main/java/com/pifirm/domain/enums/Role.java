package com.pifirm.domain.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN(1L),
    COMMERCES(2L),
    DELIVERY(3L),
    OPERATIONS_COORDINATOR(4L),
    CUSTOMER(5L);

    private final Long id;

    private Role(Long id) {
        this.id = id;
    }
}

