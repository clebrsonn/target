package br.com.hyteck.platform.controller;

import br.com.hyteck.platform.entity.Cart;
import br.com.hyteck.platform.frw.AbstractController;
import br.com.hyteck.platform.service.IServices;
import br.com.hyteck.platform.service.impl.CartService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController extends AbstractController<Cart> {

    private final CartService cartService;

    @GetMapping(value = "{cartId}/coupon/{couponName}")
    public ResponseEntity<Cart> addCoupon(@Parameter @PathVariable Long cartId, @PathVariable String couponName) {
        Cart cart = cartService.addCouppon(cartId, couponName);
        return ResponseEntity.ok(cart);
    }

    @GetMapping(value = "{cartId}/product/{productId}")
    public ResponseEntity<Cart> addProduct(@Parameter @PathVariable Long cartId, @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.addProduct(cartId, productId));
    }

    @Override
    protected IServices<Cart> getService() {
        return cartService;
    }
}