package com.griddynamics.serviceshop.controller;

import com.griddynamics.serviceshop.model.Product;
import com.griddynamics.serviceshop.service.ProductService;
import com.griddynamics.serviceshop.service.SessionService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController

public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    SessionService sessionService;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ResponseEntity getAllProducts(HttpServletRequest request) {
        if (sessionService.isSessionExists(request)) {

            List<Product> products = productService.getAllProducts();
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
     return new ResponseEntity<>("Not authorized", HttpStatus.BAD_REQUEST);
    }
}
