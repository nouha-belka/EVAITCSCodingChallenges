package com.evaitcs.collections;

import java.util.*;

/**
 * ============================================================================
 * MAIN APPLICATION: InventoryApp
 * TOPIC: Testing all Collections and Generics concepts
 * ============================================================================
 */
public class InventoryApp {

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("   INVENTORY MANAGEMENT SYSTEM");
        System.out.println("============================================\n");

        // =====================================================================
        // STEP 1: CREATE INVENTORY MANAGER
        // =====================================================================

        // TODO 1: Create an InventoryManager instance
        // InventoryManager inventory = new InventoryManager();

        // =====================================================================
        // STEP 2: ADD PRODUCTS
        // =====================================================================

        System.out.println("--- Adding Products ---\n");

        // TODO 2: Add at least 8 products across different categories
        // inventory.addProduct(new Product("P001", "Laptop", "Electronics", 999.99, 15));
        // inventory.addProduct(new Product("P002", "Mouse", "Electronics", 29.99, 100));
        // inventory.addProduct(new Product("P003", "Desk Chair", "Furniture", 249.99, 25));
        // inventory.addProduct(new Product("P004", "Monitor", "Electronics", 399.99, 30));
        // inventory.addProduct(new Product("P005", "Notebook", "Office Supplies", 4.99, 500));
        // inventory.addProduct(new Product("P006", "Pen Set", "Office Supplies", 12.99, 200));
        // inventory.addProduct(new Product("P007", "Standing Desk", "Furniture", 599.99, 8));
        // inventory.addProduct(new Product("P008", "Webcam", "Electronics", 79.99, 3));

        // TODO 3: Try adding a duplicate product — should fail!
        // inventory.addProduct(new Product("P001", "Duplicate", "Test", 0, 0));

        // =====================================================================
        // STEP 3: TEST LIST OPERATIONS
        // =====================================================================

        System.out.println("\n--- List Operations ---\n");

        // TODO 4: Print all products sorted by name
        // List<Product> sortedByName = inventory.getProductsSortedByName();
        // System.out.println("Sorted by name:");
        // for (Product p : sortedByName) System.out.println("  " + p);

        // TODO 5: Print all products sorted by price
        // List<Product> sortedByPrice = inventory.getProductsSortedByPrice();
        // System.out.println("\nSorted by price:");
        // for (Product p : sortedByPrice) System.out.println("  " + p);

        // =====================================================================
        // STEP 4: TEST MAP OPERATIONS
        // =====================================================================

        System.out.println("\n--- Map Operations ---\n");

        // TODO 6: Find a product by ID
        // Product found = inventory.findProductById("P004");
        // System.out.println("Found P004: " + found);

        // TODO 7: Get products by category
        // List<Product> electronics = inventory.getProductsByCategory("Electronics");
        // System.out.println("Electronics: " + electronics.size() + " products");

        // TODO 8: Get category counts
        // Map<String, Integer> categoryCounts = inventory.getCategoryCounts();
        // System.out.println("Categories: " + categoryCounts);

        // =====================================================================
        // STEP 5: TEST SET OPERATIONS
        // =====================================================================

        System.out.println("\n--- Set Operations ---\n");

        // TODO 9: Get all unique categories
        // Set<String> categories = inventory.getAllCategories();
        // System.out.println("All categories: " + categories);

        // TODO 10: Check product existence
        // System.out.println("P001 exists? " + inventory.productExists("P001"));
        // System.out.println("P999 exists? " + inventory.productExists("P999"));

        // =====================================================================
        // STEP 6: TEST QUEUE OPERATIONS
        // =====================================================================

        System.out.println("\n--- Queue Operations (Restock) ---\n");

        // TODO 11: Add low-stock items to restock queue
        // List<Product> lowStock = inventory.getLowStockProducts(10);
        // System.out.println("Low stock items:");
        // for (Product p : lowStock) {
        //     System.out.println("  " + p.getName() + " (qty: " + p.getQuantity() + ")");
        //     inventory.addToRestockQueue(p.getProductId());
        // }

        // TODO 12: Process restock queue (FIFO)
        // System.out.println("\nProcessing restock queue:");
        // while (inventory.getRestockQueueSize() > 0) {
        //     String id = inventory.processNextRestock();
        //     System.out.println("  Restocked: " + id);
        // }

        // =====================================================================
        // STEP 7: SUMMARY
        // =====================================================================

        System.out.println("\n--- Inventory Summary ---\n");

        // TODO 13: Print summary statistics
        // System.out.println("Total products: " + inventory.getProductCount());
        // System.out.println("Total value: $" + String.format("%.2f", inventory.getTotalInventoryValue()));

        System.out.println("\n============================================");
        System.out.println("   INVENTORY SYSTEM DEMO COMPLETE");
        System.out.println("============================================");
    }
}

