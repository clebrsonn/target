package br.com.hyteck.platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

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


}
