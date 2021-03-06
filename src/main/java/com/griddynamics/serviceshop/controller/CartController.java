package com.griddynamics.serviceshop.controller;

import com.griddynamics.serviceshop.dto.CartItemDto;
import com.griddynamics.serviceshop.dto.ProductDto;
import com.griddynamics.serviceshop.model.Cart;
import com.griddynamics.serviceshop.model.CartItem;
import com.griddynamics.serviceshop.service.CartService;
import com.griddynamics.serviceshop.service.ProductService;
import com.griddynamics.serviceshop.service.SessionService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/cart")
@RestController
public class CartController {

    private CartService cartService;
    private ProductService productService;
    private SessionService sessionService;

    @Autowired
    public CartController(CartService cartService, ProductService productService, SessionService sessionService) {
        this.cartService = cartService;
        this.productService = productService;
        this.sessionService = sessionService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addToCart(@RequestBody ProductDto product, HttpServletRequest request) {
        if (sessionService.isSessionExists(request)) {
            if (productService.isProductNotExists(product)) {
                return new ResponseEntity<>("There is no such product in store.", HttpStatus.NOT_FOUND);
            } else if (productService.isNotEnoughProducts(product)) {
                return new ResponseEntity<>("Not enough products in store!", HttpStatus.NOT_FOUND);
            } else {
                cartService.addItem(product, getSessionId(request));
                return new ResponseEntity<>("Product was added to cart.", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Not authorized", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity editCartItem(@RequestBody ProductDto product, HttpServletRequest request) {
        if (sessionService.isSessionExists(request)) {
            if (productService.isProductNotExists(product)) {
                return new ResponseEntity<>("There is no such product in store.", HttpStatus.NOT_FOUND);
            } else if (productService.isNotEnoughProducts(product)) {
                return new ResponseEntity<>("Not enough products in store!", HttpStatus.NOT_FOUND);
            } else {
                cartService.editItem(product.getId(), product.getQuantity(), getSessionId(request));
                return new ResponseEntity<>("Cart item was updated successfully.", HttpStatus.OK);
            }

        }
        return new ResponseEntity<>("Not authorized", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity removeCartItem(@RequestBody CartItemDto item, HttpServletRequest request) {
        if (sessionService.isSessionExists(request)) {
            cartService.removeItem(item.getId(), getSessionId(request));
            return new ResponseEntity<>("Cart item was removed", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not authorized", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> showCart(HttpServletRequest request) {
        if (sessionService.isSessionExists(request)) {
            return new ResponseEntity<Cart>(cartService.displayItems(getSessionId(request)), HttpStatus.OK);
        }
        return new ResponseEntity<>("Not authorized", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public ResponseEntity checkout(HttpServletRequest request) {
        if (sessionService.isSessionExists(request)) {
            Cart userCart = cartService.displayItems(getSessionId(request));
            if (productService.isAllProductsInStore(userCart)) {
                for (CartItem cartItem : userCart.getItems()) {
                    long id = cartItem.getProductId();
                    long quantity = productService.getProductById(id).getQuantity() - cartItem.getQuantity();
                    productService.updateProduct(id, quantity);

                }

            } else {
                return new ResponseEntity<>("Not enough products in store.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Checked out successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not authorized", HttpStatus.BAD_REQUEST);

    }


    private String getSessionId(HttpServletRequest request) {
        return request.getHeader("sessionId");
    }


}
