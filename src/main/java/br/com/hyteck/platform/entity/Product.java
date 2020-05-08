package br.com.hyteck.platform.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Currency;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Data
public class Product extends AbstractEntity<String> {

    @Currency(value = {"BRL", "USD"})
    private BigDecimal price;

    private String description;

    @NotEmpty
    @NotNull
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
