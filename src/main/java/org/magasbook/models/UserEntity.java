package org.magasbook.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.magasbook.embeddable.Fullname;
import org.magasbook.enums.Preferences;
import org.magasbook.enums.UserRoles;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Fullname fullname;

    @Column(name = "nick_name")
    private String nickname;

    @Column(name = "age", nullable = false, length = 120)
    private Byte age;

    @ElementCollection(targetClass = Preferences.class)
    @CollectionTable(name = "user-preferences", joinColumns = @JoinColumn(name = "user-id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "preferences", nullable = false)
    private List<Preferences> preferences;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "number_phone", nullable = false, unique = true)
    private String numberPhone;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "owner-orders")
    private List<OrderEntity> orders;

    @ManyToMany
    @JoinTable(
            name = "user_markers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductEntity> markers;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRoles role;

    @OneToMany(mappedBy = "owner")
    @JsonManagedReference(value = "owner-reviews")
    private List<ReviewEntity> reviews;
}
