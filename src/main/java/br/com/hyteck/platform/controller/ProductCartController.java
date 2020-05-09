package br.com.hyteck.platform.controller;

import br.com.hyteck.platform.entity.Cart;
import br.com.hyteck.platform.entity.ProductCart;
import br.com.hyteck.platform.framework.AbstractController;
import br.com.hyteck.platform.service.IServices;
import br.com.hyteck.platform.service.impl.ProductCartService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartproduct")
@AllArgsConstructor
public class ProductCartController extends AbstractController<ProductCart> {

    private final ProductCartService productCartService;

    @Override
    protected IServices<ProductCart> getService() {
        return productCartService;
    }


    @PostMapping(value = "{cartId}/product/{productId}")
    public ResponseEntity<Cart> create(@Parameter @PathVariable Long cartId, @PathVariable Long productId) {
        return ResponseEntity.ok(productCartService.addProduct(cartId, productId));
    }


    @DeleteMapping(value = "{cartId}/product/{productId}")
    public ResponseEntity<Cart> removeProduct(@Parameter @PathVariable Long cartId, @PathVariable Long productId) {
        return ResponseEntity.ok(productCartService.removeProduct(cartId, productId));
    }
}