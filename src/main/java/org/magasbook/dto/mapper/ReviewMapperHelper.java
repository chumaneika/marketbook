package org.magasbook.dto.mapper;

import lombok.AllArgsConstructor;
import org.magasbook.models.ReviewEntity;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ReviewMapperHelper {

    @Named("mapReviewsToReviewIds")
    public List<Long> mapReviewsToReviewIds(List<ReviewEntity> reviews) {
        if (reviews == null || reviews.isEmpty()) return new ArrayList<>();
        return reviews.stream()
                .map(ReviewEntity::getId)
                .collect(Collectors.toList());
    }
}
