package br.com.hyteck.platform.controller;

        import br.com.hyteck.platform.entity.Product;
        import br.com.hyteck.platform.frw.AbstractController;
        import br.com.hyteck.platform.service.IServices;
        import io.swagger.v3.oas.annotations.tags.Tag;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Tag(name = "Api de Produtos")
public class ProductController extends AbstractController<Product> {

    public ProductController(IServices<Product> productService) {
        super(productService);
    }
}