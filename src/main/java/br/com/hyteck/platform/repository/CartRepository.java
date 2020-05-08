package br.com.hyteck.platform.repository;

import br.com.hyteck.platform.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
