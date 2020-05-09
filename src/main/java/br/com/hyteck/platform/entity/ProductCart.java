package br.com.hyteck.platform.entity;

import br.com.hyteck.platform.frw.AbstractDiscount;
import br.com.hyteck.platform.frw.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema
public class ProductCart extends AbstractEntity<String> {


    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id")
//    @MapsId
    @JsonIgnore
    private Product product;

    @ManyToOne
    @Schema
    @JsonBackReference
    private Cart cart;

    @Range(min = 1)
    @Schema
    @Builder.Default
    private Integer quantity =1;

    @Schema
    @Builder.Default
    private BigDecimal discount = new BigDecimal(0);

    @Schema
    @Builder.Default
    private BigDecimal totalByQuantity =new BigDecimal(0);

    @Builder.Default
    private BigDecimal totalWithDiscount =new BigDecimal(0);

    public BigDecimal getTotalByQuantity() {
        setTotalByQuantity();
        return totalByQuantity;
    }

    private void setTotalByQuantity() {
        this.totalByQuantity = product.getPrice().multiply(new BigDecimal(quantity));
    }


    public BigDecimal getTotalWithDiscount() {
        setTotalWithDiscount();
        return totalWithDiscount;
    }

    private void setTotalWithDiscount() {
        this.totalWithDiscount = getTotalByQuantity().subtract(getTotalByQuantity().multiply(discount));
    }
}
