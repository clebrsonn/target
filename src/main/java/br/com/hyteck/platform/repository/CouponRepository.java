package br.com.hyteck.platform.repository;

import br.com.hyteck.platform.entity.discounts.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByName(String name);
}
