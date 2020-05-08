package br.com.hyteck.platform.repository;

import br.com.hyteck.platform.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
