package com.example.e_commerce.model;

import java.util.List;

public class Order {
    private List<CartItem> items;
    private double totalAmount;
    private boolean discountApplied;

    public Order(List<CartItem> items, double totalAmount, boolean discountApplied) {
        this.items = items;
        this.totalAmount = totalAmount;
        this.discountApplied = discountApplied;
    }

    public int getItemCount() {
        return items.stream().mapToInt(CartItem::getQuantity).sum();
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isDiscountApplied() {
        return discountApplied;
    }

    public double getDiscountAmount() {
        return discountApplied ? items.stream().mapToDouble(CartItem::getTotalPrice).sum() - totalAmount : 0;
    }
}

