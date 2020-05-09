package br.com.hyteck.platform.entity.discounts;

import br.com.hyteck.platform.entity.Cart;
import br.com.hyteck.platform.frw.AbstractDiscount;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DiscountProgessive extends AbstractDiscount {
    @Override
    public Cart applyDiscount(Cart cart) {
        BigDecimal value = new BigDecimal(0);
        if (cart.getTotal().compareTo(new BigDecimal("10000")) >= 0) {
            value =cart.getPercentDiscount().add(new BigDecimal("0.1"));
        } else if (cart.getTotal().compareTo(new BigDecimal("5000")) >= 0) {
            value =cart.getPercentDiscount().add(new BigDecimal("0.07"));
        } else if (cart.getTotal().compareTo(new BigDecimal("1000")) >= 0) {
            value =cart.getPercentDiscount().add(new BigDecimal("0.05"));
        }
        cart.setPercentDiscount(value);
        return cart;
    }
}

