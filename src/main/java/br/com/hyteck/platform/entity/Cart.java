package br.com.hyteck.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class Cart extends AbstractEntity<String>{

    @OneToMany(mappedBy = "cart")
    private List<ProductCart> productCart;

}
