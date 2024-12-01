package com.example.e_commerce.service;

import com.example.e_commerce.model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private OrderManager orderManager;

    @Autowired
    public AdminService(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public Stats getStats() {
        return orderManager.getStats();
    }
}

