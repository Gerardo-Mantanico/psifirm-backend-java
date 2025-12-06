package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.user.UserCreationDto;
import com.pifirm.domain.dto.user.UserDto;
import com.pifirm.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserEntity userEntity);
    List<UserDto> toDto(Iterable<UserEntity> movieEntityList);

    UserEntity toEntity(UserDto userDto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", ignore = true)
    UserEntity toEntity(UserCreationDto userDto);
}
