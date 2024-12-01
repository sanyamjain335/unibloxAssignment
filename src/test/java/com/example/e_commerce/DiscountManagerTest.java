package com.example.e_commerce;

import com.example.e_commerce.model.DiscountCode;
import com.example.e_commerce.service.DiscountManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DiscountManagerTest {

  private DiscountManager discountManager;

  @BeforeEach
  void setUp() {
    discountManager = new DiscountManager(5); // Every 5th order gets a discount
  }

  @Test
  void testGenerateDiscount() {
    Optional<DiscountCode> discountCode = Optional.empty();
    for (int i = 1; i <= 5; i++) {
      discountCode = discountManager.generateDiscount();
    }
    assertTrue(discountCode.isPresent());
    assertNotNull(discountCode.get().getCode());
  }

  @Test
  void testGenerateDiscountNotTriggered() {
    Optional<DiscountCode> discountCode = Optional.empty();
    for (int i = 1; i < 5; i++) { // 4 orders, not reaching the nth order
      discountCode = discountManager.generateDiscount();
    }
    assertTrue(discountCode.isEmpty());
  }

  @Test
  void testValidateDiscount() {
    // Generate a discount
    for (int i = 1; i <= 5; i++) {
      discountManager.generateDiscount();
    }

    DiscountCode discountCode = discountManager.getActiveDiscount();
    assertNotNull(discountCode);

    // Validate the generated discount
    boolean isValid = discountManager.validateDiscount(discountCode.getCode());
    assertTrue(isValid);
  }

  @Test
  void testValidateDiscountInvalidCode() {
    for (int i = 1; i <= 5; i++) {
      discountManager.generateDiscount();
    }

    boolean isValid = discountManager.validateDiscount("INVALID_CODE");
    assertFalse(isValid);
  }

  @Test
  void testMarkDiscountUsed() {
    // Generate a discount
    for (int i = 1; i <= 5; i++) {
      discountManager.generateDiscount();
    }

    DiscountCode discountCode = discountManager.getActiveDiscount();
    assertNotNull(discountCode);

    discountManager.markDiscountUsed();

    // Validate the discount is now marked as used
    assertTrue(discountCode.isUsed());
  }
}


