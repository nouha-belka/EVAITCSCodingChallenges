package com.evaitcs.oopfundamentals;

/**
 * ============================================================================
 * CLASS: Customer
 * TOPIC: Encapsulation — Protecting data with private fields and validation
 * ============================================================================
 *
 * ENCAPSULATION means:
 * 1. Make fields PRIVATE (hide the data)
 * 2. Provide PUBLIC getters (controlled reading)
 * 3. Provide PUBLIC setters WITH VALIDATION (controlled writing)
 *
 * WHY? Imagine a bank letting anyone directly change account balances
 * without validation. Chaos! Encapsulation prevents this.
 *
 * INTERVIEW TIP:
 * "Encapsulation bundles data and methods together, restricts direct access
 *  to data, and ensures data integrity through validation in setters."
 * ============================================================================
 */
public class Customer {

    // =========================================================================
    // PRIVATE FIELDS — These cannot be accessed directly from outside!
    // =========================================================================

    // TODO 1: Declare private fields for:
    //   - customerId (String)
    //   - firstName (String)
    //   - lastName (String)
    //   - email (String)
    //   - phoneNumber (String)
    //   - age (int)


    // =========================================================================
    // CONSTRUCTOR
    // =========================================================================

    /**
     * TODO 2: Create a constructor that takes all fields as parameters
     * and initializes them. Use the 'this' keyword to distinguish
     * between parameters and fields.
     *
     * Add validation:
     * - firstName and lastName must not be null or empty
     * - age must be between 18 and 120
     * - email must contain '@'
     *
     * If validation fails, throw an IllegalArgumentException with
     * a descriptive message.
     *
     * Example:
     * if (firstName == null || firstName.trim().isEmpty()) {
     *     throw new IllegalArgumentException("First name cannot be empty");
     * }
     */
    // YOUR CONSTRUCTOR HERE


    // =========================================================================
    // GETTERS — Provide read-only access to private fields
    // =========================================================================

    /**
     * TODO 3: Write getter methods for ALL fields.
     *
     * Naming convention: getFieldName()
     * Example:
     * public String getCustomerId() {
     *     return customerId;
     * }
     *
     * Write getters for: customerId, firstName, lastName, email, phoneNumber, age
     */
    // YOUR GETTERS HERE


    /**
     * TODO 4: Write a convenience method getFullName()
     * that returns firstName + " " + lastName
     */
    // YOUR CODE HERE


    // =========================================================================
    // SETTERS WITH VALIDATION — Control how data is modified
    // =========================================================================

    /**
     * TODO 5: Write setter methods WITH VALIDATION for:
     *
     * setEmail(String email):
     *   - Must contain '@' symbol
     *   - Must not be null or empty
     *   - If invalid, throw IllegalArgumentException
     *
     * setPhoneNumber(String phoneNumber):
     *   - Must be exactly 10 digits
     *   - Must not be null
     *   - If invalid, throw IllegalArgumentException
     *
     * setAge(int age):
     *   - Must be between 18 and 120
     *   - If invalid, throw IllegalArgumentException
     *
     * NOTE: customerId should NOT have a setter — it's assigned once
     * and never changes (it's like a primary key in a database).
     */
    // YOUR SETTERS HERE


    // =========================================================================
    // TOSTRING METHOD — For readable output
    // =========================================================================

    /**
     * TODO 6: Override the toString() method
     * Return a formatted string with the customer's information.
     *
     * Example output:
     * "Customer{id='C001', name='John Smith', email='john@email.com', age=30}"
     *
     * The @Override annotation tells the compiler you're overriding
     * a method from the parent class (Object). It helps catch errors!
     */
    @Override
    public String toString() {
        // TODO: Return a formatted string with customer info
        return ""; // Replace this line
    }
}

