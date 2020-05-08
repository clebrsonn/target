package br.com.hyteck.platform.entity;

import br.com.hyteck.platform.frw.AbstractEntity;
import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema
public class Product extends AbstractEntity<String> {

//    @Currency(value = {"BRL", "USD"})
    @Schema
    private BigDecimal price;

    @Schema
    private String description;

    @NotEmpty
    @NotNull
    @Schema
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return getName().equals(product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }
}
