package br.com.hyteck.platform.entity.discounts;

import br.com.hyteck.platform.entity.Cart;
import br.com.hyteck.platform.entity.Partner;
import br.com.hyteck.platform.framework.AbstractDiscount;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema
public class Coupon extends AbstractDiscount {


    @Schema
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "PARTNER_COUPON", joinColumns = {@JoinColumn(name = "ID_COUPON")},
            inverseJoinColumns = {@JoinColumn(name = "ID_PARTNER")})
    @JsonBackReference
    private Set<Partner> affiliates;


    @Override
    public Cart applyDiscount(Cart cart) {
        cart.setPercentDiscount(cart.getCoupon().getPercent().divide(new BigDecimal(100)));
        return cart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coupon)) return false;
        if (!super.equals(o)) return false;
        Coupon coupon = (Coupon) o;
        return getAffiliates().equals(coupon.getAffiliates());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAffiliates());
    }
}
