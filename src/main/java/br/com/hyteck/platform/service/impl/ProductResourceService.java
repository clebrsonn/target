//package br.com.hyteck.platform.service.impl;
//
//import br.com.hyteck.platform.api.resource.ProductResource;
//import br.com.hyteck.platform.entity.Product;
//import br.com.hyteck.platform.repository.ProductRepository;
//import br.com.hyteck.platform.service.IServices;
//import lombok.AllArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//
//@Service
//@AllArgsConstructor
//public class ProductResourceService implements IServices<ProductResource> {
//
//
//    private final ProductRepository productRepository;
//
//
//    public Page<ProductResource> findall(Pageable pageable) {
//
//        return productRepository.findAll(pageable);
//    }
//
//
//    public Optional<ProductResource> findById(Long id) {
//        return productRepository.findById(id);
//    }
//
//    public ProductResource create(ProductResource product) {
//        return productRepository.save(product);
//    }
//
//    @Override
//    public void delete(Long id) {
//        productRepository.deleteById(id);
//    }
//
//}
