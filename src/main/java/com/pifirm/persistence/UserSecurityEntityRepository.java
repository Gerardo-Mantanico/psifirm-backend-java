package com.pifirm.persistence;

import com.pifirm.domain.enums.SecurityCodeStatus;
import com.pifirm.domain.enums.SecurityCodeType;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.UserSecurityRepository;
import com.pifirm.persistence.crud.CrudUserSecurity;
import com.pifirm.persistence.entity.UserEntity;
import com.pifirm.persistence.entity.UserSecurityEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserSecurityEntityRepository implements UserSecurityRepository {

    private static final Logger log = LogManager.getLogger(UserSecurityEntityRepository.class);
    private final CrudUserSecurity crudUserSecurity;
    private final PasswordEncoder passwordEncoder;

    public UserSecurityEntityRepository(CrudUserSecurity crudUserSecurity, PasswordEncoder passwordEncoder) {
        this.crudUserSecurity = crudUserSecurity;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserSecurityEntity> findCodeByCodeAndTypeAndStatus(UserEntity userEntity, String code, SecurityCodeType securityCodeType) {
        List<UserSecurityEntity> candidates = this.crudUserSecurity.findAllByUserAndCodeTypeAndStatus(userEntity, securityCodeType, SecurityCodeStatus.ACTIVE).orElseThrow(
                () -> new GeneralException("invalid-code", "Código inválido")
                        .setStatus(HttpStatus.UNAUTHORIZED)
        );

        for (UserSecurityEntity entity : candidates) {
            if (passwordEncoder.matches(code, entity.getCode())) {
                return Optional.of(entity);
            }
        }

        return Optional.empty();
    }

    @Override
    public void inactivePreviousByUserAndCodeType(UserEntity userEntity, SecurityCodeType securityCodeType) {
        // Update status of previous ACTIVE codes. SET to INACTIVE
        this.crudUserSecurity.updateStatusByUserAndCodeTypeAndStatus(SecurityCodeStatus.INACTIVE ,userEntity, securityCodeType, SecurityCodeStatus.ACTIVE);
    }

    @Override
    public void save(UserSecurityEntity userSecurityEntity) {
        this.crudUserSecurity.save(userSecurityEntity);
    }

}
