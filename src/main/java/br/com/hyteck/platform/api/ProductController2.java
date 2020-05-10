//package br.com.hyteck.platform.api;
//
//import br.com.hyteck.platform.framework.AbstractController;
//import br.com.hyteck.platform.api.resource.ProductResource;
//import br.com.hyteck.platform.service.IServices;
//import br.com.hyteck.platform.service.impl.ProductService;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/product2")
//@Tag(name = "Api de Produtos2")
//@AllArgsConstructor
//public class ProductController2 extends AbstractController<ProductResource> {
//
//    private final ProductService productService;
//
//    @Override
//    protected IServices<ProductResource> getService() {
//        return productService;
//    }
//}