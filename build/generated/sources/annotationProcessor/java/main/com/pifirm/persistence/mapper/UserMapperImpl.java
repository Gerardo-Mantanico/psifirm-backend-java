package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.role.RoleDto;
import com.pifirm.domain.dto.user.UserCreationDto;
import com.pifirm.domain.dto.user.UserDto;
import com.pifirm.persistence.entity.RoleEntity;
import com.pifirm.persistence.entity.UserEntity;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-11T12:04:24-0600",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 25.0.1 (Arch Linux)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        Long id = null;
        String firstname = null;
        String lastname = null;
        String email = null;
        String phoneNumber = null;
        Instant createdAt = null;
        Instant updatedAt = null;
        Boolean active = null;
        RoleDto role = null;
        Boolean use2fa = null;

        id = userEntity.getId();
        firstname = userEntity.getFirstname();
        lastname = userEntity.getLastname();
        email = userEntity.getEmail();
        phoneNumber = userEntity.getPhoneNumber();
        createdAt = userEntity.getCreatedAt();
        updatedAt = userEntity.getUpdatedAt();
        active = userEntity.getActive();
        role = roleEntityToRoleDto( userEntity.getRole() );
        use2fa = userEntity.getUse2fa();

        UserDto userDto = new UserDto( id, firstname, lastname, email, phoneNumber, createdAt, updatedAt, active, role, use2fa );

        return userDto;
    }

    @Override
    public List<UserDto> toDto(Iterable<UserEntity> movieEntityList) {
        if ( movieEntityList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>();
        for ( UserEntity userEntity : movieEntityList ) {
            list.add( toDto( userEntity ) );
        }

        return list;
    }

    @Override
    public UserEntity toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUse2fa( userDto.use2fa() );
        userEntity.setId( userDto.id() );
        userEntity.setFirstname( userDto.firstname() );
        userEntity.setLastname( userDto.lastname() );
        userEntity.setEmail( userDto.email() );
        userEntity.setPhoneNumber( userDto.phoneNumber() );
        userEntity.setActive( userDto.active() );
        userEntity.setCreatedAt( userDto.createdAt() );
        userEntity.setUpdatedAt( userDto.updatedAt() );

        return userEntity;
    }

    @Override
    public UserEntity toEntity(UserCreationDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setFirstname( userDto.getFirstname() );
        userEntity.setLastname( userDto.getLastname() );
        userEntity.setEmail( userDto.getEmail() );
        userEntity.setPhoneNumber( userDto.getPhoneNumber() );
        userEntity.setPassword( userDto.getPassword() );

        return userEntity;
    }

    protected RoleDto roleEntityToRoleDto(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = roleEntity.getId();
        name = roleEntity.getName();
        description = roleEntity.getDescription();

        RoleDto roleDto = new RoleDto( id, name, description );

        return roleDto;
    }
}
