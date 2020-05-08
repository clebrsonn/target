package br.com.hyteck.platform.controller;

import br.com.hyteck.platform.entity.Coupon;
import br.com.hyteck.platform.frw.AbstractController;
import br.com.hyteck.platform.service.IServices;
import br.com.hyteck.platform.service.impl.CouponService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupon")
@AllArgsConstructor
public class CouponController extends AbstractController<Coupon> {

    private final CouponService couponService;

    @Override
    protected IServices<Coupon> getService() {
        return couponService;
    }
}
