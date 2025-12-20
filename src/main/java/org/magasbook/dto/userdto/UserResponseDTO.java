package org.magasbook.dto.userdto;

import org.magasbook.embeddable.Fullname;
import org.magasbook.enums.Preferences;
import java.util.List;
import java.util.Set;

public record UserResponseDTO(
        Long id,
        Fullname fullname,
        String nickname,
        Byte age,
        List<Preferences> preferences,
        String email,
        String numberPhone,
        Boolean isAdmin,

        Set<Long> markerIds
) {}
