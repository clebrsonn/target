package br.com.hyteck.platform.controller;

import br.com.hyteck.platform.entity.Partner;
import br.com.hyteck.platform.framework.AbstractController;
import br.com.hyteck.platform.service.IServices;
import br.com.hyteck.platform.service.impl.PartnerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/affiliated")
@Tag(name = "Api de Parceiros")
@AllArgsConstructor
public class PartnerController extends AbstractController<Partner> {

    private final PartnerService partnerService;

    @Override
    protected IServices<Partner> getService() {
        return partnerService;
    }
}
