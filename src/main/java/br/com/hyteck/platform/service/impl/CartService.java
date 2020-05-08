package br.com.hyteck.platform.service.impl;

import br.com.hyteck.platform.entity.Cart;
import br.com.hyteck.platform.repository.CartRepository;
import br.com.hyteck.platform.service.IServices;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService implements IServices<Cart> {


    private final CartRepository cartRepository;

    private final CouponService couponService;

    @Override
    public Page<Cart> findall(Pageable pageable) {
        return cartRepository.findAll(pageable);
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart create(Cart entity) {
        return cartRepository.save(entity);
    }

    public void addCouppon(Long cartId, String couponName) {
        couponService.findByName(couponName).ifPresentOrElse(coupon -> {
            final var cartById = cartRepository.findById(cartId);
            cartById.ifPresentOrElse(cart -> {
                cart.setCoupon(coupon);
                cartRepository.save(cart);
            }, () -> {
                throw new EmptyResultDataAccessException(1);
            });

        }, () -> {
            throw new EmptyResultDataAccessException(1);
        });

    }

    ;

}
