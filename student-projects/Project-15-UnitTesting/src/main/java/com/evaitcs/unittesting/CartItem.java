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
}

