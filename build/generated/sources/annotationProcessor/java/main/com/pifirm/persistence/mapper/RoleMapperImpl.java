package com.pifirm.persistence.mapper;

import com.pifirm.domain.dto.role.RoleDto;
import com.pifirm.persistence.entity.RoleEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-05T16:50:51-0600",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 25.0.1 (Arch Linux)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDto toDto(RoleEntity roleEntity) {
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

    @Override
    public List<RoleDto> toDto(List<RoleEntity> roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        List<RoleDto> list = new ArrayList<RoleDto>( roleEntity.size() );
        for ( RoleEntity roleEntity1 : roleEntity ) {
            list.add( toDto( roleEntity1 ) );
        }

        return list;
    }
}
