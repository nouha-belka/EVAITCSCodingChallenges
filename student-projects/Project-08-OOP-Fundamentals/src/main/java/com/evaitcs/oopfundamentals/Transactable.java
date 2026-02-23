package com.evaitcs.oopfundamentals;

/**
 * ============================================================================
 * INTERFACE: Transactable
 * TOPIC: Abstraction — Interfaces define contracts that classes MUST follow
 * ============================================================================
 *
 * WHY INTERFACES?
 * In a real banking system, different account types handle transactions
 * differently, but they ALL need to support the same basic operations.
 * An interface defines WHAT must be done, not HOW.
 *
 * REAL-WORLD ANALOGY:
 * Think of this like a job description — it says what you need to do,
 * but each employee does it their own way.
 *
 * INTERVIEW TIP:
 * "An interface defines a contract. Any class that implements it
 *  MUST provide implementations for all its abstract methods."
 * ============================================================================
 */
public interface Transactable {

    /**
     * TODO 1: Declare an abstract method for depositing money.
     * Method name: deposit
     * Parameter: double amount
     * Return type: boolean (true if successful, false if not)
     *
     * Note: Interface methods are abstract by default — no body needed!
     * Example: boolean deposit(double amount);
     */
    // YOUR CODE HERE


    /**
     * TODO 2: Declare an abstract method for withdrawing money.
     * Method name: withdraw
     * Parameter: double amount
     * Return type: boolean
     */
    // YOUR CODE HERE


    /**
     * TODO 3: Declare an abstract method for transferring money.
     * Method name: transfer
     * Parameters: Transactable toAccount, double amount
     * Return type: boolean
     */
    // YOUR CODE HERE


    /**
     * TODO 4: Declare a default method for getting a transaction summary.
     * Default methods HAVE a body — they provide a default implementation
     * that classes can optionally override.
     *
     * This was introduced in Java 8. It lets you add methods to interfaces
     * without breaking existing implementations.
     *
     * Method name: getTransactionSummary
     * Return type: String
     * Default body: return "Transaction summary not available.";
     *
     * Syntax: default String getTransactionSummary() { ... }
     */
    // YOUR CODE HERE

}

