package br.com.hyteck.platform.entity;

import br.com.hyteck.platform.frw.AbstractDiscount;
import br.com.hyteck.platform.frw.AbstractEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema
@Entity
public class Cart extends AbstractEntity<String> {

    @OneToMany(mappedBy = "cart")
    @Schema
    private List<ProductCart> productCart;

    @ManyToOne
    @Schema
    private AbstractDiscount coupon;

    @Schema
    private BigDecimal valueDiscount;

    private BigDecimal total;

    private BigDecimal subTotal;


    public Cart addProduct(Product product) {
        productCart.forEach(pCart -> {
            if (pCart.getId().equals(product.getId())) {
                pCart.setQuantity(pCart.getQuantity() + 1);
            } else {
                getProductCart().add(ProductCart.builder()
                        .product(product)
                        .cart(this)
                        .build()
                );
            }


        });
        return this;

    }

}
