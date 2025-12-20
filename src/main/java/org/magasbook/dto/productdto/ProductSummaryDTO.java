package org.magasbook.dto.productdto;

import jakarta.validation.constraints.*;

public record ProductSummaryDTO(
        @NotNull
        @Positive
        Long id,

        @NotBlank
        @Size(min = 7, max = 30)
        String title,

        @Positive
        @NotNull
        Integer price,

        @NotNull
        String author,

        @NotBlank
        String photo,

        @NotNull
        @PositiveOrZero
        @Max(10)
        Double weight
) {}
