# unibloxAssignment

## API Usage Examples

1. Add Item to Cart
Endpoint: POST /cart/add
Description: Adds an item to the user's cart.

```
curl -X POST http://localhost:8080/cart/add \
-H "Content-Type: application/json" \
-d '{
  "name": "Laptop",
  "price": 1500.0,
  "quantity": 1
}'
```

2. Checkout Cart
Endpoint: POST /cart/checkout
Description: Checks out the cart and optionally applies a discount code.

Without Discount Code:

```
curl -X POST http://localhost:8080/cart/checkout \
-H "Content-Type: application/json" \
-d '{
  "discountCode": ""
}'
```

With Discount Code:
```
curl -X POST http://localhost:8080/cart/checkout \
-H "Content-Type: application/json" \
-d '{
  "discountCode": "DISCOUNT123"
}'
```

3. View Order Statistics (Admin)
Endpoint: GET /admin/stats
Description: Retrieves statistics on orders and discounts.

```
curl -X GET http://localhost:8080/admin/stats
```

4. Add Multiple Items to Cart
Endpoint: POST /cart/add
Description: Adds various items to the cart for testing bulk scenarios.

Adding a Phone:
```
curl -X POST http://localhost:8080/cart/add \
-H "Content-Type: application/json" \
-d '{
  "name": "Phone",
  "price": 800.0,
  "quantity": 2
}'
```

Adding Headphones:
```
curl -X POST http://localhost:8080/cart/add \
-H "Content-Type: application/json" \
-d '{
  "name": "Headphones",
  "price": 150.0,
  "quantity": 3
}'
```

5. Checkout Multiple Items with Discount
Endpoint: POST /cart/checkout
Description: Checks out multiple items using a valid discount code.

```
curl -X POST http://localhost:8080/cart/checkout \
-H "Content-Type: application/json" \
-d '{
  "discountCode": "VALIDCODE123"
}'
```

6. Empty Cart and Checkout
Endpoint: POST /cart/checkout
Description: Attempts to check out with an empty cart.

```
curl -X POST http://localhost:8080/cart/checkout \
-H "Content-Type: application/json" \
-d '{
  "discountCode": ""
}'
```


