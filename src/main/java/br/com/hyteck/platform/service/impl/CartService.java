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

    private final ProductService productService;

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

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    public Cart addCouppon(Long cartId, String couponName) {
        return couponService.findByName(couponName).map(coupon -> {
            Optional<Cart> cartById = cartRepository.findById(cartId);
            return cartById.map(cart -> {
                cart.setCoupon(coupon);
                return cartRepository.save(cart);

            }).orElseThrow(() -> {
                throw new EmptyResultDataAccessException(1);
            });

        }).orElseThrow(() -> {
            throw new EmptyResultDataAccessException(1);
        });

    }

    public Cart addProduct(Long cartId, Long productId) {

        return productService.findById(productId).map(product -> cartRepository.findById(cartId).map(cart -> {
            cart.addProduct(product);
            return cartRepository.save(cart);

        }).orElseThrow(() -> {
            throw new EmptyResultDataAccessException(1);
        })).orElseThrow(() -> {
            throw new EmptyResultDataAccessException(1);
        });
    }


}
