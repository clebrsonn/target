package br.com.hyteck.platform.framework;

import br.com.hyteck.platform.entity.Cart;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

import static java.util.Objects.nonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "discount")
public abstract class AbstractDiscount extends AbstractEntity<String> {

    @NotEmpty
    @NotNull
    @Size(min = 3)
    @Schema
    @Column(unique = true)
    private String name;

    @Schema
    private BigDecimal percent;

//    @Schema
//    private BigDecimal value;

    @Transient
    private AbstractDiscount next;

    public abstract Cart applyDiscount(Cart cart);

    /**
     * Builds chains of middleware objects.
     */
    public AbstractDiscount linkWith(AbstractDiscount next) {
        this.next = next;
        return next;
    }

    /**
     * Runs check on the next object in chain or ends traversing if we're in
     * last object in chain.
     */
    public void verifyDiscount(Cart cart) {
        applyDiscount(cart);
        if (next != null) {
            next.verifyDiscount(cart);
        }
        if (nonNull(cart.getCoupon())) {
            cart.getCoupon().applyDiscount(cart);
        }
    }


}
