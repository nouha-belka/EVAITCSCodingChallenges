package com.evaitcs.oopadvanced;

/**
 * ============================================================================
 * RECORD: EmployeeRecord
 * TOPIC: Records — Immutable data carriers (Java 14+)
 * ============================================================================
 *
 * WHAT ARE RECORDS?
 * Records are a special type of class designed to hold IMMUTABLE data.
 * Java automatically generates:
 *   - Private final fields
 *   - Constructor
 *   - Getters (named after the field, e.g., name() not getName())
 *   - equals(), hashCode(), toString()
 *
 * BEFORE RECORDS (lots of boilerplate):
 *   public class Employee {
 *       private final String name;
 *       private final int age;
 *       // Constructor, getters, equals, hashCode, toString... 50+ lines!
 *   }
 *
 * WITH RECORDS (one line!):
 *   public record Employee(String name, int age) {}
 *
 * INTERVIEW TIP:
 * "Records reduce boilerplate for data-carrying classes. They're ideal for
 *  DTOs, API responses, and value objects where immutability is desired."
 * ============================================================================
 */

// =========================================================================
// TODO 1: Define the EmployeeRecord as a Java Record
// =========================================================================
// A record is declared like:  public record Name(Type field1, Type field2) { }
//
// Define EmployeeRecord with these components:
//   - String employeeId
//   - String firstName
//   - String lastName
//   - Department department  (use the enum you created!)
//   - double salary
//   - String email
//
// Example: public record EmployeeRecord(String employeeId, String firstName, ...) { }

public record EmployeeRecord(
    // TODO: Add your record components here
    String employeeId,
    String firstName,
    String lastName,
    Department department,
    double salary,
    String email
) {

    // =========================================================================
    // COMPACT CONSTRUCTOR — Validation
    // =========================================================================
    // Records support "compact constructors" for validation.
    // You don't need to list the parameters or assign fields — Java does that!
    //
    // public EmployeeRecord {
    //     if (salary < 0) throw new IllegalArgumentException("...");
    // }
    // =========================================================================

    /**
     * TODO 2: Add a compact constructor with validation:
     * - employeeId must not be null or empty
     * - firstName must not be null or empty
     * - lastName must not be null or empty
     * - salary must be >= 0
     * - email must contain '@'
     * - department must not be null
     *
     * Throw IllegalArgumentException for any violation.
     */
    // YOUR COMPACT CONSTRUCTOR HERE
    // Hint: public EmployeeRecord { ... validation only, no assignments ... }


    // =========================================================================
    // CUSTOM METHODS — Records CAN have additional methods!
    // =========================================================================

    /**
     * TODO 3: Create a method fullName() that returns "firstName lastName"
     */
    public String fullName() {
        // TODO: Return firstName + " " + lastName
        return ""; // Replace this line
    }

    /**
     * TODO 4: Create a method getAnnualSalary() that returns salary * 12
     * (assuming salary is monthly)
     */
    public double getAnnualSalary() {
        // TODO: Return salary * 12
        return 0.0; // Replace this line
    }

    /**
     * TODO 5: Create a method isHighEarner() that returns true if
     * annual salary > 100,000
     */
    public boolean isHighEarner() {
        // TODO: Check if annual salary exceeds 100,000
        return false; // Replace this line
    }
}

