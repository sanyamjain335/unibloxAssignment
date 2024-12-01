package com.example.e_commerce.controller;

import com.example.e_commerce.model.Cart;
import com.example.e_commerce.model.Order;
import com.example.e_commerce.service.CartService;
import com.example.e_commerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/cart")
public class UserController {

    private final CartService cartService;
    private final CheckoutService checkoutService;

    @Autowired
    public UserController(CartService cartService, CheckoutService checkoutService) {
        this.cartService = cartService;
        this.checkoutService = checkoutService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addItem(@RequestBody Map<String, Object> itemData) {
        String name = (String) itemData.get("name");
        double price = (Double) itemData.get("price");
        int quantity = (Integer) itemData.get("quantity");

        cartService.addItem(name, price, quantity);
        return ResponseEntity.ok("Item added to cart");
    }

    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(@RequestBody Map<String, String> request) {
        String discountCode = request.get("discountCode");
        Cart cart = cartService.getCart();
        Order order = checkoutService.checkout(cart, discountCode);
        return ResponseEntity.ok(order);
    }
}


