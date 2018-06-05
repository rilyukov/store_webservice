package com.griddynamics.serviceshop.service;

import com.griddynamics.serviceshop.dto.ProductDto;
import com.griddynamics.serviceshop.model.Cart;

public interface CartService {
    public void addItem(ProductDto productDto, String sessionId);

    public Cart displayItems(String sessionId);

    public void editItem(Long id, Long quantity, String sessionId);

    public void removeItem(Long id, String sessionId);

}
