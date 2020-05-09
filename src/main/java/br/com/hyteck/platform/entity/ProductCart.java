package br.com.hyteck.platform.entity;

import br.com.hyteck.platform.frw.AbstractDiscount;
import br.com.hyteck.platform.frw.AbstractEntity;
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
@AttributeOverride(name = "id", column = @Column(name = "PRO_CAR_ID"))
@Schema
public class ProductCart extends AbstractEntity<String> {


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRO_CAR_ID")
    @MapsId
    @JsonIgnore
    private Product product;

    @ManyToOne
    @Schema
    private Cart cart;

    @Range(min = 1)
    @Schema
    private Integer quantity;

    @OneToOne
    @Schema
    private AbstractDiscount discount;

    @Schema
    @Transient
    private BigDecimal totalByQuantity;

    public BigDecimal getTotalByQuantity() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }

    public void setTotalByQuantity() {
        this.totalByQuantity = product.getPrice().multiply(new BigDecimal(quantity));
    }
}
