package com.evaitcs.capstone.util;

import com.evaitcs.capstone.model.*;

/**
 * ============================================================================
 * CLASS: EmployeeFactory
 * TOPICS: Factory Pattern, OCP (Open/Closed Principle)
 * ============================================================================
 *
 * FACTORY PATTERN:
 * Centralizes employee creation. The client says "create a full-time employee"
 * and the factory handles the details.
 *
 * OCP: To add a new employee type (e.g., ContractEmployee), you add a new
 * case here — no existing code changes!
 * ============================================================================
 */
public class EmployeeFactory {

    /**
     * TODO 1: Create a static factory method for creating employees
     *
     * @param type "FULL_TIME" or "PART_TIME"
     * @param employeeId unique ID
     * @param firstName first name
     * @param lastName last name
     * @param email email address
     * @param department department enum
     * @param hireYear year hired
     * @param salary for full-time: annual salary; for part-time: hourly rate
     * @param extra for full-time: bonus percentage; for part-time: hours per week
     * @return the created Employee
     * @throws IllegalArgumentException for unknown type
     */
    public static Employee createEmployee(String type, String employeeId,
            String firstName, String lastName, String email,
            Department department, int hireYear,
            double salary, double extra) {

        // TODO: Use a switch on type.toUpperCase()
        //   "FULL_TIME" → return new FullTimeEmployee(...)
        //   "PART_TIME" → return new PartTimeEmployee(..., (int) extra)
        //   default → throw new IllegalArgumentException("Unknown employee type: " + type)

        return null; // Replace this line
    }
}

