package org.magasbook.dto.mapper;

import org.magasbook.dto.userdto.UserCreateDTO;
import org.magasbook.dto.userdto.UserResponseDTO;
import org.magasbook.dto.userdto.UserShortInfoDTO;
import org.magasbook.dto.userdto.UserUpdateDTO;
import org.magasbook.models.UserEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = ProductMapperHelper.class)
public interface UserMapper {
    UserEntity toEntity(UserCreateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUpdateEntity(UserUpdateDTO dto, @MappingTarget UserEntity entity);

    @Mapping(source = "markers", target = "markerIds", qualifiedByName = "mapProductToProductIds")
    UserResponseDTO toDTO(UserEntity userEntity);

    UserEntity toEntityFromShort(UserShortInfoDTO dto);
}
