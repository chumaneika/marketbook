package org.magasbook.dto.mapper;

import org.magasbook.dto.productdto.ProductCreateDTO;
import org.magasbook.dto.productdto.ProductResponseDTO;
import org.magasbook.dto.productdto.ProductSummaryDTO;
import org.magasbook.dto.productdto.ProductUpdateDTO;
import org.magasbook.models.ProductEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = ReviewMapperHelper.class)
public interface ProductMapper {
    ProductEntity toEntity(ProductCreateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUpdateEntity(ProductUpdateDTO dto, @MappingTarget ProductEntity entity);

    @Mapping(source = "reviews", target = "reviewIds", qualifiedByName = "mapReviewsToReviewIds")
    ProductResponseDTO toDTO(ProductEntity entity);

    ProductSummaryDTO toSummaryProduct(ProductEntity productEntity);
}
