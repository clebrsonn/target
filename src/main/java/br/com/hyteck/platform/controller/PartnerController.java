package br.com.hyteck.platform.controller;

import br.com.hyteck.platform.entity.Partner;
import br.com.hyteck.platform.frw.AbstractController;
import br.com.hyteck.platform.service.IServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/affiliated")
@Tag(name = "Api de Parceiros")
public class PartnerController extends AbstractController<Partner> {


    public PartnerController(IServices<Partner> service) {
        super(service);
    }


}
