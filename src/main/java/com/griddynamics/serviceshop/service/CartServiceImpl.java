package com.griddynamics.serviceshop.service;

import com.griddynamics.serviceshop.dto.ProductDto;
import com.griddynamics.serviceshop.model.Cart;
import com.griddynamics.serviceshop.model.CartItem;
import com.griddynamics.serviceshop.repository.CartItemJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {
    @Autowired
    CartItemJdbcRepository cartItemJdbcRepository;

    @Override
    public void addItem(ProductDto productDto, String sessionId) {
        cartItemJdbcRepository.addItem(productDto, sessionId);

    }

    @Override
    public Cart displayItems(String sessionId) {
        Cart cart = new Cart();
        List<CartItem> cartItems = cartItemJdbcRepository.getCartItems(sessionId);
        Double total = 0D;
        if (cartItems != null) {
            for (CartItem cartItem : cartItems) {
                cartItem.setId(cartItems.indexOf(cartItem) + 1);
                total += cartItem.getPrice() * cartItem.getQuantity();
            }
            cart.setItems(cartItems);
            cart.setTotal(total);
        }
        return cart;
    }

    @Override
    public void editItem(Long id, Long quantity, String sessionId) {
        cartItemJdbcRepository.updateCartItem(id, quantity, sessionId);
    }

    @Override
    public void removeItem(Long id, String sessionId) {
        cartItemJdbcRepository.removeCartItem(id, sessionId);
    }

    @Override
    public Cart getCart(Long id) {
        return null;
    }
}
