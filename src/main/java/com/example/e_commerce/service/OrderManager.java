package com.example.e_commerce.service;

import com.example.e_commerce.model.Cart;
import com.example.e_commerce.model.Order;
import com.example.e_commerce.model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderManager {
    private List<Order> orders = new ArrayList<>();
    private DiscountManager discountManager;

    @Autowired
    public OrderManager(DiscountManager discountManager) {
        this.discountManager = discountManager;
    }

    public Order placeOrder(Cart cart, String discountCode) {
        boolean discountApplied = discountManager.validateDiscount(discountCode);
        double total = cart.calculateTotal();
        if (discountApplied) {
            total *= (1 - discountManager.getActiveDiscount().getPercentage());
            discountManager.markDiscountUsed();
        }

        Order order = new Order(cart.getItems(), total, discountApplied);
        orders.add(order);
        discountManager.generateDiscount(); // Check for new discount
        return order;
    }

    public Stats getStats() {
        int totalItems = orders.stream().mapToInt(Order::getItemCount).sum();
        double totalAmount = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        double totalDiscount = orders.stream()
                .filter(Order::isDiscountApplied)
                .mapToDouble(Order::getDiscountAmount)
                .sum();
        return new Stats(totalItems, totalAmount, totalDiscount);
    }
}


