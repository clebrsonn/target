package br.com.hyteck.platform.service.impl;

import br.com.hyteck.platform.entity.Cart;
import br.com.hyteck.platform.entity.ProductCart;
import br.com.hyteck.platform.repository.ProductCartRepository;
import br.com.hyteck.platform.service.IServices;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductCartService implements IServices<ProductCart> {


    private final ProductCartRepository productCartRepository;

    private final CouponService couponService;
    private final CartService cartService;
    private final ProductService productService;

    @Override
    public Page<ProductCart> findall(Pageable pageable) {
        return productCartRepository.findAll(pageable);
    }

    @Override
    public Optional<ProductCart> findById(Long id) {
        return productCartRepository.findById(id);
    }

    @Override
    public ProductCart create(ProductCart entity) {
        couponService.verifyDiscount(entity.getCart());
        return productCartRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        productCartRepository.deleteById(id);
    }

    public Cart addProduct(Long cartId, Long productId) {

        return productService.findById(productId).map(product -> cartService.findById(cartId).map(cart -> {
                    cart = cart.addProduct(product);
                    couponService.verifyDiscount(cart);
                    productCartRepository.saveAll(cart.getCartProducts());
                    return cartService.calculateTotal(cart.getId());

                }).orElse(cartService.create(Cart.builder()
                        .cartProducts(Collections.singletonList(ProductCart.builder().product(product).build()))
                .build()))
        ).orElseThrow(() -> {
            throw new EmptyResultDataAccessException(1);
        });
    }

    public Cart removeProduct(Long cartId, Long productId) {

        return productService.findById(productId).map(product -> cartService.findById(cartId).map(cart -> {
            cart = cart.removeProduct(product);
            return cartService.create(cart);

        }).orElseThrow(() -> {
            throw new EmptyResultDataAccessException(1);
        })).orElseThrow(() -> {
            throw new EmptyResultDataAccessException(1);
        });
    }
}
