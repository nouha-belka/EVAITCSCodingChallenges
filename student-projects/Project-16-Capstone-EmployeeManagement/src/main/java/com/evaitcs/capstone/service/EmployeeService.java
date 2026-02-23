package com.evaitcs.capstone.service;

import com.evaitcs.capstone.dao.EmployeeRepository;
import com.evaitcs.capstone.model.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * CLASS: EmployeeService
 * TOPICS: SRP, DIP, Collections, Business Logic
 * ============================================================================
 *
 * SRP: This class handles ONLY employee business logic (not file I/O, not UI).
 * DIP: It depends on the EmployeeRepository INTERFACE, not the File implementation.
 *
 * INTERVIEW TIP:
 * "I separated business logic into a service layer that depends on repository
 *  interfaces. This makes the code testable — I can mock the repository in
 *  unit tests — and follows the Single Responsibility Principle."
 * ============================================================================
 */
public class EmployeeService {

    // TODO 1: Declare a private final EmployeeRepository<Employee> field


    // TODO 2: Create a constructor that takes EmployeeRepository<Employee>
    //   (This is DEPENDENCY INJECTION — the caller provides the dependency)


    /**
     * TODO 3: Hire a new employee (add to repository)
     * @param employee the new employee
     * @throws IllegalArgumentException if employee is null or already exists
     */
    public void hireEmployee(Employee employee) {
        // TODO: Validate, then save to repository
    }

    /**
     * TODO 4: Terminate an employee (change status to TERMINATED)
     * @param employeeId the employee to terminate
     * @throws NoSuchElementException if employee not found
     */
    public void terminateEmployee(String employeeId) {
        // TODO: Find employee, change status, update in repository
    }

    /**
     * TODO 5: Get all employees in a specific department
     * Use Java Collections to filter!
     *
     * @param department the department to filter by
     * @return list of employees in that department
     */
    public List<Employee> getEmployeesByDepartment(Department department) {
        // TODO: Get all employees, filter by department
        // Hint: Use a loop or stream:
        // return repository.findAll().stream()
        //     .filter(e -> e.getDepartment() == department)
        //     .collect(Collectors.toList());

        return new ArrayList<>(); // Replace this line
    }

    /**
     * TODO 6: Get all active employees
     */
    public List<Employee> getActiveEmployees() {
        // TODO: Filter by EmployeeStatus.ACTIVE
        return new ArrayList<>(); // Replace this line
    }

    /**
     * TODO 7: Find an employee by ID
     * @throws NoSuchElementException if not found
     */
    public Employee getEmployeeById(String id) {
        // TODO: Use repository.findById() and handle Optional
        return null; // Replace this line
    }

    /**
     * TODO 8: Get the total employee count
     */
    public long getEmployeeCount() {
        return 0; // Replace this line
    }

    /**
     * TODO 9: Get a summary map: Department → count of employees
     * @return Map<Department, Long> showing how many employees per department
     */
    public Map<Department, Long> getDepartmentHeadcounts() {
        // TODO: Group and count employees by department
        return new HashMap<>(); // Replace this line
    }
}

