package com.evaitcs.capstone.model;

import java.io.Serializable;

/**
 * ============================================================================
 * ABSTRACT CLASS: Employee
 * TOPICS: Abstraction, Inheritance, Encapsulation, Serialization
 * ============================================================================
 *
 * This is the BASE class for all employee types.
 * It demonstrates:
 * - ABSTRACTION: abstract methods for salary calculation
 * - ENCAPSULATION: private fields with validated setters
 * - INHERITANCE: FullTimeEmployee and PartTimeEmployee extend this
 * - SERIALIZATION: implements Serializable for file persistence
 *
 * INTERVIEW TIP:
 * "I designed Employee as an abstract class because different employee types
 *  share common data (name, id, department) but calculate pay differently.
 *  This follows the Open/Closed Principle — I can add new employee types
 *  without modifying existing code."
 * ============================================================================
 */
public abstract class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    // =========================================================================
    // TODO 1: Declare private fields
    //   - employeeId (String)
    //   - firstName (String)
    //   - lastName (String)
    //   - email (String)
    //   - department (Department enum)
    //   - status (EmployeeStatus enum)
    //   - hireYear (int)
    // =========================================================================


    // =========================================================================
    // TODO 2: Create a constructor with validation
    //   - No field should be null
    //   - firstName and lastName must not be empty
    //   - email must contain '@'
    //   - hireYear must be between 1970 and current year
    //   - Set status to ACTIVE by default
    //   Throw IllegalArgumentException for invalid data
    // =========================================================================


    // =========================================================================
    // TODO 3: Create getters for ALL fields
    // =========================================================================


    // =========================================================================
    // TODO 4: Create setters WITH VALIDATION for email, department, status
    //   - employeeId, firstName, lastName, hireYear should NOT have setters
    //     (they don't change after creation)
    // =========================================================================


    // =========================================================================
    // ABSTRACT METHODS — Each employee type implements these differently
    // =========================================================================

    /**
     * TODO 5: Declare abstract method: calculateMonthlySalary()
     * @return the monthly salary for this employee type
     */
    // public abstract double calculateMonthlySalary();

    /**
     * TODO 6: Declare abstract method: calculateAnnualSalary()
     * @return the annual salary including any bonuses
     */
    // public abstract double calculateAnnualSalary();

    /**
     * TODO 7: Declare abstract method: getEmployeeType()
     * @return "Full-Time" or "Part-Time"
     */
    // public abstract String getEmployeeType();


    // =========================================================================
    // CONCRETE METHODS — Shared by all employee types
    // =========================================================================

    /**
     * TODO 8: Create getFullName() — returns "firstName lastName"
     */
    // YOUR CODE HERE

    /**
     * TODO 9: Create getYearsOfService() — current year minus hireYear
     * Hint: java.time.Year.now().getValue() - hireYear
     */
    // YOUR CODE HERE

    /**
     * TODO 10: Override toString()
     * Format: "FullTime[id=E001, name=John Smith, dept=ENGINEERING, status=ACTIVE]"
     */
    @Override
    public String toString() {
        return ""; // Replace this line
    }

    /**
     * TODO 11: Override equals() — two employees are equal if they have the same employeeId
     */
    // YOUR CODE HERE

    /**
     * TODO 12: Override hashCode() — based on employeeId
     */
    // YOUR CODE HERE
}

