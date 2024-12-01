package com.example.e_commerce.service;

import com.example.e_commerce.model.Cart;
import com.example.e_commerce.model.CartItem;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private Cart cart = new Cart();

    public void addItem(String name, double price, int quantity) {
        cart.addItem(new CartItem(name, price, quantity));
    }

    public Cart getCart() {
        return cart;
    }
}

