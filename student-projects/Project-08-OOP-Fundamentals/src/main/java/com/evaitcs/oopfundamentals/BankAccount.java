package com.evaitcs.oopfundamentals;

/**
 * ============================================================================
 * ABSTRACT CLASS: BankAccount
 * TOPIC: Abstraction + Inheritance — The base class for ALL account types
 * ============================================================================
 *
 * ABSTRACT CLASS means:
 * - You CANNOT create objects of this class directly (no: new BankAccount())
 * - It CAN have abstract methods (methods without a body)
 * - It CAN have concrete methods (methods with a body)
 * - Subclasses MUST implement all abstract methods
 *
 * WHY ABSTRACT?
 * A "BankAccount" alone doesn't make sense — you need a SPECIFIC type
 * like SavingsAccount or CheckingAccount. But they share common behavior!
 *
 * INHERITANCE means:
 * SavingsAccount "IS-A" BankAccount (extends)
 * CheckingAccount "IS-A" BankAccount (extends)
 * They inherit all non-private fields and methods from BankAccount.
 *
 * INTERVIEW TIP:
 * "Abstract classes provide partial abstraction — some methods are implemented,
 *  some are left for subclasses. Use them when classes share common code."
 * ============================================================================
 */
public abstract class BankAccount implements Transactable {

    // =========================================================================
    // PROTECTED FIELDS
    // =========================================================================
    // 'protected' means: accessible in this class AND in subclasses
    // 'private' would mean subclasses can't see these fields directly
    // =========================================================================

    // TODO 1: Declare the following protected fields:
    //   - accountNumber (String)
    //   - balance (double)
    //   - owner (Customer) — notice this is COMPOSITION: BankAccount "has-a" Customer
    //   - isActive (boolean)


    // =========================================================================
    // CONSTRUCTOR
    // =========================================================================

    /**
     * TODO 2: Create a constructor for BankAccount
     * Parameters: accountNumber, owner, initialBalance
     *
     * Validation:
     * - accountNumber must not be null or empty
     * - owner must not be null
     * - initialBalance must be >= 0
     *
     * Set isActive to true by default.
     *
     * NOTE: Even though you can't instantiate an abstract class directly,
     * subclasses call this constructor using super(...)!
     */
    // YOUR CONSTRUCTOR HERE


    // =========================================================================
    // CONCRETE METHODS — These are shared by ALL account types
    // =========================================================================

    /**
     * TODO 3: Implement getBalance() getter
     */
    // YOUR CODE HERE

    /**
     * TODO 4: Implement getAccountNumber() getter
     */
    // YOUR CODE HERE

    /**
     * TODO 5: Implement getOwner() getter
     */
    // YOUR CODE HERE

    /**
     * TODO 6: Implement isActive() getter
     */
    // YOUR CODE HERE

    /**
     * TODO 7: Implement a closeAccount() method
     * Sets isActive to false.
     * Should print: "Account [number] has been closed."
     */
    // YOUR CODE HERE


    // =========================================================================
    // ABSTRACT METHODS — Subclasses MUST implement these!
    // =========================================================================

    /**
     * TODO 8: Declare an abstract method: calculateInterest()
     * Returns: double (the interest amount)
     * No body! Just the signature with 'abstract' keyword.
     *
     * WHY ABSTRACT? Because savings accounts and checking accounts
     * calculate interest differently!
     *
     * Syntax: public abstract double calculateInterest();
     */
    // YOUR CODE HERE


    /**
     * TODO 9: Declare an abstract method: getAccountType()
     * Returns: String (e.g., "Savings" or "Checking")
     */
    // YOUR CODE HERE


    // =========================================================================
    // METHOD OVERLOADING (Compile-time Polymorphism)
    // =========================================================================
    // Same method name, DIFFERENT parameters = overloading
    // The compiler decides which version to call based on the arguments.
    // =========================================================================

    /**
     * TODO 10: Create overloaded deposit methods:
     *
     * Version 1: deposit(double amount)
     *   - Already declared in Transactable interface
     *   - Validates amount > 0 and account is active
     *   - Adds amount to balance
     *   - Prints: "Deposited $[amount]. New balance: $[balance]"
     *   - Returns true if successful
     *
     * Version 2: deposit(double amount, String description)
     *   - Calls version 1 to do the actual deposit
     *   - Also prints the description
     *   - Returns true if successful
     *
     * This is METHOD OVERLOADING — same name, different parameters!
     */
    // YOUR CODE HERE


    // =========================================================================
    // TOSTRING
    // =========================================================================

    /**
     * TODO 11: Override toString()
     * Format: "AccountType[number=XXX, owner=Name, balance=$XX.XX, active=true]"
     * Use getAccountType() for the account type.
     */
    @Override
    public String toString() {
        return ""; // Replace this line
    }
}

