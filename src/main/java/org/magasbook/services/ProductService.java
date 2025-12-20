package org.magasbook.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.magasbook.dto.mapper.ProductMapper;
import org.magasbook.dto.productdto.ProductCreateDTO;
import org.magasbook.dto.productdto.ProductUpdateDTO;
import org.magasbook.enums.StatusProduct;
import org.magasbook.models.CategoryEntity;
import org.magasbook.models.ProductEntity;
import org.magasbook.repository.CategoryRepository;
import org.magasbook.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;


    @Transactional
    public ProductEntity createProduct(ProductCreateDTO dto) {
        CategoryEntity category = categoryRepository.findById(dto.category_id())
                .orElseThrow(() -> new EntityNotFoundException("Category is not found"));

        if (productRepository.existsByTitle(dto.title())) {
            throw new IllegalStateException("Product with this title already exists");
        }

        // todo проверка валидации фото
        // todo отрегулировать валидацию weight в сервисе продукта
        return productRepository.save(productMapper.toEntity(dto));
    }

    @Transactional
    public void updateProduct(ProductUpdateDTO dto) {
        ProductEntity product = productRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        if (productRepository.existsByTitleAndIdNot(dto.title(), product.getId())) {
            throw new IllegalStateException("Product with this title already exists");
        }

        productMapper.toUpdateEntity(dto, product);
    }

    @Transactional
    public void softDelete(Long id, StatusProduct status) throws IllegalStateException{
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product is not found"));

        product.setStatus(status);
    }

}
