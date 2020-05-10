package br.com.hyteck.platform.service.impl;

import br.com.hyteck.platform.entity.Product;
import br.com.hyteck.platform.repository.ProductRepository;
import br.com.hyteck.platform.service.IServices;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class ProductService implements IServices<Product> {


    private final ProductRepository productRepository;


    public Page<Product> findall(Pageable pageable) {

        return productRepository.findAll(pageable);
    }


    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
