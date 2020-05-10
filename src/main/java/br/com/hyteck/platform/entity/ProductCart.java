package br.com.hyteck.platform.entity;

import br.com.hyteck.platform.framework.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

import static java.util.Objects.nonNull;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema
public class ProductCart extends AbstractEntity<String> {


    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id")
//    @MapsId
    @JsonIgnore
    @Setter
    private Product product;

    @ManyToOne
    @Schema
    @JsonBackReference
    @Setter
    private Cart cart;

    @Range(min = 1)
    @Schema
    @Builder.Default
    private Integer quantity = 1;

    @Schema
    @Builder.Default
    @Setter
    private BigDecimal discount = new BigDecimal(0);

    @Schema
    @Builder.Default
    private BigDecimal totalByQuantity = new BigDecimal(0);

    @Builder.Default
    private BigDecimal totalWithDiscount = new BigDecimal(0);

    public BigDecimal getTotalByQuantity() {
        setTotalByQuantity();
        return totalByQuantity;
    }

    private void setTotalByQuantity() {
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        calculateValue();

    }

    private void calculateValue() {
        if (nonNull(product)) {
            this.totalByQuantity = product.getPrice().multiply(new BigDecimal(quantity));

            this.totalWithDiscount = totalByQuantity.subtract(totalByQuantity.multiply(discount));
        }
    }
}
