package com.evaitcs.unittesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ============================================================================
 * CLASS: ShoppingCart
 * TOPIC: The main class to be tested with JUnit 5
 * ============================================================================
 *
 * TDD APPROACH:
 * Write the tests in ShoppingCartTest.java FIRST, then come here and
 * implement each method to make the tests pass. Red → Green → Refactor!
 * ============================================================================
 */
public class ShoppingCart {

    private List<CartItem> items;

    // TODO 1: Create a constructor that initializes items as empty ArrayList
    // YOUR CONSTRUCTOR HERE


    /**
     * TODO 2: Add an item to the cart
     * If an item with the same productId already exists, increase its quantity.
     * Otherwise, add the new item.
     *
     * @param item the item to add
     * @throws IllegalArgumentException if item is null
     */
    public void addItem(CartItem item) {
        // TODO: Implement — check for duplicate productId
    }

    /**
     * TODO 3: Remove an item by productId
     *
     * @param productId the product to remove
     * @return true if found and removed
     */
    public boolean removeItem(String productId) {
        // TODO: Implement
        return false; // Replace this line
    }

    /**
     * TODO 4: Get the total price of all items in the cart
     *
     * @return the sum of all item subtotals
     */
    public double getTotal() {
        // TODO: Sum up all item subtotals
        return 0.0; // Replace this line
    }

    /**
     * TODO 5: Get the number of unique items (not total quantity)
     *
     * @return number of unique items
     */
    public int getItemCount() {
        // TODO: Return items.size()
        return 0; // Replace this line
    }

    /**
     * TODO 6: Get the total quantity of all items
     *
     * @return sum of all quantities
     */
    public int getTotalQuantity() {
        // TODO: Sum all item quantities
        return 0; // Replace this line
    }

    /**
     * TODO 7: Find an item by productId
     *
     * @param productId the product to find
     * @return Optional containing the item if found, empty otherwise
     */
    public Optional<CartItem> findItem(String productId) {
        // TODO: Search and return the item wrapped in Optional
        return Optional.empty(); // Replace this line
    }

    /**
     * TODO 8: Clear all items from the cart
     */
    public void clear() {
        // TODO: Clear the items list
    }

    /**
     * TODO 9: Check if the cart is empty
     *
     * @return true if no items in cart
     */
    public boolean isEmpty() {
        // TODO: Return whether items list is empty
        return true; // Replace this line
    }

    /**
     * TODO 10: Apply a discount percentage to the total
     *
     * @param discountPercent the discount (0-100)
     * @return the discounted total
     * @throws IllegalArgumentException if discount is < 0 or > 100
     */
    public double applyDiscount(double discountPercent) {
        // TODO: Validate discount range, calculate discounted total
        return 0.0; // Replace this line
    }

    /**
     * TODO 11: Get all items as an unmodifiable list
     */
    public List<CartItem> getItems() {
        // TODO: Return unmodifiable copy
        return new ArrayList<>(); // Replace this line
    }
}

