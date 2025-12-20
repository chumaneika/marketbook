package org.magasbook.controllers;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.magasbook.dto.productdto.ProductCreateDTO;
import org.magasbook.dto.productdto.ProductUpdateDTO;
import org.magasbook.enums.StatusProduct;
import org.magasbook.models.ProductEntity;
import org.magasbook.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductEntity> createProduct(@Valid @RequestBody ProductCreateDTO dto) {
        ProductEntity createdProduct = productService.createProduct(dto);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/update-product")
    public ResponseEntity<Void> updateProduct(@Valid @RequestBody ProductUpdateDTO dto) {
        productService.updateProduct(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<Void> deleteProduct(
            @RequestParam Long id,
            @RequestParam StatusProduct status) {
        productService.softDelete(id, status);
        return ResponseEntity.noContent().build();
    }
}
