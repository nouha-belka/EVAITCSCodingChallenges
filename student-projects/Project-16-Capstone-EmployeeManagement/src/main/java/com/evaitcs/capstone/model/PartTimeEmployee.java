package com.evaitcs.capstone.model;

/**
 * ============================================================================
 * CLASS: PartTimeEmployee
 * TOPICS: Inheritance, Polymorphism (method overriding)
 * ============================================================================
 *
 * Part-time employees have:
 * - An hourly rate
 * - Hours worked per week
 * - Monthly salary = hourlyRate * hoursPerWeek * 4 (approx weeks/month)
 * - Annual salary = monthly * 12 (no bonus for part-time)
 * ============================================================================
 */
public class PartTimeEmployee extends Employee {

    private static final long serialVersionUID = 1L;

    // TODO 1: Declare private fields:
    //   - hourlyRate (double)
    //   - hoursPerWeek (int)


    // TODO 2: Create a constructor
    //   Call super(), validate hourlyRate > 0, hoursPerWeek between 1 and 40


    // TODO 3: Implement calculateMonthlySalary()
    //   Return: hourlyRate * hoursPerWeek * 4
    @Override
    public double calculateMonthlySalary() {
        return 0.0; // Replace this line
    }

    // TODO 4: Implement calculateAnnualSalary()
    //   Return: calculateMonthlySalary() * 12
    @Override
    public double calculateAnnualSalary() {
        return 0.0; // Replace this line
    }

    // TODO 5: Implement getEmployeeType()
    //   Return: "Part-Time"
    @Override
    public String getEmployeeType() {
        return ""; // Replace this line
    }

    // TODO 6: Create getters and validated setters for hourlyRate and hoursPerWeek
}

