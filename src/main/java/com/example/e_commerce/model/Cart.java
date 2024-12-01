package com.example.e_commerce.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(CartItem item) {
        items.add(item);
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
}


