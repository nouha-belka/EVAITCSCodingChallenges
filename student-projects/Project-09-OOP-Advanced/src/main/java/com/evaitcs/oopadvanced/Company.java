package com.evaitcs.oopadvanced;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * CLASS: Company
 * TOPIC: Composition — A class COMPOSED of other objects
 * ============================================================================
 *
 * COMPOSITION IN ACTION:
 * Company "has-a" Address         (composition — strong ownership)
 * Company "has-many" EmployeeRecords (aggregation — employees can exist independently)
 *
 * The difference:
 * - COMPOSITION: If Company is destroyed, its Address is too (strong)
 * - AGGREGATION: If Company closes, employees still exist (weak)
 *
 * Also implements Serializable for saving/loading company data.
 *
 * INTERVIEW TIP:
 * "Composition creates a 'whole-part' relationship where the part's lifecycle
 *  is managed by the whole. I use it to build complex objects from simpler ones."
 * ============================================================================
 */
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    // =========================================================================
    // TODO 1: Declare private fields:
    //   - companyName (String)
    //   - headquarters (Address)          ← COMPOSITION!
    //   - employees (List<EmployeeRecord>) ← AGGREGATION!
    //   - founded (int) — year founded
    // =========================================================================


    // =========================================================================
    // CONSTRUCTOR
    // =========================================================================

    /**
     * TODO 2: Create a constructor
     * Parameters: companyName, headquarters (Address), founded (int)
     * Initialize employees as a new empty ArrayList.
     *
     * Validation:
     * - companyName must not be null or empty
     * - headquarters must not be null
     * - founded must be a reasonable year (> 1800 and <= current year)
     */
    // YOUR CONSTRUCTOR HERE


    // =========================================================================
    // EMPLOYEE MANAGEMENT METHODS
    // =========================================================================

    /**
     * TODO 3: Add an employee to the company
     * Check if the employee's department is at capacity first!
     * Use the Department enum's isAtCapacity() method.
     *
     * @param employee the employee to add
     * @return true if added successfully, false if department is full
     */
    public boolean addEmployee(EmployeeRecord employee) {
        // TODO: Count how many employees are already in that department
        // Use a loop or stream to count employees with the same department
        // Check if department.isAtCapacity(count)
        // If at capacity, print message and return false
        // Otherwise, add employee and return true

        return false; // Replace this line
    }

    /**
     * TODO 4: Remove an employee by ID
     *
     * @param employeeId the ID of the employee to remove
     * @return true if found and removed, false if not found
     */
    public boolean removeEmployee(String employeeId) {
        // TODO: Loop through employees, find the one with matching ID, remove it
        // Hint: Use an Iterator or removeIf()
        // employees.removeIf(e -> e.employeeId().equals(employeeId));

        return false; // Replace this line
    }

    /**
     * TODO 5: Find an employee by ID
     *
     * @param employeeId the ID to search for
     * @return the EmployeeRecord if found, null if not found
     */
    public EmployeeRecord findEmployee(String employeeId) {
        // TODO: Loop through employees and return the matching one

        return null; // Replace this line
    }

    /**
     * TODO 6: Get all employees in a specific department
     *
     * @param department the department to filter by
     * @return a List of employees in that department
     */
    public List<EmployeeRecord> getEmployeesByDepartment(Department department) {
        // TODO: Create a new list, loop through employees,
        // add those whose department matches

        return new ArrayList<>(); // Replace this line
    }

    /**
     * TODO 7: Calculate the total payroll (sum of all salaries)
     *
     * @return the total monthly payroll
     */
    public double calculateTotalPayroll() {
        // TODO: Sum up all employee salaries

        return 0.0; // Replace this line
    }

    /**
     * TODO 8: Get the company's employee count
     */
    public int getEmployeeCount() {
        // TODO: Return the size of the employees list
        return 0; // Replace this line
    }

    // =========================================================================
    // GETTERS
    // =========================================================================

    /**
     * TODO 9: Create getters for companyName, headquarters, founded, and employees
     * For employees, return a COPY of the list to protect encapsulation!
     * Hint: return new ArrayList<>(employees);
     */
    // YOUR GETTERS HERE


    // =========================================================================
    // TOSTRING
    // =========================================================================

    /**
     * TODO 10: Override toString()
     * Format: "Company{name='TechCorp', headquarters=..., employees=5, founded=2020}"
     */
    @Override
    public String toString() {
        return ""; // Replace this line
    }
}

