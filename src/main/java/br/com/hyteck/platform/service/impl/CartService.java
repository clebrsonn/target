package br.com.hyteck.platform.service.impl;

import br.com.hyteck.platform.entity.Cart;
import br.com.hyteck.platform.entity.ProductCart;
import br.com.hyteck.platform.repository.CartRepository;
import br.com.hyteck.platform.service.IServices;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static java.util.Objects.nonNull;

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
        couponService.verifyDiscount(entity);
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
                if (nonNull(cart.getCoupon()) && cart.getCoupon().getPercent().compareTo(coupon.getPercent()) < 0) {
                    cart.setCoupon(coupon);
                    cart = cartRepository.save(cart);
                }
                return cart;

            }).orElseThrow(() -> {
                throw new EmptyResultDataAccessException(1);
            });

        }).orElseThrow(() -> {
            throw new EmptyResultDataAccessException(1);
        });

    }


    public Cart calculateTotal(Long cartId) {

        final var cart = findById(cartId).orElseThrow(() -> {
            throw new EmptyResultDataAccessException(1);
        });
        couponService.verifyDiscount(cart);

        BigDecimal subtotal = calculateSubTotal(cart);
        BigDecimal total = subtotal.subtract(cart.getPercentDiscount().multiply(subtotal));
        cart.setTotal(total);
        return cartRepository.save(cart);
    }

    BigDecimal calculateSubTotal(Cart cart) {

        BigDecimal sumProdCart = new BigDecimal(0);
        for (ProductCart cartProduct : cart.getCartProducts()) {
            sumProdCart = sumProdCart.add(cartProduct.getTotalWithDiscount());

        }

        cart.setSubTotal(sumProdCart);
        return sumProdCart;
    }

}
