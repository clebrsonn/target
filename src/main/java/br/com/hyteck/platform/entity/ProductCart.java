package br.com.hyteck.platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AttributeOverride(name="id", column=@Column(name = "PRO_CAR_ID"))
public class ProductCart extends AbstractEntity<String>{


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRO_CAR_ID")
    @MapsId
    @JsonIgnore
    private Product product;

    @ManyToOne
    private Cart cart;

    @Range(min = 1)
    private Integer quantity;

    @OneToOne
    private AbstractDiscount discount;

}
