package br.com.hyteck.platform.controller;

import br.com.hyteck.platform.entity.Product;
import br.com.hyteck.platform.framework.AbstractController;
import br.com.hyteck.platform.service.IServices;
import br.com.hyteck.platform.service.impl.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Tag(name = "Api de Produtos")
@AllArgsConstructor
public class ProductController extends AbstractController<Product> {

    private final ProductService productService;

    @Override
    protected IServices<Product> getService() {
        return productService;
    }
}