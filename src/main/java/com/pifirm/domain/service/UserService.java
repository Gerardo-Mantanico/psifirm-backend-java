package com.pifirm.domain.service;

import com.pifirm.domain.dto.user.UserCreationDto;
import com.pifirm.domain.dto.user.UserDto;
import com.pifirm.domain.dto.user.UserUpdateDto;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.*;
import com.pifirm.persistence.entity.*;
import com.pifirm.persistence.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;


    @Transactional
    public UserDto registerPaciente(UserCreationDto userCreationDto) {
        // Hashear contraseña en el DTO para que el mapper la copie ya hasheada
        String hashedPassword = passwordEncoder.encode(userCreationDto.getPassword());
        userCreationDto.setPassword(hashedPassword);

        Long roleId = userCreationDto.getRoleId();
        RoleEntity role = this.roleRepository.findById(roleId)
                .orElseThrow(() -> new GeneralException("role-not-found", "Rol no encontrado"));
        UserEntity userEntity = userMapper.toEntity(userCreationDto);
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUser(userEntity);
        userRoleEntity.setRole(role);
        userEntity.setUserRole(userRoleEntity);
        userEntity = userRepository.save(userEntity);
        userRoleEntity.setUser(userEntity);
        return this.userMapper.toDto(userEntity);
    }

    @Transactional
    public UserDto update(UserUpdateDto userUpdateDto, Long userId) {
        UserEntity userEntity = this.userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException("user-not-found", "Usuario no encontrado"));

        if (userUpdateDto.passwordNotEmpty())
            userEntity.setPassword(this.passwordEncoder.encode(userUpdateDto.getPassword()));
        if (userUpdateDto.firstnameNotEmpty())
            userEntity.setFirstname(userUpdateDto.getFirstname());
        if (userUpdateDto.lastnameNotEmpty())
            userEntity.setLastname(userUpdateDto.getLastname());
        ;
        if (userUpdateDto.phoneNumberNotEmpty())
            userEntity.setPhoneNumber(userUpdateDto.getPhoneNumber());
        if (userUpdateDto.getRoleId() != null) {
            RoleEntity role = this.roleRepository.findById(userUpdateDto.getRoleId())
                    .orElseThrow(() -> new GeneralException("role-not-found", "Rol no encontrado"));
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUser(userEntity);
            userRoleEntity.setRole(role);
            userEntity.setUserRole(userRoleEntity);
        }

        if (userUpdateDto.getUse2fa() != null) {
            userEntity.setUse2fa(userUpdateDto.getUse2fa());
        }

        if (userUpdateDto.getIsActive() != null) {
            userEntity.setActive(userUpdateDto.getIsActive());
        }

        this.userRepository.update(userEntity);

        return this.userMapper.toDto(userEntity);
    }

    @Transactional
    public Page<UserDto> allUsers(Pageable pageable) {
        return userRepository.allActiveUsers(pageable);
    }
    @Transactional
    public UserDto getById(Long userId) {
        UserEntity userEntity = this.userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException("user-not-found", "Usuario no encontrado"));
        return this.userMapper.toDto(userEntity);
    }

    @Transactional
    public void delete(Long userId) {
        UserEntity userEntity = this.userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException("user-not-found", "Usuario no encontrado"));
        userEntity.setActive(false);
        this.userRepository.update(userEntity);
    }

    public UserDto add(UserCreationDto userCreationDto) {
        LocalDate fecha = LocalDate.now();
        userCreationDto.setPassword( fecha.toString()+"user");
        String hashedPassword = passwordEncoder.encode(userCreationDto.getPassword());
        userCreationDto.setPassword(hashedPassword);

        Long roleId = userCreationDto.getRoleId();
        RoleEntity role = this.roleRepository.findById(roleId)
                .orElseThrow(() -> new GeneralException("role-not-found", "Rol no encontrado"));
        UserEntity userEntity = userMapper.toEntity(userCreationDto);
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUser(userEntity);
        userRoleEntity.setRole(role);
        userEntity.setUserRole(userRoleEntity);
        userEntity = userRepository.save(userEntity);
        return this.userMapper.toDto(userEntity);
    }


    @Transactional
    public List<UserDto> searchByDpiOrName(String dpi, String name) {
        List<UserEntity> list = this.userRepository.findByDpiOrName(dpi, name);
        return userMapper.toDto(list);
    }
}
