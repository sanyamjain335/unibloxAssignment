package com.example.e_commerce;

import com.example.e_commerce.model.Cart;
import com.example.e_commerce.model.CartItem;
import com.example.e_commerce.model.Order;
import com.example.e_commerce.service.CheckoutService;
import com.example.e_commerce.service.OrderManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CheckoutServiceTest {

  private CheckoutService checkoutService;
  private OrderManager orderManager;

  @BeforeEach
  void setUp() {
    orderManager = mock(OrderManager.class);
    checkoutService = new CheckoutService(orderManager);
  }

  @Test
  void testCheckoutWithDiscount() {
    Cart cart = new Cart();
    cart.addItem(new CartItem("Laptop", 1500.0, 1));
    when(orderManager.placeOrder(cart, "DISCOUNT123")).thenReturn(new Order(cart.getItems(), 1350.0, true));

    Order order = checkoutService.checkout(cart, "DISCOUNT123");
    assertEquals(1350.0, order.getTotalAmount());
    assertEquals(true, order.isDiscountApplied());
    verify(orderManager, times(1)).placeOrder(cart, "DISCOUNT123");
  }
}

