package br.com.hyteck.platform.entity.discounts;

import br.com.hyteck.platform.entity.Cart;
import br.com.hyteck.platform.framework.AbstractDiscount;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DiscountByQuantity extends AbstractDiscount {
    @Override
    public Cart applyDiscount(Cart cart) {
        cart.getCartProducts().forEach(productCart -> {
            if (productCart.getQuantity() >= 10) {
                productCart.setDiscount(new BigDecimal("0.1"));
            }

        });
        return cart;
    }
}
