package org.magasbook.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.magasbook.dto.mapper.ReviewMapper;
import org.magasbook.dto.reviewdto.ReviewCreateDTO;
import org.magasbook.dto.reviewdto.ReviewUpdateDTO;
import org.magasbook.models.ProductEntity;
import org.magasbook.models.ReviewEntity;
import org.magasbook.models.UserEntity;
import org.magasbook.repository.ProductRepository;
import org.magasbook.repository.ReviewRepository;
import org.magasbook.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ReviewMapper reviewMapper;

    @Transactional
    public ReviewEntity createReview(ReviewCreateDTO dto) {
        UserEntity user = userRepository.findById(dto.ownerId())
                .orElseThrow(() -> new EntityNotFoundException("User is not found"));

        ProductEntity product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new EntityNotFoundException("Product is not found"));


        ReviewEntity review = reviewMapper.toEntity(dto);
        return reviewRepository.save(review);
    }

    @Transactional
    public void updateReview(ReviewUpdateDTO dto) {
        ReviewEntity review = reviewRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Review is not found"));

        reviewMapper.updateEntity(dto, review);
        reviewRepository.save(review);
    }
}
