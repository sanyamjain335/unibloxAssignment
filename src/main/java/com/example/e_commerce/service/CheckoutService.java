package com.example.e_commerce.service;

import com.example.e_commerce.model.Cart;
import com.example.e_commerce.model.Order;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {
    private OrderManager orderManager;

    public CheckoutService(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public Order checkout(Cart cart, String discountCode) {
        return orderManager.placeOrder(cart, discountCode);
    }
}

