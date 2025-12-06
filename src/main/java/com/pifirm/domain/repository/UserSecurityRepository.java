package com.pifirm.domain.repository;

import com.pifirm.domain.enums.SecurityCodeType;
import com.pifirm.persistence.entity.UserEntity;
import com.pifirm.persistence.entity.UserSecurityEntity;

import java.util.Optional;

public interface UserSecurityRepository {
    Optional<UserSecurityEntity> findCodeByCodeAndTypeAndStatus(UserEntity userEntity, String code, SecurityCodeType securityCodeType);
    void inactivePreviousByUserAndCodeType(UserEntity userEntity, SecurityCodeType securityCodeType);
    void save(UserSecurityEntity userSecurityEntity);
}
