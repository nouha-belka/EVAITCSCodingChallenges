package com.evaitcs.collections;

import java.util.Objects;

/**
 * ============================================================================
 * CLASS: Product
 * TOPIC: A model class used throughout the Collections project
 * ============================================================================
 *
 * This is a simple POJO (Plain Old Java Object) that represents a product
 * in a store. It implements Comparable so products can be sorted!
 *
 * KEY CONCEPT:
 * When you use objects in Sets or as Map keys, you MUST override
 * equals() and hashCode()! Otherwise, Java can't detect duplicates.
 * ============================================================================
 */
public class Product implements Comparable<Product> {

    private String productId;
    private String name;
    private String category;
    private double price;
    private int quantity;

    // TODO 1: Create a constructor that initializes ALL fields
    // Add validation: price >= 0, quantity >= 0, name not null/empty


    // TODO 2: Create getters for ALL fields


    // TODO 3: Create setters for price and quantity WITH validation
    // (productId, name, category shouldn't change after creation)


    /**
     * TODO 4: Override equals() — Two products are equal if they have the same productId
     * This is CRITICAL for Sets and Maps!
     *
     * Pattern:
     * @Override
     * public boolean equals(Object o) {
     *     if (this == o) return true;
     *     if (o == null || getClass() != o.getClass()) return false;
     *     Product product = (Product) o;
     *     return Objects.equals(productId, product.productId);
     * }
     */
    @Override
    public boolean equals(Object o) {
        // TODO: Implement equals based on productId
        return false; // Replace this line
    }

    /**
     * TODO 5: Override hashCode() — MUST be consistent with equals()
     * If two objects are equal, they MUST have the same hashCode!
     *
     * Use: Objects.hash(productId)
     */
    @Override
    public int hashCode() {
        // TODO: Return hash based on productId
        return 0; // Replace this line
    }

    /**
     * TODO 6: Implement compareTo() — Sort products by name alphabetically
     * This lets you use TreeSet and Collections.sort() with Products!
     *
     * The Comparable interface requires: int compareTo(Product other)
     * Return negative if this < other, 0 if equal, positive if this > other
     *
     * Use: this.name.compareToIgnoreCase(other.name)
     */
    @Override
    public int compareTo(Product other) {
        // TODO: Compare by name
        return 0; // Replace this line
    }

    /**
     * TODO 7: Override toString()
     * Format: "Product{id='P001', name='Laptop', category='Electronics', price=$999.99, qty=10}"
     */
    @Override
    public String toString() {
        return ""; // Replace this line
    }
}

