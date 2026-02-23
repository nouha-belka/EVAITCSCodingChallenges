package com.evaitcs.collections;

import java.util.*;

/**
 * ============================================================================
 * CLASS: InventoryManager
 * TOPIC: Java Collections Framework — List, Set, Map, Queue
 * ============================================================================
 *
 * This class manages a store's inventory using different collection types.
 * Each method practices a specific collection operation.
 *
 * COLLECTIONS CHEAT SHEET:
 * - List:  Ordered, allows duplicates. Use for ordered data.
 * - Set:   NO duplicates, fast lookup. Use for unique items.
 * - Map:   Key-value pairs, unique keys. Use for lookups by key.
 * - Queue: FIFO ordering. Use for processing in order.
 *
 * PERFORMANCE (Big O):
 * | Collection   | Access | Search | Insert | Delete |
 * |-------------|--------|--------|--------|--------|
 * | ArrayList   | O(1)   | O(n)   | O(n)   | O(n)   |
 * | LinkedList  | O(n)   | O(n)   | O(1)   | O(1)   |
 * | HashSet     | -      | O(1)   | O(1)   | O(1)   |
 * | TreeSet     | -      | O(lgn) | O(lgn) | O(lgn) |
 * | HashMap     | -      | O(1)   | O(1)   | O(1)   |
 * | TreeMap     | -      | O(lgn) | O(lgn) | O(lgn) |
 *
 * INTERVIEW TIP:
 * "I choose ArrayList for random access, LinkedList for frequent inserts,
 *  HashSet for O(1) uniqueness checks, and HashMap for key-value lookups."
 * ============================================================================
 */
public class InventoryManager {

    // =========================================================================
    // FIELDS — Different collections for different purposes
    // =========================================================================

    // TODO 1: Declare the following collection fields:
    //   - productList (List<Product>)         — stores all products in order
    //   - productIdSet (Set<String>)           — tracks unique product IDs
    //   - productMap (Map<String, Product>)    — maps productId → Product for fast lookup
    //   - orderQueue (Queue<String>)           — queue of product IDs to restock


    // =========================================================================
    // CONSTRUCTOR
    // =========================================================================

    /**
     * TODO 2: Initialize all collections in the constructor
     * - productList → new ArrayList<>()
     * - productIdSet → new HashSet<>()
     * - productMap → new HashMap<>()
     * - orderQueue → new LinkedList<>()
     */
    // YOUR CONSTRUCTOR HERE


    // =========================================================================
    // LIST OPERATIONS
    // =========================================================================

    /**
     * TODO 3: Add a product to the inventory
     * Steps:
     * 1. Check if the product ID already exists in the Set (O(1) check!)
     * 2. If it exists, print "Product already exists" and return false
     * 3. If new: add to productList, productIdSet, and productMap
     * 4. Print "Product added: [name]" and return true
     *
     * @param product the product to add
     * @return true if added, false if duplicate
     */
    public boolean addProduct(Product product) {
        // TODO: Implement using all three collections

        return false; // Replace this line
    }

    /**
     * TODO 4: Remove a product by ID
     * Remove from all three collections (List, Set, Map).
     *
     * @param productId the ID of the product to remove
     * @return true if found and removed, false if not found
     */
    public boolean removeProduct(String productId) {
        // TODO: Remove from productMap, productIdSet, and productList
        // For the list, use removeIf: productList.removeIf(p -> p.getProductId().equals(id))

        return false; // Replace this line
    }

    /**
     * TODO 5: Get all products sorted by name
     * Use Collections.sort() — this works because Product implements Comparable!
     *
     * @return a new sorted list of products
     */
    public List<Product> getProductsSortedByName() {
        // TODO: Create a copy of productList, sort it, return it
        // Don't sort the original list!

        return new ArrayList<>(); // Replace this line
    }

    /**
     * TODO 6: Get all products sorted by price (low to high)
     * Use a Comparator — since Comparable sorts by name, use a
     * separate Comparator for price sorting.
     *
     * @return a new list sorted by price
     */
    public List<Product> getProductsSortedByPrice() {
        // TODO: Create a copy, sort with a Comparator
        // Hint: sorted.sort(Comparator.comparingDouble(Product::getPrice));
        // Or:   sorted.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));

        return new ArrayList<>(); // Replace this line
    }

    // =========================================================================
    // MAP OPERATIONS
    // =========================================================================

    /**
     * TODO 7: Find a product by ID using the Map (O(1) lookup!)
     *
     * @param productId the ID to search for
     * @return the Product, or null if not found
     */
    public Product findProductById(String productId) {
        // TODO: Use productMap.get(productId)

        return null; // Replace this line
    }

    /**
     * TODO 8: Get all products in a specific category
     * Must iterate through all products (the map values).
     *
     * @param category the category to filter by
     * @return a list of products in that category
     */
    public List<Product> getProductsByCategory(String category) {
        // TODO: Loop through productMap.values() and filter by category

        return new ArrayList<>(); // Replace this line
    }

    /**
     * TODO 9: Create a report: Map<String, Integer> — category name → count of products
     * Use a Map to count how many products are in each category.
     *
     * @return a map of category names to product counts
     */
    public Map<String, Integer> getCategoryCounts() {
        // TODO: Loop through products, count each category
        // Use: map.put(category, map.getOrDefault(category, 0) + 1)

        return new HashMap<>(); // Replace this line
    }

    // =========================================================================
    // SET OPERATIONS
    // =========================================================================

    /**
     * TODO 10: Get all unique categories in the inventory
     * Sets automatically handle uniqueness!
     *
     * @return a Set of category names
     */
    public Set<String> getAllCategories() {
        // TODO: Loop through products and add each category to a new HashSet

        return new HashSet<>(); // Replace this line
    }

    /**
     * TODO 11: Check if a product ID exists (uses the Set for O(1) lookup)
     *
     * @param productId the ID to check
     * @return true if exists
     */
    public boolean productExists(String productId) {
        // TODO: Use productIdSet.contains(productId)

        return false; // Replace this line
    }

    // =========================================================================
    // QUEUE OPERATIONS
    // =========================================================================

    /**
     * TODO 12: Add a product ID to the restock queue
     *
     * @param productId the product ID to restock
     */
    public void addToRestockQueue(String productId) {
        // TODO: Use orderQueue.offer(productId)
        // Print: "Added [productId] to restock queue"
    }

    /**
     * TODO 13: Process the next restock request (FIFO)
     * Remove and return the next product ID from the queue.
     *
     * @return the next product ID to restock, or null if queue is empty
     */
    public String processNextRestock() {
        // TODO: Use orderQueue.poll()
        // Print: "Processing restock for: [productId]"

        return null; // Replace this line
    }

    /**
     * TODO 14: Peek at the next item in the restock queue WITHOUT removing it
     *
     * @return the next product ID, or null if empty
     */
    public String peekNextRestock() {
        // TODO: Use orderQueue.peek()

        return null; // Replace this line
    }

    /**
     * TODO 15: Get the size of the restock queue
     */
    public int getRestockQueueSize() {
        return 0; // Replace this line
    }

    // =========================================================================
    // UTILITY METHODS
    // =========================================================================

    /**
     * TODO 16: Get the total number of products in inventory
     */
    public int getProductCount() {
        return 0; // Replace this line
    }

    /**
     * TODO 17: Calculate total inventory value
     * Sum of (price * quantity) for all products
     */
    public double getTotalInventoryValue() {
        // TODO: Loop through products and sum price * quantity

        return 0.0; // Replace this line
    }

    /**
     * TODO 18: Find products with low stock (quantity < threshold)
     *
     * @param threshold the low stock threshold
     * @return list of products with quantity below threshold
     */
    public List<Product> getLowStockProducts(int threshold) {
        // TODO: Filter products where quantity < threshold

        return new ArrayList<>(); // Replace this line
    }
}

