package br.com.hyteck.platform.controller;

import br.com.hyteck.platform.entity.discounts.Coupon;
import br.com.hyteck.platform.framework.AbstractController;
import br.com.hyteck.platform.service.IServices;
import br.com.hyteck.platform.service.impl.CouponService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupon")
@AllArgsConstructor
@Tag(name = "Api de Cupons")
public class CouponController extends AbstractController<Coupon> {

    private final CouponService couponService;

    @Override
    protected IServices<Coupon> getService() {
        return couponService;
    }
}
