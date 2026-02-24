package com.evaitcs.unittesting;

import java.util.Arrays;
import java.util.List;

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
        if(total <0 ){
            throw new IllegalArgumentException("totalt can't be negative");
        }
        if(total >= 500 ){
            return 20.0;

        }else if (total >= 200 ) {
            return 10.0;
        }else if (total >= 100 ) {
            return 5.0;
        }else{
            return 0.0;
        }
    }

    /**
     * TODO 2: Calculate the final price after discount
     *
     * @param total the original total
     * @return the total after applying the appropriate discount
     */
    public double calculateFinalPrice(double total) {
        // TODO: Get discount percent, apply it, return final price
        return total - total * (getDiscountPercent(total) / 100); // Replace this line
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
        List<String> validCodes =  Arrays.asList("SAVE10", "WELCOME20", "VIP30");
        return validCodes.contains(code); // Replace this line
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
        if(code.equals("SAVE10" )){
            return 10.0;
        }else if(code.equals("WELCOME20" )){
            return 20.0;
        }else if(code.equals("VIP30" )){
            return 30.0;
        }else{
            throw new IllegalArgumentException("invalid promo code");
        }

    }
}

