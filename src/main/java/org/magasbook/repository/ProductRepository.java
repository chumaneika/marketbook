package org.magasbook.repository;

import org.magasbook.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    boolean existsByTitle(String title);
    ProductEntity findByTitle(String title);
    boolean existsByTitleAndIdNot(String title, Long id);
}
