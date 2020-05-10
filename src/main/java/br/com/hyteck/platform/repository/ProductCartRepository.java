package br.com.hyteck.platform.repository;

import br.com.hyteck.platform.entity.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
    Optional<ProductCart> findByCartIdAndProductId(Long cartId, Long productId);
}
