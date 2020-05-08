package br.com.hyteck.platform.controller;

import br.com.hyteck.platform.entity.Coupon;
import br.com.hyteck.platform.frw.AbstractController;
import br.com.hyteck.platform.service.IServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupon")
public class CouponController extends AbstractController<Coupon> {


    public CouponController(IServices<Coupon> service) {
        super(service);
    }


}
