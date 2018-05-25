package com.griddynamics.serviceshop.controller;

import com.griddynamics.serviceshop.dto.ProductDto;
import com.griddynamics.serviceshop.model.Product;
import com.griddynamics.serviceshop.service.CartService;
import com.griddynamics.serviceshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addToCart(@RequestBody ProductDto product, HttpServletRequest request) {


        Product item = productService.getProductById(product.getId());
        if (item.getQuantity() < product.getQuantity()) {
            return new ResponseEntity("Not enough products in store! You requested" + product.getQuantity()
                    + "evailable" + item.getQuantity(), HttpStatus.EXPECTATION_FAILED);
        }else{

        }

        return null;
    }
}
