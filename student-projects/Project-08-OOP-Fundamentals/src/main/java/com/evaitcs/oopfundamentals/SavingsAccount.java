package com.evaitcs.oopfundamentals;

/**
 * ============================================================================
 * CLASS: SavingsAccount
 * TOPIC: Inheritance + Polymorphism — Extending the abstract BankAccount
 * ============================================================================
 *
 * INHERITANCE: SavingsAccount "IS-A" BankAccount
 *   - It inherits: accountNumber, balance, owner, isActive, deposit()
 *   - It MUST implement: calculateInterest(), getAccountType(), withdraw(), transfer()
 *
 * METHOD OVERRIDING (Runtime Polymorphism):
 *   - When you override a method, the subclass version is called at runtime
 *   - Use @Override annotation to mark overridden methods
 *   - The JVM decides which version to call based on the ACTUAL object type
 *
 * INTERVIEW TIP:
 * "In a SavingsAccount, withdrawal is restricted by a minimum balance.
 *  This is why we override the withdraw method — different rules for
 *  different account types. That's polymorphism in action!"
 * ============================================================================
 */
public class SavingsAccount extends BankAccount {

    // =========================================================================
    // ADDITIONAL FIELDS (specific to SavingsAccount)
    // =========================================================================

    // TODO 1: Declare private fields:
    //   - interestRate (double) — e.g., 0.05 for 5%
    //   - minimumBalance (double) — e.g., 100.0 (can't go below this)
    //   - withdrawalLimit (int) — max withdrawals per month
    //   - withdrawalCount (int) — tracks withdrawals this month


    // =========================================================================
    // CONSTRUCTOR
    // =========================================================================

    /**
     * TODO 2: Create a constructor for SavingsAccount
     * Parameters: accountNumber, owner, initialBalance, interestRate, minimumBalance
     *
     * Call the parent constructor using super(accountNumber, owner, initialBalance)
     * Then initialize the SavingsAccount-specific fields.
     * Set withdrawalLimit to 6 (industry standard for savings accounts).
     * Set withdrawalCount to 0.
     */
    // YOUR CONSTRUCTOR HERE


    // =========================================================================
    // IMPLEMENT ABSTRACT METHODS
    // =========================================================================

    /**
     * TODO 3: Implement calculateInterest()
     * Formula: balance * interestRate
     * Return the calculated interest amount.
     *
     * @Override tells the compiler this method overrides an abstract method.
     */
    @Override
    public double calculateInterest() {
        // TODO: Return balance * interestRate
        return 0.0; // Replace this line
    }

    /**
     * TODO 4: Implement getAccountType()
     * Return the string "Savings"
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
     * Rules for savings account withdrawal:
     * 1. Account must be active
     * 2. Amount must be positive
     * 3. Resulting balance must be >= minimumBalance
     * 4. withdrawalCount must be < withdrawalLimit
     *
     * If successful:
     * - Subtract amount from balance
     * - Increment withdrawalCount
     * - Print: "Withdrawn $[amount]. New balance: $[balance]"
     * - Return true
     *
     * If any rule is violated, print an appropriate error message and return false.
     */
    @Override
    public boolean withdraw(double amount) {
        // TODO: Implement the withdrawal logic with all the rules above

        return false; // Replace this line
    }

    /**
     * TODO 6: Implement transfer(Transactable toAccount, double amount)
     * Steps:
     * 1. Withdraw the amount from this account (call this.withdraw())
     * 2. If withdrawal succeeds, deposit into toAccount
     * 3. If deposit fails, refund the amount back to this account
     * 4. Return true if successful, false otherwise
     */
    @Override
    public boolean transfer(Transactable toAccount, double amount) {
        // TODO: Implement transfer logic

        return false; // Replace this line
    }

    // =========================================================================
    // ADDITIONAL METHODS
    // =========================================================================

    /**
     * TODO 7: Create a method to apply interest
     * Calculate interest using calculateInterest() and add it to the balance.
     * Print: "Interest of $[interest] applied. New balance: $[balance]"
     */
    public void applyInterest() {
        // TODO: Calculate interest and add to balance
    }

    /**
     * TODO 8: Create a method to reset the monthly withdrawal count
     * This would be called at the start of each month.
     * Set withdrawalCount back to 0.
     * Print: "Withdrawal count reset for account [accountNumber]"
     */
    public void resetMonthlyWithdrawals() {
        // TODO: Reset withdrawalCount to 0
    }

    /**
     * TODO 9: Create getters for the SavingsAccount-specific fields
     * - getInterestRate()
     * - getMinimumBalance()
     * - getWithdrawalCount()
     * - getWithdrawalLimit()
     */
    // YOUR GETTERS HERE

}

