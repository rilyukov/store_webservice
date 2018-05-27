package com.griddynamics.serviceshop.service;

import com.griddynamics.serviceshop.dto.ProductDto;
import com.griddynamics.serviceshop.model.Cart;
import org.springframework.stereotype.Service;

@Service("cartService")
public class CartServiceImpl implements CartService {


    @Override
    public void addItem(ProductDto productDto) {

    }

    @Override
    public Cart displayItems(Long sessionId) {
        return null;
    }

    @Override
    public void editItem(Long id, Long quantity) {

    }

    @Override
    public void removeItem(Long id) {

    }

    @Override
    public Cart getCart(Long id) {
        return null;
    }
}
