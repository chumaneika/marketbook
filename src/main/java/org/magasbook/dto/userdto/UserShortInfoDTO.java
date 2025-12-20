package org.magasbook.dto.userdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.magasbook.enums.UserRoles;

public record UserShortInfoDTO(
        @NotNull
        Long id,

        @NotBlank
        UserRoles role
) {}
