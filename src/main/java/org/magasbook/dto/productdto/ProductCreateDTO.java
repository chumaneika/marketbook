package org.magasbook.dto.productdto;

import jakarta.validation.constraints.*;

public record ProductCreateDTO(

        @NotBlank
        @Size(min = 7, max = 30)
        String title,

        @NotBlank
        @Size(min = 30, max = 255)
        String description,

        @Positive
        @NotNull
        Integer price,

        @NotNull
        @Positive
        Long category_id,

        @NotBlank
        String photo,

        @NotNull
        String author,

        @PositiveOrZero
        @NotNull
        Integer quantity,

        @NotNull
        @PositiveOrZero
        @Max(10) Double weight
) {}
