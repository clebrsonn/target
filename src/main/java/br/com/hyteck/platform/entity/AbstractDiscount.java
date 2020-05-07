package br.com.hyteck.platform.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public abstract class AbstractDiscount extends AbstractEntity<String>{

    @NotEmpty
    @NotNull
    @Size(min = 3)
    private String name;

    private BigDecimal percent;

    public abstract Cart applyDiscount(Cart cart);
}
