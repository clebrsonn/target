package br.com.hyteck.platform.service;

import br.com.hyteck.platform.entity.Product;
import br.com.hyteck.platform.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Page<Product> findall(Pageable pageable) {

        return productRepository.findAll(pageable);
    }


    public Optional<Product> finById(UUID id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }
}
