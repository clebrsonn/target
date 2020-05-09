package br.com.hyteck.platform.service.impl;

import br.com.hyteck.platform.entity.Cart;
import br.com.hyteck.platform.entity.discounts.Coupon;
import br.com.hyteck.platform.factory.DiscountProcessFactory;
import br.com.hyteck.platform.frw.AbstractDiscount;
import br.com.hyteck.platform.repository.CouponRepository;
import br.com.hyteck.platform.service.IServices;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class CouponService implements IServices<Coupon> {


    private final CouponRepository couponRepository;

    private final DiscountProcessFactory discountProcessFactory;


    public Page<Coupon> findall(Pageable pageable) {

        return couponRepository.findAll(pageable);
    }


    public Optional<Coupon> findById(Long id) {
        return couponRepository.findById(id);
    }

    public Coupon create(Coupon product) {
        return couponRepository.save(product);
    }

    public Optional<Coupon> findByName(String couponName) {
        return couponRepository.findByName(couponName);
    }

    @Override
    public void delete(Long id) {
        couponRepository.deleteById(id);
    }

    public void verifyDiscount(Cart entity) {
        final var discount = discountProcessFactory.chainProcessor();

        discount.verifyDiscount(entity);
    }
}
