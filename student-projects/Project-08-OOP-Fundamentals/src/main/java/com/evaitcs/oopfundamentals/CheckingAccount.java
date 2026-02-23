package com.evaitcs.oopfundamentals;

/**
 * ============================================================================
 * CLASS: CheckingAccount
 * TOPIC: Inheritance + Polymorphism — Another subclass of BankAccount
 * ============================================================================
 *
 * POLYMORPHISM IN ACTION:
 * CheckingAccount and SavingsAccount BOTH extend BankAccount,
 * but they implement withdraw() and calculateInterest() DIFFERENTLY.
 *
 * When you write: BankAccount account = new CheckingAccount(...);
 * And then call:  account.withdraw(100);
 * Java calls CheckingAccount's version — that's RUNTIME POLYMORPHISM!
 *
 * INTERVIEW TIP:
 * "Polymorphism allows me to write code that works with the parent type
 *  (BankAccount) but executes the behavior of the actual child type
 *  (SavingsAccount or CheckingAccount) at runtime."
 * ============================================================================
 */
public class CheckingAccount extends BankAccount {

    // =========================================================================
    // ADDITIONAL FIELDS
    // =========================================================================

    // TODO 1: Declare private fields:
    //   - overdraftLimit (double) — how far below 0 the balance can go
    //   - transactionFee (double) — fee charged per withdrawal (e.g., 1.50)


    // =========================================================================
    // CONSTRUCTOR
    // =========================================================================

    /**
     * TODO 2: Create a constructor
     * Parameters: accountNumber, owner, initialBalance, overdraftLimit, transactionFee
     * Call super() and initialize CheckingAccount-specific fields.
     */
    // YOUR CONSTRUCTOR HERE


    // =========================================================================
    // IMPLEMENT ABSTRACT METHODS
    // =========================================================================

    /**
     * TODO 3: Implement calculateInterest()
     * Checking accounts typically have very low or 0% interest.
     * Return: balance * 0.001 (0.1% interest rate)
     */
    @Override
    public double calculateInterest() {
        return 0.0; // Replace this line
    }

    /**
     * TODO 4: Implement getAccountType()
     * Return "Checking"
     */
    @Override
    public String getAccountType() {
        return ""; // Replace this line
    }

    // =========================================================================
    // IMPLEMENT INTERFACE METHODS (from Transactable)
    // =========================================================================

    /**
     * TODO 5: Implement withdraw(double amount)
     * Rules for checking account:
     * 1. Account must be active
     * 2. Amount must be positive
     * 3. (balance - amount - transactionFee) must be >= (-overdraftLimit)
     *    (allows going negative up to the overdraft limit)
     *
     * If successful:
     * - Subtract (amount + transactionFee) from balance
     * - Print: "Withdrawn $[amount] (fee: $[fee]). New balance: $[balance]"
     * - Return true
     *
     * If not:
     * - Print: "Insufficient funds. Available (including overdraft): $[available]"
     * - Return false
     */
    @Override
    public boolean withdraw(double amount) {
        // TODO: Implement withdrawal logic with overdraft and transaction fee

        return false; // Replace this line
    }

    /**
     * TODO 6: Implement transfer(Transactable toAccount, double amount)
     * Same logic as SavingsAccount — withdraw from this, deposit to toAccount.
     */
    @Override
    public boolean transfer(Transactable toAccount, double amount) {
        // TODO: Implement transfer

        return false; // Replace this line
    }

    // =========================================================================
    // ADDITIONAL METHODS
    // =========================================================================

    /**
     * TODO 7: Create getters for overdraftLimit and transactionFee
     */
    // YOUR GETTERS HERE


    /**
     * TODO 8: Create a method to check available funds (including overdraft)
     * Method name: getAvailableFunds
     * Returns: balance + overdraftLimit
     */
    // YOUR CODE HERE

}

