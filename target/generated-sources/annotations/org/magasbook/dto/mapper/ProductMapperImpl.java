package org.magasbook.dto.mapper;

import java.util.List;
import javax.annotation.processing.Generated;
import org.magasbook.dto.productdto.ProductCreateDTO;
import org.magasbook.dto.productdto.ProductResponseDTO;
import org.magasbook.dto.productdto.ProductSummaryDTO;
import org.magasbook.dto.productdto.ProductUpdateDTO;
import org.magasbook.models.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-16T11:42:34-0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Homebrew)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private ReviewMapperHelper reviewMapperHelper;

    @Override
    public ProductEntity toEntity(ProductCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setTitle( dto.title() );
        productEntity.setDescription( dto.description() );
        productEntity.setPrice( dto.price() );
        productEntity.setPhoto( dto.photo() );
        productEntity.setAuthor( dto.author() );
        productEntity.setQuantity( dto.quantity() );
        productEntity.setWeight( dto.weight() );

        return productEntity;
    }

    @Override
    public void toUpdateEntity(ProductUpdateDTO dto, ProductEntity entity) {
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
        if ( dto.price() != null ) {
            entity.setPrice( dto.price() );
        }
        if ( dto.photo() != null ) {
            entity.setPhoto( dto.photo() );
        }
        if ( dto.author() != null ) {
            entity.setAuthor( dto.author() );
        }
        if ( dto.quantity() != null ) {
            entity.setQuantity( dto.quantity() );
        }
        if ( dto.weight() != null ) {
            entity.setWeight( dto.weight() );
        }
    }

    @Override
    public ProductResponseDTO toDTO(ProductEntity entity) {
        if ( entity == null ) {
            return null;
        }

        List<Long> reviewIds = null;
        Long id = null;
        String title = null;
        String description = null;
        Integer price = null;
        String author = null;
        String photo = null;

        reviewIds = reviewMapperHelper.mapReviewsToReviewIds( entity.getReviews() );
        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        price = entity.getPrice();
        author = entity.getAuthor();
        photo = entity.getPhoto();

        Long categoryId = null;

        ProductResponseDTO productResponseDTO = new ProductResponseDTO( id, title, description, price, author, photo, categoryId, reviewIds );

        return productResponseDTO;
    }

    @Override
    public ProductSummaryDTO toSummaryProduct(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        Integer price = null;
        String author = null;
        String photo = null;
        Double weight = null;

        id = productEntity.getId();
        title = productEntity.getTitle();
        price = productEntity.getPrice();
        author = productEntity.getAuthor();
        photo = productEntity.getPhoto();
        weight = productEntity.getWeight();

        ProductSummaryDTO productSummaryDTO = new ProductSummaryDTO( id, title, price, author, photo, weight );

        return productSummaryDTO;
    }
}
