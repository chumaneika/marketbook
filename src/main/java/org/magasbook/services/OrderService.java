package org.magasbook.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.magasbook.dto.mapper.ProductMapper;
import org.magasbook.dto.productdto.ProductSummaryDTO;
import org.magasbook.dto.userdto.UserResponseDTO;
import org.magasbook.enums.StatusOrder;
import org.magasbook.models.OrderEntity;
import org.magasbook.models.ProductEntity;
import org.magasbook.models.UserEntity;
import org.magasbook.repository.OrderRepository;
import org.magasbook.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    public void archiveOrder(Long id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order is not found "));

        if (order.getStatus() == StatusOrder.COMPLETED) {
            throw new IllegalStateException("The order is already completed");

        }

        order.setStatus(StatusOrder.COMPLETED);
    }

    public void cleanCart(Long id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order is not found "));

        if (order.getStatus() == StatusOrder.COMPLETED) {
            throw new IllegalStateException("Cart cannot be cleaned because the order is already completed");
        }

        order.getOrderItems().clear();
        orderRepository.save(order);
    }

    public List<ProductSummaryDTO> getProductsCurrentCart(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User is not found"));

        OrderEntity currentOrder = user.getOrders().stream()
                .filter(x -> x.getStatus() == StatusOrder.WAITING)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Current cart (WAITING order) is not found"));

        return currentOrder.getOrderItems().stream()
                .map(orderItem -> productMapper.toSummaryProduct(orderItem.getProduct()))
                .toList();
    }
}
