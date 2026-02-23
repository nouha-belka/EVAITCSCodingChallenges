package com.evaitcs.oopfundamentals;

/**
 * ============================================================================
 * MAIN APPLICATION: BankingApp
 * TOPIC: Putting it all together — OOP in action!
 * ============================================================================
 *
 * This is where you test everything you've built. You'll create Customer
 * objects, open different account types, and perform transactions.
 *
 * PAY ATTENTION TO POLYMORPHISM:
 * Notice how we can store both SavingsAccount and CheckingAccount
 * in a BankAccount variable — that's the power of polymorphism!
 *
 * BankAccount account1 = new SavingsAccount(...);   // Polymorphism!
 * BankAccount account2 = new CheckingAccount(...);  // Polymorphism!
 * account1.withdraw(100);  // Calls SavingsAccount's withdraw
 * account2.withdraw(100);  // Calls CheckingAccount's withdraw
 * ============================================================================
 */
public class BankingApp {

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("   WELCOME TO EVAITCS BANKING SYSTEM");
        System.out.println("============================================\n");

        // =====================================================================
        // STEP 1: CREATE CUSTOMERS (Tests Encapsulation)
        // =====================================================================

        // TODO 1: Create two Customer objects using the constructor you built.
        // Customer 1: "C001", "John", "Smith", "john@email.com", "5551234567", 30
        // Customer 2: "C002", "Jane", "Doe", "jane@email.com", "5559876543", 25
        //
        // Example: Customer customer1 = new Customer("C001", "John", "Smith", ...);


        // TODO 2: Print both customers using System.out.println()
        // This will call YOUR toString() method!


        // TODO 3: Test encapsulation — try calling customer1.setAge(-5)
        // It should throw an IllegalArgumentException because of your validation!
        // Wrap it in a try/catch block:
        //
        // try {
        //     customer1.setAge(-5);
        // } catch (IllegalArgumentException e) {
        //     System.out.println("Validation works! " + e.getMessage());
        // }


        System.out.println("\n--- Account Creation ---\n");

        // =====================================================================
        // STEP 2: CREATE ACCOUNTS (Tests Inheritance & Abstraction)
        // =====================================================================

        // TODO 4: Create a SavingsAccount for customer1
        // Parameters: "SAV001", customer1, 5000.0, 0.05 (5% interest), 500.0 (min balance)
        //
        // Use the PARENT type for the variable — this demonstrates POLYMORPHISM:
        // BankAccount savingsAccount = new SavingsAccount("SAV001", customer1, 5000.0, 0.05, 500.0);


        // TODO 5: Create a CheckingAccount for customer2
        // Parameters: "CHK001", customer2, 3000.0, 1000.0 (overdraft limit), 1.50 (transaction fee)
        //
        // BankAccount checkingAccount = new CheckingAccount("CHK001", customer2, 3000.0, 1000.0, 1.50);


        // TODO 6: Print both accounts using System.out.println()
        // This calls YOUR toString() — notice how each shows a different account type!


        System.out.println("\n--- Transactions ---\n");

        // =====================================================================
        // STEP 3: PERFORM TRANSACTIONS (Tests Polymorphism)
        // =====================================================================

        // TODO 7: Deposit money into both accounts
        // savingsAccount.deposit(1000.0);
        // checkingAccount.deposit(500.0);


        // TODO 8: Withdraw money from both accounts
        // Notice how the SAME method call (withdraw) behaves DIFFERENTLY
        // for SavingsAccount vs CheckingAccount — that's polymorphism!
        //
        // savingsAccount.withdraw(200.0);   // Has minimum balance rule
        // checkingAccount.withdraw(200.0);  // Has overdraft and transaction fee


        // TODO 9: Try to withdraw too much from savings (should fail — minimum balance)
        // savingsAccount.withdraw(6000.0);  // Should print error


        // TODO 10: Try the overdraft on checking account
        // checkingAccount.withdraw(3500.0);  // Should work if within overdraft limit


        System.out.println("\n--- Transfers ---\n");

        // =====================================================================
        // STEP 4: TRANSFER BETWEEN ACCOUNTS
        // =====================================================================

        // TODO 11: Transfer money from savings to checking
        // savingsAccount.transfer(checkingAccount, 500.0);


        // TODO 12: Print final balances
        // System.out.println("Savings balance: $" + savingsAccount.getBalance());
        // System.out.println("Checking balance: $" + checkingAccount.getBalance());


        System.out.println("\n--- Interest Calculation ---\n");

        // =====================================================================
        // STEP 5: CALCULATE INTEREST (Tests Abstract Methods)
        // =====================================================================

        // TODO 13: Calculate and print interest for both accounts
        // Notice: same method name, different results — abstract methods in action!
        //
        // System.out.println("Savings interest: $" + savingsAccount.calculateInterest());
        // System.out.println("Checking interest: $" + checkingAccount.calculateInterest());


        // TODO 14: If your savingsAccount is actually a SavingsAccount, apply interest:
        // You need to CAST it:
        //
        // if (savingsAccount instanceof SavingsAccount) {
        //     ((SavingsAccount) savingsAccount).applyInterest();
        // }


        System.out.println("\n--- Polymorphism Demo ---\n");

        // =====================================================================
        // STEP 6: POLYMORPHISM ARRAY DEMO
        // =====================================================================

        // TODO 15: Create an array of BankAccount and store BOTH account types in it
        // BankAccount[] accounts = { savingsAccount, checkingAccount };
        //
        // Loop through the array and for EACH account, print:
        //   - Account type (getAccountType())
        //   - Account number
        //   - Balance
        //   - Calculated interest
        //
        // for (BankAccount account : accounts) {
        //     System.out.println("Type: " + account.getAccountType());
        //     System.out.println("Number: " + account.getAccountNumber());
        //     System.out.println("Balance: $" + account.getBalance());
        //     System.out.println("Interest: $" + account.calculateInterest());
        //     System.out.println("---");
        // }
        //
        // NOTICE: The loop doesn't know if it's a Savings or Checking account,
        // but Java calls the RIGHT version of each method! That's polymorphism!


        System.out.println("\n============================================");
        System.out.println("   BANKING SYSTEM DEMO COMPLETE");
        System.out.println("============================================");
    }
}

