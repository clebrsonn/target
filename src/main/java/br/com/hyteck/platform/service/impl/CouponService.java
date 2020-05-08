package br.com.hyteck.platform.service.impl;

import br.com.hyteck.platform.entity.Coupon;
import br.com.hyteck.platform.repository.CouponRepository;
import br.com.hyteck.platform.service.IServices;
import jdk.jshell.spi.ExecutionControl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class CouponService implements IServices<Coupon> {


    private final CouponRepository couponRepository;


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
}
