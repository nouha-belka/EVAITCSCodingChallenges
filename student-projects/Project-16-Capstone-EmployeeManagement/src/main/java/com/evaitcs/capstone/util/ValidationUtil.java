package com.evaitcs.capstone.util;

/**
 * ============================================================================
 * CLASS: ValidationUtil
 * TOPICS: Static utility methods, Input validation
 * ============================================================================
 *
 * A utility class with static methods for validating user input.
 * Used throughout the application for consistent validation.
 * ============================================================================
 */
public final class ValidationUtil {

    // Private constructor prevents instantiation — utility class!
    private ValidationUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * TODO 1: Validate that a string is not null or empty
     * @param value the string to check
     * @param fieldName the name of the field (for error messages)
     * @throws IllegalArgumentException if null or empty
     */
    public static void requireNonEmpty(String value, String fieldName) {
        // if (value == null || value.trim().isEmpty()) {
        //     throw new IllegalArgumentException(fieldName + " must not be null or empty");
        // }
    }

    /**
     * TODO 2: Validate that a number is positive
     */
    public static void requirePositive(double value, String fieldName) {
        // if (value <= 0) {
        //     throw new IllegalArgumentException(fieldName + " must be positive, got: " + value);
        // }
    }

    /**
     * TODO 3: Validate that a number is within a range (inclusive)
     */
    public static void requireInRange(int value, int min, int max, String fieldName) {
        // if (value < min || value > max) {
        //     throw new IllegalArgumentException(
        //         fieldName + " must be between " + min + " and " + max + ", got: " + value);
        // }
    }

    /**
     * TODO 4: Validate email format (must contain '@' and '.')
     */
    public static void requireValidEmail(String email) {
        // requireNonEmpty(email, "Email");
        // if (!email.contains("@") || !email.contains(".")) {
        //     throw new IllegalArgumentException("Invalid email format: " + email);
        // }
    }

    /**
     * TODO 5: Validate that an object is not null
     */
    public static void requireNonNull(Object obj, String fieldName) {
        // if (obj == null) {
        //     throw new IllegalArgumentException(fieldName + " must not be null");
        // }
    }
}

