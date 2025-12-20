package org.magasbook.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.magasbook.enums.StatusOrder;

import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "owner-orders")
    private UserEntity owner;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "order-orderItems")
    private List<OrderItemEntity> orderItems;

    @Column(name = "cost", nullable = false)
    private Integer cost;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusOrder status;

    @PrePersist
    @PreUpdate
    public void manageCost() {
        if (orderItems == null || orderItems.isEmpty()) {
            this.cost = 0;
            return;
        }

        this.cost = orderItems.stream()
                .mapToInt(orderItem -> {
                    int quantity = orderItem.getQuantity() != null ? orderItem.getQuantity() : 0;
                    int price = (orderItem.getProduct() != null && orderItem.getProduct().getPrice() != null)
                            ? orderItem.getProduct().getPrice()
                            : 0;
                    return quantity * price;
                })
                .sum();
    }
}
