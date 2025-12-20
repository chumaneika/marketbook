package org.magasbook.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.magasbook.enums.StatusProduct;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 30, unique = true)
    private String title;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "product-reviews")
    private List<ReviewEntity> reviews;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference(value = "category-products")
    private CategoryEntity category;

    @Column(name = "photo")
    private String photo;

    @Column(name = "author", nullable = false, length = 25)
    private String author;

    @ManyToMany(mappedBy = "markers")
    private Set<UserEntity> users;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "product-orderItems")
    private List<OrderItemEntity> orderItems;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "status", nullable = false)
    private StatusProduct status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "weight")
    private Double weight;

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
    }
}
