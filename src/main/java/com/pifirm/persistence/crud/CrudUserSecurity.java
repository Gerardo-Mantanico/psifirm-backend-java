package com.pifirm.persistence.crud;

import com.pifirm.domain.enums.SecurityCodeStatus;
import com.pifirm.domain.enums.SecurityCodeType;
import com.pifirm.persistence.entity.UserEntity;
import com.pifirm.persistence.entity.UserSecurityEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CrudUserSecurity extends CrudRepository<UserSecurityEntity, Long> {
    Optional<List<UserSecurityEntity>> findAllByUserAndCodeTypeAndStatus(UserEntity user, SecurityCodeType codeType, SecurityCodeStatus securityCodeStatus);

    void deleteByUserIdAndCodeType(Long userId, SecurityCodeType codeType);

    @Modifying
    @Query("UPDATE UserSecurityEntity us SET us.status = :newStatus WHERE us.user = :user AND us.codeType = :codeType AND us.status = :currentStatus")
    int updateStatusByUserAndCodeTypeAndStatus(@Param("newStatus") SecurityCodeStatus newStatus, @Param("user") UserEntity user, @Param("codeType") SecurityCodeType codeType, @Param("currentStatus") SecurityCodeStatus currentStatus);
}
