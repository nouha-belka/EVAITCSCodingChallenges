package com.evaitcs.capstone.service;

import com.evaitcs.capstone.model.*;
import java.util.List;

/**
 * ============================================================================
 * CLASS: PayrollService
 * TOPICS: SRP, Polymorphism, Collections
 * ============================================================================
 *
 * This class handles ALL payroll calculations.
 * It demonstrates POLYMORPHISM: the same calculateMonthlySalary() method
 * works differently for FullTime vs PartTime employees, but this service
 * doesn't need to know the difference!
 * ============================================================================
 */
public class PayrollService {

    /**
     * TODO 1: Calculate total monthly payroll for a list of employees
     * Uses polymorphism — calls calculateMonthlySalary() on each employee,
     * and Java automatically uses the correct implementation!
     *
     * @param employees list of all employees
     * @return total monthly payroll
     */
    public double calculateTotalMonthlyPayroll(List<Employee> employees) {
        // TODO: Sum calculateMonthlySalary() for all employees
        return 0.0; // Replace this line
    }

    /**
     * TODO 2: Calculate total annual payroll including bonuses
     * @param employees list of all employees
     * @return total annual payroll
     */
    public double calculateTotalAnnualPayroll(List<Employee> employees) {
        // TODO: Sum calculateAnnualSalary() for all employees
        return 0.0; // Replace this line
    }

    /**
     * TODO 3: Find the highest-paid employee
     * @param employees list of employees
     * @return the employee with the highest annual salary
     */
    public Employee getHighestPaidEmployee(List<Employee> employees) {
        // TODO: Loop through employees, track the one with highest salary
        return null; // Replace this line
    }

    /**
     * TODO 4: Calculate average salary
     * @param employees list of employees
     * @return average annual salary
     */
    public double calculateAverageSalary(List<Employee> employees) {
        // TODO: Total salary / number of employees
        return 0.0; // Replace this line
    }

    /**
     * TODO 5: Get payroll breakdown by department
     * @param employees list of employees
     * @return formatted string showing payroll per department
     */
    public String getPayrollBreakdown(List<Employee> employees) {
        // TODO: Group by department, sum salaries, format as string
        // Example:
        // "ENGINEERING: $450,000.00"
        // "MARKETING: $250,000.00"
        return ""; // Replace this line
    }
}

