package com.griddynamics.serviceshop.service;

import com.griddynamics.serviceshop.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
   private static List<Product> products;

   static {
       products  = populateDummyProducts();
   }

    @Override
    public Product getProductById(Long id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product getProductByTitle(String title) {
        for (Product product : products) {
            if (product.getTitle() == title) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    private static List<Product> populateDummyProducts(){
        List<Product>  products = new ArrayList<>();
        products.add(new Product(1L,"Product1", 23L, 21.5));
        products.add(new Product(2L,"Product2", 3L, 112.5));
        products.add(new Product(3L,"Product3", 9L, 3.5));
        products.add(new Product(4L,"Product4", 18L, 50.5));
        return products;
    }
}
