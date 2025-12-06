package com.pifirm.persistence;

import com.pifirm.domain.dto.user.UserDto;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.UserRepository;
import com.pifirm.persistence.crud.CrudUser;
import com.pifirm.persistence.entity.UserEntity;
import com.pifirm.persistence.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserEntityRepository implements UserRepository {

    private final CrudUser crudUser;
    private final UserMapper userMapper;

    public UserEntityRepository(CrudUser crudUser, UserMapper userMapper) {
        this.crudUser = crudUser;
        this.userMapper = userMapper;
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        if (crudUser.findFirstByEmail(userEntity.getEmail()).orElse(null) != null) {
            throw new GeneralException("email-already-registered", "El correo electrónico ya está registrado.");
        }

        return this.crudUser.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return this.crudUser.findFirstByEmailAndActiveTrue(email);
    }

    @Override
    public Page<UserDto> allActiveUsers(Pageable pageable) {
        return this.crudUser.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        return this.crudUser.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return this.crudUser.findByIdAndActiveTrue(id);
    }

    @Override
    public Optional<UserEntity> find(Long id) {
        return this.crudUser.findById(id);
    }
}
