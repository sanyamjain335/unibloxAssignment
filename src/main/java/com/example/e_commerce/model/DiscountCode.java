package com.example.e_commerce.model;

public class DiscountCode {
    private String code;
    private double percentage;
    private boolean used;

    public DiscountCode(String code, double percentage) {
        this.code = code;
        this.percentage = percentage;
        this.used = false;
    }

    public String getCode() {
        return code;
    }

    public double getPercentage() {
        return percentage;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}

