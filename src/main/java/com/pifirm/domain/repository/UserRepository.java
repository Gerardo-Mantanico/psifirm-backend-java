package com.pifirm.domain.repository;

import com.pifirm.domain.dto.user.UserDto;
import com.pifirm.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository {
    UserEntity save(UserEntity userEntity);
    Optional<UserEntity> findByEmail(String email);
    Page<UserDto> allActiveUsers(Pageable pageable);
    UserEntity update(UserEntity userEntity);
    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> find(Long id);
}
