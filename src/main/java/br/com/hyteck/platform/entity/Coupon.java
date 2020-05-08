package br.com.hyteck.platform.entity;

import br.com.hyteck.platform.frw.AbstractDiscount;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema
@Table(name = "COUPON")
public class Coupon extends AbstractDiscount {


    @Schema
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "AFFILIATED_COUPON", joinColumns = {@JoinColumn(name = "ID_COUPON")},
            inverseJoinColumns = {@JoinColumn(name = "ID_AFFILIATED")})
    @JsonBackReference
    private Set<Partner> affiliates;


    @Override
    public Cart applyDiscount(Cart cart) {

        if (cart.getCoupon() != null && cart.getCoupon().getPercent().compareTo(this.getPercent()) < 0) {
            cart.setCoupon(this);

        }

        return cart;
    }
}
