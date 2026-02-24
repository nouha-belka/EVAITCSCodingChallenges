package com.evaitcs.unittesting;

/**
 * ============================================================================
 * CLASS: CartItem
 * TOPIC: A simple model class — will be tested through ShoppingCartTest
 * ============================================================================
 */
public class CartItem {

    private String productId;
    private String name;
    private double price;
    private int quantity;

    /**
     * TODO 1: Create a constructor with validation
     * - name must not be null/empty
     * - price must be > 0
     * - quantity must be >= 1
     * Throw IllegalArgumentException for invalid input.
     */
    // YOUR CONSTRUCTOR HERE
    public CartItem(String productId, String name, double price, int quantity) {
        if(name == null){
            throw new IllegalArgumentException("Name can't be blank or null");
        }
        if(price <= 0){
            throw new IllegalArgumentException("Price can't be negative");
        }
        if(quantity < 1 ){
            throw new IllegalArgumentException("Quantity can't be less than 1");
        }
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;

    }


    // TODO 2: Create getters for all fields

    // TODO 3: Create a setter for quantity with validation (>= 1)

    /**
     * TODO 4: Calculate the subtotal for this item
     * @return price * quantity
     */
    public double getSubtotal() {
        // TODO: Return price * quantity
        return 0.0; // Replace this line
    }

    @Override
    public String toString() {
        return String.format("CartItem{id='%s', name='%s', price=%.2f, qty=%d}",
                productId, name, price, quantity);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

