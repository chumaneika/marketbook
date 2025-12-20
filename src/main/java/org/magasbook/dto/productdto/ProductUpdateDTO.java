package org.magasbook.dto.productdto;

import jakarta.validation.constraints.*;

public record ProductUpdateDTO(

        @NotNull
        Long id,

        @NotBlank
        @Size(min = 7, max = 30)
        String title,

        @NotBlank
        @Size(min = 30, max = 255)
        String description,

        @Positive
        @NotNull
        Integer price,

        @NotBlank
        String author,

        @NotBlank
        String photo,

        @NotNull
        @Positive
        Long categoryId,

        @PositiveOrZero
        @NotNull
        Integer quantity,

        @NotNull
        @PositiveOrZero
        @Max(10)
        Double weight
)
{}
