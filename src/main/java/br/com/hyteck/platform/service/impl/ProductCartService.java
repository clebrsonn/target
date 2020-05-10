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

import javax.transaction.Transactional;
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

    @Transactional
    public Cart addProduct(Long cartId, Long productId) {
        Cart cart;
        final var productOptional = productService.findById(productId);
        if (productOptional.isPresent()) {
            final var oCart = cartService.findById(cartId);
            if (oCart.isPresent()) {

                cart = oCart.get().addProduct(productOptional.get());
                couponService.verifyDiscount(cart);
                cart.setCartProducts(productCartRepository.saveAll(cart.getCartProducts()));
                cartService.calculateTotal(cart.getId());
            } else {

                cart = Cart.builder().build();
                cart = cartService.create(cart);
                cart = cart.addProduct(productOptional.get());
                cart.setCartProducts(productCartRepository.saveAll(cart.getCartProducts()));

            }
            return cart;


        } else {
            throw new EmptyResultDataAccessException(1);
        }
    }

    @Transactional
    public Cart removeProduct(Long cartId, Long productId) {
        return productCartRepository.findByCartIdAndProductId(cartId, productId).map(productCart -> {
            if (productCart.getQuantity() > 1) {
                productCart.setQuantity(productCart.getQuantity() - 1);
                productCartRepository.save(productCart);
            } else {
                productCartRepository.delete(productCart);
            }


            return cartService.calculateTotal(cartId);
        }).orElseThrow(() -> {
            throw new EmptyResultDataAccessException(1);
        });
    }
}
