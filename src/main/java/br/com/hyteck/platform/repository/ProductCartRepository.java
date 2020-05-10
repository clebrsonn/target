package br.com.hyteck.platform.repository;

import br.com.hyteck.platform.entity.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
}
