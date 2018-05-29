package com.griddynamics.serviceshop.service;

import com.griddynamics.serviceshop.model.Product;
import com.griddynamics.serviceshop.repository.ProductJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductJdbcRepository productJdbcRepository;


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

    public void updateProduct(Long id, Long quantity){
        productJdbcRepository.updateProduct(id, quantity);
    }

    /*private static List<Product> populateDummyProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product1", 23L, 21.5));
        products.add(new Product(2L, "Product2", 3L, 112.5));
        products.add(new Product(3L, "Product3", 9L, 3.5));
        products.add(new Product(4L, "Product4", 18L, 50.5));
        return products;
    }*/
}
