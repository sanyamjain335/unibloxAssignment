package com.example.e_commerce;

import com.example.e_commerce.model.Cart;
import com.example.e_commerce.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartServiceTest {

  private CartService cartService;

  @BeforeEach
  void setUp() {
    cartService = new CartService();
  }

  @Test
  void testAddItem() {
    cartService.addItem("Laptop", 1500.0, 1);
    Cart cart = cartService.getCart();
    assertEquals(1, cart.getItems().size());
  }

  @Test
  void testCalculateTotal() {
    cartService.addItem("Laptop", 1500.0, 1);
    cartService.addItem("Mouse", 50.0, 2);
    Cart cart = cartService.getCart();
    assertEquals(1600.0, cart.calculateTotal());
  }
}

