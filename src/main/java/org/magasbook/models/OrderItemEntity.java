package org.magasbook.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference(value = "order-orderItems")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "product-orderItems")
    private ProductEntity product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
