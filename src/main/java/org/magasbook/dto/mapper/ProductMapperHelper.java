package org.magasbook.dto.mapper;

import lombok.AllArgsConstructor;
import org.magasbook.models.ProductEntity;
import org.magasbook.repository.ProductRepository;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductMapperHelper {
    private ProductRepository productRepository;

    @Named("mapProductToProductIds")
    public Set<Long> mapProductToProductIds(Set<ProductEntity> products) {
        if (products == null) return null;
        return products.stream()
                .map(ProductEntity::getId)
                .collect(Collectors.toSet());
    }
}
