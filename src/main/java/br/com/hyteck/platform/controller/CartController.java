package br.com.hyteck.platform.controller;

import br.com.hyteck.platform.entity.Cart;
import br.com.hyteck.platform.framework.AbstractController;
import br.com.hyteck.platform.service.IServices;
import br.com.hyteck.platform.service.impl.CartService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
@Tag(name = "Api de Carrinho")
public class CartController extends AbstractController<Cart> {

    private final CartService cartService;

    @Override
    protected IServices<Cart> getService() {
        return cartService;
    }

    @Override
    @Hidden
    public ResponseEntity<Cart> update(Long id, @Valid Cart Entity) {
        return super.update(id, Entity);
    }

    @Override
    @Hidden
    public ResponseEntity<Cart> create(@Valid Cart cart) {
        return super.create(cart);
    }

    @GetMapping(value = "{cartId}/coupon/{couponName}")
    public ResponseEntity<Cart> addCoupon(@Parameter @PathVariable Long cartId, @PathVariable String couponName) {
        Cart cart = cartService.addCouppon(cartId, couponName);
        return ResponseEntity.ok(cart);
    }

    @GetMapping(value = "{id}/total")
    public ResponseEntity<Cart> calculateToral(@Parameter @PathVariable Long id) {
        return ResponseEntity.ok(cartService.calculateTotal(id));
    }


}