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
import java.util.stream.Collectors;

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
    private List<ProductCart> cartProducts;

    @ManyToOne
    @Schema
    private AbstractDiscount coupon;

    @Schema
    private BigDecimal percentDiscount;

    @Schema
    private BigDecimal total;

    @Schema
    private BigDecimal subTotal;


    public Cart addProduct(Product product) {
        cartProducts.forEach(pCart -> {
            if (pCart.getId().equals(product.getId())) {
                pCart.setQuantity(pCart.getQuantity() + 1);
                pCart.getTotalByQuantity();
                pCart.getTotalWithDiscount();
                pCart.setCart(this);
            } else {
                getCartProducts().add(ProductCart.builder()
                        .product(product)
                        .cart(this)
                        .quantity(1)
                        .build()
                );
            }


        });
        return this;
    }


    public Cart removeProduct(Product product) {
        cartProducts.forEach(pCart -> {
            if (pCart.getId().equals(product.getId()) && pCart.getQuantity() > 1) {
                pCart.setQuantity(pCart.getQuantity() - 1);
                pCart.getTotalByQuantity();
                pCart.getTotalWithDiscount();
                pCart.setCart(this);
            } else {
                final var productCartFiltered = getCartProducts().stream().
                        filter(productCart -> !pCart.getId().equals(product.getId())).collect(Collectors.toList());
                setCartProducts(productCartFiltered);
            }

        });
        return this;
    }


//    public BigDecimal getTotal() {
//
//
//
//        return total;
//    }
//
//    public void setTotal(BigDecimal total) {
//        this.total = total;
//    }
//
//    public BigDecimal getSubTotal() {
//        return subTotal;
//    }
//
//    public void setSubTotal(BigDecimal subTotal) {
//        this.subTotal = subTotal;
//    }
}
