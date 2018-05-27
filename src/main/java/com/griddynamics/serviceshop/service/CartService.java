package com.griddynamics.serviceshop.service;

import com.griddynamics.serviceshop.dto.ProductDto;
import com.griddynamics.serviceshop.model.Cart;

public interface CartService {
    public void addItem(ProductDto productDto);
    public Cart displayItems(Long sessionId);
    public void editItem(Long id, Long quantity);
    public void removeItem(Long id);
    public Cart getCart(Long id);
}
