package org.magasbook.dto.reviewdto;

import jakarta.validation.constraints.*;

public record ReviewUpdateDTO(

        @NotNull
        Long id,

        @NotBlank
        @Size(min = 5, max = 128)
        String title,

        @NotBlank
        @Size(min = 10, max = 1024)
        String description,

        @NotNull
        @Min(1)
        @Max(5)
        Byte rating
) {}
