package com.griddynamics.serviceshop.service;

import com.griddynamics.serviceshop.model.Product;
import com.griddynamics.serviceshop.repository.ProductJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    private ProductJdbcRepository productJdbcRepository;

    @Autowired
    public ProductServiceImpl(ProductJdbcRepository productJdbcRepository) {
        this.productJdbcRepository = productJdbcRepository;
    }


    @Override
    public Product getProductById(Long id) {
        return productJdbcRepository.getProductById(id);
    }

    @Override
    public Product getProductByTitle(String title) {
        return productJdbcRepository.getProductByTitle(title);
    }

    @Override
    public List<Product> getAllProducts() {

        return productJdbcRepository.getAllProducts();
    }

    public void updateProduct(Long id, Long quantity) {
        productJdbcRepository.updateProduct(id, quantity);
    }

}
