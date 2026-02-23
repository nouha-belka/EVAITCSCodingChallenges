package com.evaitcs.unittesting;

/**
 * ============================================================================
 * CLASS: DiscountCalculator
 * TOPIC: Business logic class — great for parameterized test practice
 * ============================================================================
 */
public class DiscountCalculator {

    /**
     * TODO 1: Calculate discount based on total amount
     * Rules:
     *   Total >= $500  → 20% discount
     *   Total >= $200  → 10% discount
     *   Total >= $100  → 5% discount
     *   Total < $100   → 0% discount
     *
     * @param total the cart total before discount
     * @return the discount percentage (0, 5, 10, or 20)
     * @throws IllegalArgumentException if total is negative
     */
    public double getDiscountPercent(double total) {
        // TODO: Implement the discount rules
        return 0.0; // Replace this line
    }

    /**
     * TODO 2: Calculate the final price after discount
     *
     * @param total the original total
     * @return the total after applying the appropriate discount
     */
    public double calculateFinalPrice(double total) {
        // TODO: Get discount percent, apply it, return final price
        return 0.0; // Replace this line
    }

    /**
     * TODO 3: Check if a promo code is valid
     * Valid codes: "SAVE10", "WELCOME20", "VIP30"
     *
     * @param code the promo code
     * @return true if the code is valid
     */
    public boolean isValidPromoCode(String code) {
        // TODO: Check against known promo codes
        return false; // Replace this line
    }

    /**
     * TODO 4: Apply a promo code and return the discount percentage
     * "SAVE10"    → 10%
     * "WELCOME20" → 20%
     * "VIP30"     → 30%
     * Invalid     → throws IllegalArgumentException
     *
     * @param code the promo code
     * @return the discount percentage for the code
     */
    public double getPromoDiscount(String code) {
        // TODO: Return the discount for the promo code
        return 0.0; // Replace this line
    }
}

