package com.griddynamics.serviceshop.service;

import com.griddynamics.serviceshop.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    Product getProductByTitle(String title);
    List<Product> getAllProducts();
}
