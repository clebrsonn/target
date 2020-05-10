package br.com.hyteck.platform.entity;

import br.com.hyteck.platform.framework.AbstractDiscount;
import br.com.hyteck.platform.framework.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @Schema
    @JsonManagedReference
    @Builder.Default
    private List<ProductCart> cartProducts = new ArrayList<>();

    @ManyToOne
    @Schema
    private AbstractDiscount coupon;

    @Schema
    @Builder.Default
    private BigDecimal percentDiscount = new BigDecimal(0);

    @Schema
    @Builder.Default
    private BigDecimal total = new BigDecimal(0);

    @Schema
    @Builder.Default
    private BigDecimal subTotal = new BigDecimal(0);


    public Cart addProduct(Product product) {
        if (cartProducts.isEmpty()) {
            cartProducts.add(ProductCart.builder()
                    .product(product)
                    .cart(this)
                    .quantity(1)
                    .totalByQuantity(product.getPrice())
                    .totalWithDiscount(product.getPrice())
                    .build());
        } else {
            cartProducts.forEach(pCart -> {
                if (pCart.getProduct().getId().equals(product.getId())) {
                    pCart.setQuantity(pCart.getQuantity() + 1);
                    pCart.setCart(this);
                } else {
                    getCartProducts().add(ProductCart.builder()
                            .product(product)
                            .cart(this)
                            .quantity(1)
                            .totalByQuantity(product.getPrice())
                            .totalWithDiscount(product.getPrice())
                            .build()
                    );
                }


            });
        }
        return this;
    }
}
