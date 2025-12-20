package org.magasbook.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.magasbook.dto.productdto.ProductSummaryDTO;
import org.magasbook.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PutMapping("/archive-order/{id}")
    public ResponseEntity<Void> archiveOrder(@PathVariable("id") Long id) {
        orderService.archiveOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/clean-cart/{id}")
    public ResponseEntity<Void> cleanCart(@PathVariable("id") Long id) {
        orderService.cleanCart(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-cart/{id}")
    public ResponseEntity<List<ProductSummaryDTO>> getProductsCurrentCart(@PathVariable("id") Long userId) {
        List<ProductSummaryDTO> currentOrder = orderService.getProductsCurrentCart(userId);
        return ResponseEntity.ok(currentOrder);
    }
}
