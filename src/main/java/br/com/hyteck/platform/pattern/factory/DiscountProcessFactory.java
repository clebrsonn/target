package br.com.hyteck.platform.pattern.factory;

import br.com.hyteck.platform.entity.discounts.DiscountByQuantity;
import br.com.hyteck.platform.entity.discounts.DiscountProgessive;
import br.com.hyteck.platform.framework.AbstractDiscount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DiscountProcessFactory {

    private final DiscountProgessive discountProgessive;
    private final DiscountByQuantity discountByQuantity;


    public AbstractDiscount chainProcessor() {
        discountByQuantity
                .linkWith(discountProgessive);

        return discountByQuantity;
    }
}
