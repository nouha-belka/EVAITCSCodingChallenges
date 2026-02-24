package com.evaitcs.unittesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * ============================================================================
 * TEST CLASS: ShoppingCartTest
 * TOPIC: JUnit 5 — Writing comprehensive unit tests
 * ============================================================================
 *
 * TDD APPROACH (Red → Green → Refactor):
 * 1. RED: Write a failing test (the method isn't implemented yet)
 * 2. GREEN: Implement the minimum code to pass the test
 * 3. REFACTOR: Clean up the code while keeping tests green
 *
 * TEST NAMING CONVENTION:
 * methodName_condition_expectedResult
 * Example: addItem_validItem_itemIsAdded
 *
 * ARRANGE-ACT-ASSERT PATTERN:
 * Arrange: Set up test data
 * Act:     Call the method being tested
 * Assert:  Verify the result
 *
 * INTERVIEW TIP:
 * "I follow TDD when building new features. I write tests first to define
 *  expected behavior, then implement code to pass them. This ensures
 *  high test coverage and catches regressions early."
 * ============================================================================
 */
class ShoppingCartTest {

    // This will be recreated before EACH test (ensures test independence!)
    private ShoppingCart cart;

    /**
     * @BeforeEach runs before EVERY test method.
     * This gives each test a FRESH cart — tests don't affect each other!
     */
    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
    }

    // =========================================================================
    // TESTS FOR: addItem()
    // =========================================================================

    /**
     * TODO 1: Test adding a valid item to the cart
     * Arrange: Create a CartItem
     * Act: Add it to the cart
     * Assert: Cart should contain 1 item, item count should be 1
     */
    @Test
    @DisplayName("addItem: Adding a valid item increases cart size")
    void addItem_validItem_itemIsAdded() {
        // Arrange
        CartItem item = new CartItem("123", "Nouha", 123.0, 5);
        // CartItem item = new CartItem("P001", "Laptop", 999.99, 1);
        
        // Act
        // cart.addItem(item);
        cart.addItem(item);
        
        // Assert
        // assertEquals(1, cart.getItemCount(), "Cart should have 1 item");
        assertEquals(1, cart.getItemCount(),"1 item in cart");
        // assertFalse(cart.isEmpty(), "Cart should not be empty");
        assertFalse(cart.isEmpty(), "Cart should not be empty");
    }

    /**
     * TODO 2: Test adding null item throws exception
     */
    @Test
    @DisplayName("addItem: Adding null item throws IllegalArgumentException")
    void addItem_nullItem_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            cart.addItem(null);
        });
        // assertThrows(IllegalArgumentException.class, () -> {
        //     cart.addItem(null);
        // });
    }

    /**
     * TODO 3: Test adding duplicate item increases quantity
     */
    @Test
    @DisplayName("addItem: Adding duplicate product increases quantity")
    void addItem_duplicateProduct_quantityIncreases() {
        // Arrange
        CartItem item1 = new CartItem("P001", "Laptop", 999.99, 1);
        CartItem item2 = new CartItem("P001", "Laptop", 999.99, 2);

        // Act
        cart.addItem(item1);
        cart.addItem(item2);

        // Assert — should be 1 unique item with quantity 3
        assertEquals(1, cart.getItemCount(), "Should still be 1 unique item");
        assertEquals(3, cart.getTotalQuantity(), "Quantity should be 3");
    }

    // =========================================================================
    // TESTS FOR: removeItem()
    // =========================================================================

    /**
     * TODO 4: Test removing an existing item
     */
    @Test
    @DisplayName("removeItem: Removing existing item returns true")
    void removeItem_existingItem_returnsTrue() {
        // Arrange
        cart.addItem(new CartItem("P001", "Laptop", 999.99, 1));
        // cart.addItem(new CartItem("P001", "Laptop", 999.99, 1));
        
        // Act
        boolean removed = cart.removeItem("P001");
        // boolean removed = cart.removeItem("P001");

        // Assert
        assertTrue(removed, "should return true" );
        assertTrue(cart.isEmpty(), "Should return true, car is empty after removal");
        // assertTrue(removed, "Should return true for existing item");
        // assertTrue(cart.isEmpty(), "Cart should be empty after removal");
    }

    /**
     * TODO 5: Test removing a non-existent item
     */
    @Test
    @DisplayName("removeItem: Removing non-existent item returns false")
    void removeItem_nonExistentItem_returnsFalse() {
        boolean removed = cart.removeItem("FAKE_ID");
        assertFalse(removed, "Should return false for non-existent item");
    }

    // =========================================================================
    // TESTS FOR: getTotal()
    // =========================================================================

    /**
     * TODO 6: Test total calculation with multiple items
     */
    @Test
    @DisplayName("getTotal: Correctly sums all item subtotals")
    void getTotal_multipleItems_calculatesCorrectly() {
        // Arrange
        cart.addItem(new CartItem("P001", "Laptop", 1000.00, 1));   // 1000.00
        cart.addItem(new CartItem("P002", "Mouse", 25.00, 2));      // 50.00

        // Act
        double total = cart.getTotal();

        // Assert
        assertEquals(1050.00, total, 0.01, "Total should be $1050.00");
    }

    /**
     * TODO 7: Test total of empty cart is zero
     */
    @Test
    @DisplayName("getTotal: Empty cart returns 0.0")
    void getTotal_emptyCart_returnsZero() {
        assertEquals(0.0, cart.getTotal(), 0.01);
    }

    // =========================================================================
    // TESTS FOR: applyDiscount()
    // =========================================================================

    /**
     * TODO 8: Test applying a valid discount
     */
    @Test
    @DisplayName("applyDiscount: 10% discount on $100 total returns $90")
    void applyDiscount_validPercent_calculatesCorrectly() {
        // Arrange
        // cart.addItem(new CartItem("P001", "Item", 100.00, 1));

        // Act
        // double discounted = cart.applyDiscount(10);

        // Assert
        // assertEquals(90.00, discounted, 0.01, "10% off $100 should be $90");
    }

    /**
     * TODO 9: Test applying invalid discount (negative)
     */
    @Test
    @DisplayName("applyDiscount: Negative discount throws exception")
    void applyDiscount_negativePercent_throwsException() {
        // assertThrows(IllegalArgumentException.class, () -> {
        //     cart.applyDiscount(-10);
        // });
    }

    /**
     * TODO 10: Test applying invalid discount (> 100%)
     */
    @Test
    @DisplayName("applyDiscount: Discount > 100% throws exception")
    void applyDiscount_over100Percent_throwsException() {
        // assertThrows(IllegalArgumentException.class, () -> {
        //     cart.applyDiscount(150);
        // });
    }

    // =========================================================================
    // TESTS FOR: findItem()
    // =========================================================================

    /**
     * TODO 11: Test finding an existing item returns non-empty Optional
     */
    @Test
    @DisplayName("findItem: Finding existing item returns present Optional")
    void findItem_existingItem_returnsPresent() {
        // cart.addItem(new CartItem("P001", "Laptop", 999.99, 1));
        // assertTrue(cart.findItem("P001").isPresent(), "Should find the item");
    }

    /**
     * TODO 12: Test finding non-existent item returns empty Optional
     */
    @Test
    @DisplayName("findItem: Finding non-existent item returns empty Optional")
    void findItem_nonExistentItem_returnsEmpty() {
        // assertTrue(cart.findItem("FAKE").isEmpty(), "Should not find the item");
    }

    // =========================================================================
    // TESTS FOR: clear() and isEmpty()
    // =========================================================================

    /**
     * TODO 13: Test clearing the cart
     */
    @Test
    @DisplayName("clear: After clearing, cart is empty")
    void clear_afterAddingItems_cartIsEmpty() {
        // cart.addItem(new CartItem("P001", "Laptop", 999.99, 1));
        // cart.addItem(new CartItem("P002", "Mouse", 25.00, 1));
        // cart.clear();
        // assertTrue(cart.isEmpty(), "Cart should be empty after clear");
        // assertEquals(0, cart.getItemCount(), "Item count should be 0");
    }

    /**
     * TODO 14: Test new cart is empty
     */
    @Test
    @DisplayName("isEmpty: New cart is empty")
    void isEmpty_newCart_returnsTrue() {
        // assertTrue(cart.isEmpty(), "New cart should be empty");
    }

    // =========================================================================
    // EDGE CASE TESTS — These separate good developers from great ones!
    // =========================================================================

    /**
     * TODO 15: Test CartItem with invalid price throws exception
     */
    @Test
    @DisplayName("CartItem: Negative price throws IllegalArgumentException")
    void cartItem_negativePrice_throwsException() {
        // assertThrows(IllegalArgumentException.class, () -> {
        //     new CartItem("P001", "Bad Item", -10.00, 1);
        // });
    }

    /**
     * TODO 16: Test CartItem with zero quantity throws exception
     */
    @Test
    @DisplayName("CartItem: Zero quantity throws IllegalArgumentException")
    void cartItem_zeroQuantity_throwsException() {
        // assertThrows(IllegalArgumentException.class, () -> {
        //     new CartItem("P001", "Bad Item", 10.00, 0);
        // });
    }

    /**
     * TODO 17: Test applying 0% discount returns full price
     */
    @Test
    @DisplayName("applyDiscount: 0% discount returns original total")
    void applyDiscount_zeroPercent_returnsOriginalTotal() {
        // cart.addItem(new CartItem("P001", "Item", 100.00, 1));
        // assertEquals(100.00, cart.applyDiscount(0), 0.01);
    }

    /**
     * TODO 18: Test applying 100% discount returns zero
     */
    @Test
    @DisplayName("applyDiscount: 100% discount returns $0.00")
    void applyDiscount_hundredPercent_returnsZero() {
        // cart.addItem(new CartItem("P001", "Item", 100.00, 1));
        // assertEquals(0.00, cart.applyDiscount(100), 0.01);
    }
}

