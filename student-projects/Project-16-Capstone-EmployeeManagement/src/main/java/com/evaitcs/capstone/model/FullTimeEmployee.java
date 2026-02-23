package com.evaitcs.capstone.model;

/**
 * ============================================================================
 * CLASS: FullTimeEmployee
 * TOPICS: Inheritance, Polymorphism (method overriding)
 * ============================================================================
 *
 * Full-time employees have:
 * - A fixed annual salary
 * - A bonus percentage
 * - Monthly salary = annualSalary / 12
 * - Annual salary = annualSalary + (annualSalary * bonusPercent / 100)
 * ============================================================================
 */
public class FullTimeEmployee extends Employee {

    private static final long serialVersionUID = 1L;

    // TODO 1: Declare private fields:
    //   - annualBaseSalary (double)
    //   - bonusPercentage (double) — e.g., 10.0 for 10%


    // TODO 2: Create a constructor
    //   Parameters: employeeId, firstName, lastName, email, department, hireYear,
    //               annualBaseSalary, bonusPercentage
    //   Call super() for the common fields, then set salary/bonus fields
    //   Validate: annualBaseSalary > 0, bonusPercentage >= 0


    // TODO 3: Implement calculateMonthlySalary()
    //   Return: annualBaseSalary / 12
    @Override
    public double calculateMonthlySalary() {
        return 0.0; // Replace this line
    }

    // TODO 4: Implement calculateAnnualSalary()
    //   Return: annualBaseSalary + (annualBaseSalary * bonusPercentage / 100)
    @Override
    public double calculateAnnualSalary() {
        return 0.0; // Replace this line
    }

    // TODO 5: Implement getEmployeeType()
    //   Return: "Full-Time"
    @Override
    public String getEmployeeType() {
        return ""; // Replace this line
    }

    // TODO 6: Create getters for annualBaseSalary and bonusPercentage

    // TODO 7: Create a setter for bonusPercentage with validation (>= 0, <= 50)
}

