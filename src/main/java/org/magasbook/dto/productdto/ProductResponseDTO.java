package org.magasbook.dto.productdto;

import java.util.List;

public record ProductResponseDTO(
        Long id,
        String title,
        String description,
        Integer price,
        String author,
        String photo,
        Long categoryId,
        List<Long> reviewIds
) {}
