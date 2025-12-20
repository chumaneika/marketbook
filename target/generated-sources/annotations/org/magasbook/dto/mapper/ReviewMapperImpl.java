package org.magasbook.dto.mapper;

import javax.annotation.processing.Generated;
import org.magasbook.dto.reviewdto.ReviewCreateDTO;
import org.magasbook.dto.reviewdto.ReviewUpdateDTO;
import org.magasbook.models.ReviewEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-16T11:42:34-0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Homebrew)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewEntity toEntity(ReviewCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ReviewEntity reviewEntity = new ReviewEntity();

        reviewEntity.setTitle( dto.title() );
        reviewEntity.setDescription( dto.description() );
        reviewEntity.setRating( dto.rating() );

        return reviewEntity;
    }

    @Override
    public void updateEntity(ReviewUpdateDTO dto, ReviewEntity entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.id() != null ) {
            entity.setId( dto.id() );
        }
        if ( dto.title() != null ) {
            entity.setTitle( dto.title() );
        }
        if ( dto.description() != null ) {
            entity.setDescription( dto.description() );
        }
        if ( dto.rating() != null ) {
            entity.setRating( dto.rating() );
        }
    }
}
