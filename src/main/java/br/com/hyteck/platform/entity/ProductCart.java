package br.com.hyteck.platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

public class ProductCart extends AbstractEntity<String>{


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @MapsId
    @JsonIgnore
    private Product product;

//    @ManyToOne
//    private Cart cart;

    private Integer quantity;


}
