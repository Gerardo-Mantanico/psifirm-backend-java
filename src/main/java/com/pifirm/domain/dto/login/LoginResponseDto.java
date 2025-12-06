package com.pifirm.domain.dto.login;

import com.pifirm.domain.dto.user.UserDto;

public record LoginResponseDto(
        Boolean use2fa,
        UserDto user
) {
}
