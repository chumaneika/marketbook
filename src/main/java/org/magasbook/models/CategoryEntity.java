package org.magasbook.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 25)
    private String title;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference(value = "category-children")
    private CategoryEntity parent;

    @OneToMany(mappedBy = "parent")
    @JsonManagedReference(value = "category-children")
    private List<CategoryEntity> children = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    @JsonManagedReference(value = "category-products")
    private Set<ProductEntity> products;

}
