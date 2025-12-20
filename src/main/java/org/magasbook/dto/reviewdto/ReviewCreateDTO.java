package org.magasbook.dto.reviewdto;

import jakarta.validation.constraints.*;

public record ReviewCreateDTO(

        @NotBlank
        @Size(min = 5, max = 128)
        String title,

        @NotBlank
        @Size(min = 5, max = 1024)
        String description,

        @NotNull
        @Min(1)
        @Max(5)
        Byte rating,

        @NotNull
        Long ownerId,

        @NotNull
        Long productId
) {}
