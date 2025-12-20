package org.magasbook.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.magasbook.dto.userdto.UserCreateDTO;
import org.magasbook.dto.userdto.UserResponseDTO;
import org.magasbook.dto.userdto.UserShortInfoDTO;
import org.magasbook.dto.userdto.UserUpdateDTO;
import org.magasbook.embeddable.Fullname;
import org.magasbook.enums.Preferences;
import org.magasbook.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-16T11:42:34-0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Homebrew)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private ProductMapperHelper productMapperHelper;

    @Override
    public UserEntity toEntity(UserCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setFullname( dto.fullname() );
        userEntity.setNickname( dto.nickname() );
        userEntity.setAge( dto.age() );
        List<Preferences> list = dto.preferences();
        if ( list != null ) {
            userEntity.setPreferences( new ArrayList<Preferences>( list ) );
        }
        userEntity.setNumberPhone( dto.numberPhone() );

        return userEntity;
    }

    @Override
    public void toUpdateEntity(UserUpdateDTO dto, UserEntity entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.id() != null ) {
            entity.setId( dto.id() );
        }
        if ( dto.fullname() != null ) {
            entity.setFullname( dto.fullname() );
        }
        if ( dto.nickname() != null ) {
            entity.setNickname( dto.nickname() );
        }
        if ( dto.age() != null ) {
            entity.setAge( dto.age() );
        }
        if ( entity.getPreferences() != null ) {
            List<Preferences> list = dto.preferences();
            if ( list != null ) {
                entity.getPreferences().clear();
                entity.getPreferences().addAll( list );
            }
        }
        else {
            List<Preferences> list = dto.preferences();
            if ( list != null ) {
                entity.setPreferences( new ArrayList<Preferences>( list ) );
            }
        }
        if ( dto.numberPhone() != null ) {
            entity.setNumberPhone( dto.numberPhone() );
        }
    }

    @Override
    public UserResponseDTO toDTO(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        Set<Long> markerIds = null;
        Long id = null;
        Fullname fullname = null;
        String nickname = null;
        Byte age = null;
        List<Preferences> preferences = null;
        String email = null;
        String numberPhone = null;

        markerIds = productMapperHelper.mapProductToProductIds( userEntity.getMarkers() );
        id = userEntity.getId();
        fullname = userEntity.getFullname();
        nickname = userEntity.getNickname();
        age = userEntity.getAge();
        List<Preferences> list = userEntity.getPreferences();
        if ( list != null ) {
            preferences = new ArrayList<Preferences>( list );
        }
        email = userEntity.getEmail();
        numberPhone = userEntity.getNumberPhone();

        Boolean isAdmin = null;

        UserResponseDTO userResponseDTO = new UserResponseDTO( id, fullname, nickname, age, preferences, email, numberPhone, isAdmin, markerIds );

        return userResponseDTO;
    }

    @Override
    public UserEntity toEntityFromShort(UserShortInfoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( dto.id() );
        userEntity.setRole( dto.role() );

        return userEntity;
    }
}
