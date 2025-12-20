package org.magasbook.dto.userdto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.magasbook.embeddable.Fullname;
import org.magasbook.enums.Preferences;

import java.util.List;

public record UserUpdateDTO(

        @NotNull
        Long id,

        @Valid
        Fullname fullname,

        @NotNull
        @Size(min = 5, max = 25)
        String nickname,

        @NotNull
        @Min(14)
        @Max(120)
        Byte age,

        List<Preferences> preferences,

        @NotBlank
        @Pattern(regexp = "^(\\+7|8)\\d{10}$")
        String numberPhone
) {}
