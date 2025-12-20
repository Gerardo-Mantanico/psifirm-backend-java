package com.pifirm.domain.dto.psm;

import com.pifirm.domain.dto.user.UserDto;
import com.pifirm.domain.dto.user.UserSimpleDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PsmCompletado {
    private UserDto userSimpleDto;
    private PsmResDto reqDto;
}
