package com.pifirm.domain.repository;

import com.pifirm.domain.dto.user.UserDto;
import com.pifirm.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    UserEntity save(UserEntity userEntity);
    Optional<UserEntity> findByEmail(String email);
    Page<UserDto> allActiveUsers(Pageable pageable);
    UserEntity update(UserEntity userEntity);
    Optional<UserEntity> findById(Long id);

    @Query("SELECT u FROM UserEntity u WHERE (:dpi IS NULL OR u.dpi = :dpi) AND (:name IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    List<UserEntity> findByDpiOrName(@Param("dpi") String dpi, @Param("name") String name);

}
