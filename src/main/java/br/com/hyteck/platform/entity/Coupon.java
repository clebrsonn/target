package br.com.hyteck.platform.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Coupon extends AbstractDiscount{


    @Override
    public Cart applyDiscount(Cart cart) {

        if(cart.getCoupon()!=null && cart.getCoupon().getPercent().compareTo(this.getPercent()) < 0){
            cart.setCoupon(this);

        }

        return cart;
    }
}
