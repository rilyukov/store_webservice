package com.griddynamics.serviceshop.service;

import com.griddynamics.serviceshop.dto.ProductDto;
import com.griddynamics.serviceshop.model.Cart;
import com.griddynamics.serviceshop.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);

    Product getProductByTitle(String title);

    List<Product> getAllProducts();

    void updateProduct(Long id, Long quantity);

    boolean isProductNotExists(ProductDto product);

    boolean isNotEnoughProducts(ProductDto productDto);

    boolean isAllProductsInStore(Cart cart);
}
