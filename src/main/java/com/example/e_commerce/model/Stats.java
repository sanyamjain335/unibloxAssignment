package com.example.e_commerce.model;

public class Stats {
    private int totalItems;
    private double totalAmount;
    private double totalDiscount;

    public Stats(int totalItems, double totalAmount, double totalDiscount) {
        this.totalItems = totalItems;
        this.totalAmount = totalAmount;
        this.totalDiscount = totalDiscount;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }
}

