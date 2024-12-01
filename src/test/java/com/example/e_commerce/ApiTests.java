package com.example.e_commerce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void testAddItemToCart() {
    String url = "/cart/add";

    // Request body
    String requestBody = "{\"name\":\"Laptop\",\"price\":1500.0,\"quantity\":1}";

    // Set headers
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Create the request entity
    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

    // Make the POST request
    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

    // Assertions
    assertEquals(200, response.getStatusCodeValue());
    assertEquals("Item added to cart", response.getBody());
  }

  @Test
  void testCheckout() {
    String addItemUrl = "/cart/add";
    String checkoutUrl = "/cart/checkout";

    // Headers
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Add an item to the cart
    String addItemRequest = "{\"name\":\"Laptop\",\"price\":1500.0,\"quantity\":1}";
    HttpEntity<String> addItemEntity = new HttpEntity<>(addItemRequest, headers);
    restTemplate.postForEntity(addItemUrl, addItemEntity, String.class);

    // Checkout with a discount code
    String checkoutRequest = "{\"discountCode\":\"DISCOUNT123\"}";
    HttpEntity<String> checkoutEntity = new HttpEntity<>(checkoutRequest, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(checkoutUrl, checkoutEntity, String.class);

    // Assertions
    assertEquals(200, response.getStatusCodeValue());
    assertNotNull(response.getBody());
  }


  @Test
  void testAdminStats() {
    String url = "/admin/stats";
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

    assertEquals(200, response.getStatusCodeValue());
  }
}

