package br.com.hyteck.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class Cart extends AbstractEntity<String>{

    @OneToMany(mappedBy = "cart")
    private List<ProductCart> productCart;

    @ManyToOne
    private AbstractDiscount coupon;

    private BigDecimal valueDiscount;

}
