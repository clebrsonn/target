package br.com.hyteck.platform.frw;

import br.com.hyteck.platform.entity.Cart;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

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
    private String name;

    @Schema
    private BigDecimal percent;

    public abstract Cart applyDiscount(Cart cart);
}
