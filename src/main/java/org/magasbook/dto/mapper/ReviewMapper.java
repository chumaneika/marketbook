package org.magasbook.dto.mapper;

import org.magasbook.dto.reviewdto.ReviewCreateDTO;
import org.magasbook.dto.reviewdto.ReviewUpdateDTO;
import org.magasbook.models.ReviewEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewEntity toEntity(ReviewCreateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ReviewUpdateDTO dto, @MappingTarget ReviewEntity entity);
}
