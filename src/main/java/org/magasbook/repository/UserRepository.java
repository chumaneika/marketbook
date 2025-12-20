package org.magasbook.repository;

import org.magasbook.dto.productdto.ProductSummaryDTO;
import org.magasbook.models.ProductEntity;
import org.magasbook.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByNickname(String nickname);

    boolean existsByNumberPhone(String numberPhone);

    Optional<UserEntity> findByNumberPhone(String numberPhone);
}
