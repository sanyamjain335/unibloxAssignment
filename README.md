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
