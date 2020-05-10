package br.com.hyteck.platform.controller;

import br.com.hyteck.platform.entity.Cart;
import br.com.hyteck.platform.service.impl.ProductCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartproduct")
@AllArgsConstructor
public class ProductCartController {

    private final ProductCartService productCartService;

    @Operation(summary = "inserir produto ao carrinho")
    @PostMapping(value = "{cartId}/product/{productId}")
    public ResponseEntity<Cart> create(@Parameter @PathVariable Long cartId, @PathVariable Long productId) {
        return ResponseEntity.ok(productCartService.addProduct(cartId, productId));
    }

    @Operation(summary = "remover produto do carrinho")
    @DeleteMapping(value = "{cartId}/product/{productId}")
    public ResponseEntity<Cart> removeProduct(@Parameter @PathVariable Long cartId, @PathVariable Long productId) {
        return ResponseEntity.ok(productCartService.removeProduct(cartId, productId));
    }
}