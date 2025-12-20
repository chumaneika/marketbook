package org.magasbook.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "owner-reviews")
    private UserEntity owner;

    @Column(name = "title", nullable = false, length = 60)
    private String title;

    @Column(name = "description", length = 60)
    private String description;

    @Column(name = "rating", nullable = false)
    private Byte rating;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "product-reviews")
    private ProductEntity product;

}
