package com.example.e_commerce;

import com.example.e_commerce.model.Cart;
import com.example.e_commerce.model.CartItem;
import com.example.e_commerce.model.Order;
import com.example.e_commerce.model.Stats;
import com.example.e_commerce.service.DiscountManager;
import com.example.e_commerce.service.OrderManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderManagerTest {

  private DiscountManager discountManager;
  private OrderManager orderManager;
  private Cart cart;

  @BeforeEach
  void setUp() {
    discountManager = mock(DiscountManager.class); // Mocked for testing
    orderManager = new OrderManager(discountManager);

    cart = new Cart();
    cart.addItem(new CartItem("Item1", 50, 2)); // 2 items of $50 each
    cart.addItem(new CartItem("Item2", 100, 1)); // 1 item of $100
  }

  @Test
  void testPlaceOrderWithoutDiscount() {
    // Configure the discount manager to return no valid discount
    when(discountManager.validateDiscount(anyString())).thenReturn(false);

    Order order = orderManager.placeOrder(cart, "INVALID_CODE");

    assertEquals(200.0, order.getTotalAmount()); // No discount applied
    assertFalse(order.isDiscountApplied());
    verify(discountManager, times(1)).generateDiscount(); // Check for new discount
  }

  @Test
  void testPlaceOrderWithDiscount() {
    // Configure the discount manager to validate a discount
    when(discountManager.validateDiscount("VALID_CODE")).thenReturn(true);
    when(discountManager.getActiveDiscount()).thenReturn(
        new com.example.e_commerce.model.DiscountCode("VALID_CODE", 0.1)
    );

    Order order = orderManager.placeOrder(cart, "VALID_CODE");

    assertEquals(180.0, order.getTotalAmount(), 0.01); // 10% discount applied on $200
    assertTrue(order.isDiscountApplied());
    verify(discountManager, times(1)).markDiscountUsed();
    verify(discountManager, times(1)).generateDiscount();
  }

  @Test
  void testPlaceOrderInvalidDiscount() {
    // Invalid discount code should result in no discount applied
    when(discountManager.validateDiscount("INVALID_CODE")).thenReturn(false);

    Order order = orderManager.placeOrder(cart, "INVALID_CODE");

    assertEquals(200.0, order.getTotalAmount()); // Total remains same
    assertFalse(order.isDiscountApplied());
  }

  @Test
  void testGetStats() {
    // Mock discount manager behavior
    when(discountManager.validateDiscount(anyString())).thenReturn(false);

    // Place two orders
    orderManager.placeOrder(cart, "INVALID_CODE");
    orderManager.placeOrder(cart, "INVALID_CODE");

    Stats stats = orderManager.getStats();

    assertEquals(6, stats.getTotalItems()); // 3 items per order * 2 orders
    assertEquals(400.0, stats.getTotalAmount(), 0.01); // $200 per order * 2 orders
    assertEquals(0.0, stats.getTotalDiscount(), 0.01); // No discount applied
  }

  @Test
  void testGetStatsWithDiscount() {
    // Mock discount manager for one discounted order
    when(discountManager.validateDiscount("VALID_CODE")).thenReturn(true);
    when(discountManager.getActiveDiscount()).thenReturn(
        new com.example.e_commerce.model.DiscountCode("VALID_CODE", 0.1)
    );

    // Place orders
    orderManager.placeOrder(cart, "VALID_CODE"); // Discount applied
    when(discountManager.validateDiscount("INVALID_CODE")).thenReturn(false);
    orderManager.placeOrder(cart, "INVALID_CODE"); // No discount

    Stats stats = orderManager.getStats();

    assertEquals(6, stats.getTotalItems()); // 3 items per order * 2 orders
    assertEquals(380.0, stats.getTotalAmount(), 0.01); // $180 + $200
    assertEquals(20.0, stats.getTotalDiscount(), 0.01); // $20 discount on first order
  }
}


