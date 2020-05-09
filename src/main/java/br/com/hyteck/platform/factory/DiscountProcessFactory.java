package br.com.hyteck.platform.factory;

import br.com.hyteck.platform.entity.discounts.DiscountByQuantity;
import br.com.hyteck.platform.entity.discounts.DiscountProgessive;
import br.com.hyteck.platform.frw.AbstractDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscountProcessFactory {

    @Autowired
    private DiscountProgessive discountProgessive;
    @Autowired
    private DiscountByQuantity discountByQuantity;


    public AbstractDiscount chainProcessor() {
        return discountByQuantity
                .linkWith(discountProgessive);
    }
}
