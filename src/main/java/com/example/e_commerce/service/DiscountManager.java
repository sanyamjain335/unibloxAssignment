package com.example.e_commerce.service;

import com.example.e_commerce.model.DiscountCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DiscountManager {
    private int nthOrder;
    private int currentOrderCount = 0;
    private DiscountCode activeDiscount;

    public DiscountManager(@Value("${discount.nthOrder}") int nthOrder) {
        this.nthOrder = nthOrder;
    }

    public Optional<DiscountCode> generateDiscount() {
        currentOrderCount++;
        if (currentOrderCount == nthOrder) {
            activeDiscount = new DiscountCode(UUID.randomUUID().toString(), 0.1);
            currentOrderCount = 0;
            return Optional.of(activeDiscount);
        }
        return Optional.empty();
    }

    public boolean validateDiscount(String code) {
        return activeDiscount != null && activeDiscount.getCode().equals(code) && !activeDiscount.isUsed();
    }

    public void markDiscountUsed() {
        if (activeDiscount != null) {
            activeDiscount.setUsed(true);
        }
    }

    public DiscountCode getActiveDiscount() {
        return activeDiscount;
    }
}


