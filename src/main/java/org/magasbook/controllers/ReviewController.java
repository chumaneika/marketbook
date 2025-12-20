package org.magasbook.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.magasbook.dto.reviewdto.ReviewCreateDTO;
import org.magasbook.dto.reviewdto.ReviewUpdateDTO;
import org.magasbook.models.ReviewEntity;
import org.magasbook.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<ReviewEntity> createReview(@Valid @RequestBody ReviewCreateDTO dto) {
        ReviewEntity review = reviewService.createReview(dto);
        return ResponseEntity.ok(review);
    }

    @PutMapping("/update-review")
    public ResponseEntity<Void> updateReview(@Valid @RequestBody ReviewUpdateDTO dto) {
        reviewService.updateReview(dto);
        return ResponseEntity.noContent().build();
    }
}
