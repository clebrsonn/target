package br.com.hyteck.platform.entity;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Cart extends AbstractEntity<String> {

    private List<ProductCart> products;


}
