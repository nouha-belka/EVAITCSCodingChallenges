package com.evaitcs.capstone;

import com.evaitcs.capstone.dao.*;
import com.evaitcs.capstone.model.*;
import com.evaitcs.capstone.service.*;
import com.evaitcs.capstone.util.*;

import java.util.List;
import java.util.Map;

/**
 * ============================================================================
 * CAPSTONE MAIN APPLICATION: Employee Management System
 * ============================================================================
 *
 * This is the culmination of EVERYTHING you've learned:
 *
 * ┌─────────────────────────────────────────────────────────────┐
 * │                    ARCHITECTURE LAYERS                       │
 * ├─────────────────────────────────────────────────────────────┤
 * │  Main App (this file)                                       │
 * │    ↓ uses                                                   │
 * │  Service Layer (EmployeeService, PayrollService, Report)    │
 * │    ↓ depends on (interface)                                 │
 * │  DAO Layer (EmployeeRepository → FileEmployeeRepository)    │
 * │    ↓ uses                                                   │
 * │  Model Layer (Employee, FullTimeEmployee, PartTimeEmployee) │
 * │    ↓ uses                                                   │
 * │  Util Layer (Factory, Singleton Config, Validation)         │
 * └─────────────────────────────────────────────────────────────┘
 *
 * DESIGN PATTERNS USED:
 * ✅ Singleton    — AppConfig (one configuration instance)
 * ✅ Factory      — EmployeeFactory (create employees without new)
 * ✅ Repository   — FileEmployeeRepository (data access abstraction)
 *
 * SOLID PRINCIPLES APPLIED:
 * ✅ SRP — Each class has ONE responsibility
 * ✅ OCP — Add new employee types without changing existing code
 * ✅ LSP — FullTime and PartTime are substitutable for Employee
 * ✅ ISP — Small, focused interfaces (EmployeeRepository)
 * ✅ DIP — Services depend on interfaces, not implementations
 *
 * INTERVIEW PREP:
 * Be ready to explain:
 * 1. Why you chose this architecture
 * 2. How you applied each SOLID principle
 * 3. Which design patterns you used and WHY
 * 4. How you would add a new feature (e.g., ContractEmployee)
 * 5. How you would switch from file storage to a database
 * ============================================================================
 */
public class EmployeeManagementApp {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║     EMPLOYEE MANAGEMENT SYSTEM              ║");
        System.out.println("║     EVAITCS Corporation                     ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // =====================================================================
        // STEP 1: INITIALIZE (Singleton + Dependency Injection)
        // =====================================================================

        System.out.println("--- Initializing System ---\n");

        // TODO 1: Get AppConfig singleton
        // AppConfig config = AppConfig.getInstance();
        // System.out.println("Company: " + config.getCompanyName());

        // TODO 2: Create repository (DI — inject into service)
        // EmployeeRepository<Employee> repository = new FileEmployeeRepository(config.getDataFilePath());

        // TODO 3: Create services (inject repository)
        // EmployeeService employeeService = new EmployeeService(repository);
        // PayrollService payrollService = new PayrollService();
        // ReportService reportService = new ReportService();

        // =====================================================================
        // STEP 2: CREATE EMPLOYEES (Factory Pattern)
        // =====================================================================

        System.out.println("--- Hiring Employees ---\n");

        // TODO 4: Use EmployeeFactory to create employees
        // Employee emp1 = EmployeeFactory.createEmployee("FULL_TIME",
        //     "E001", "Alice", "Johnson", "alice@evaitcs.com",
        //     Department.ENGINEERING, 2022, 95000.0, 10.0);
        //
        // Employee emp2 = EmployeeFactory.createEmployee("FULL_TIME",
        //     "E002", "Bob", "Williams", "bob@evaitcs.com",
        //     Department.MARKETING, 2021, 85000.0, 8.0);
        //
        // Employee emp3 = EmployeeFactory.createEmployee("PART_TIME",
        //     "E003", "Carol", "Davis", "carol@evaitcs.com",
        //     Department.ENGINEERING, 2023, 45.0, 20);
        //
        // Employee emp4 = EmployeeFactory.createEmployee("FULL_TIME",
        //     "E004", "David", "Brown", "david@evaitcs.com",
        //     Department.FINANCE, 2020, 105000.0, 15.0);
        //
        // Employee emp5 = EmployeeFactory.createEmployee("PART_TIME",
        //     "E005", "Eve", "Wilson", "eve@evaitcs.com",
        //     Department.SALES, 2024, 30.0, 25);

        // TODO 5: Hire employees through the service
        // employeeService.hireEmployee(emp1);
        // employeeService.hireEmployee(emp2);
        // employeeService.hireEmployee(emp3);
        // employeeService.hireEmployee(emp4);
        // employeeService.hireEmployee(emp5);

        // System.out.println("Total employees: " + employeeService.getEmployeeCount());

        // =====================================================================
        // STEP 3: QUERY EMPLOYEES (Collections + Polymorphism)
        // =====================================================================

        System.out.println("\n--- Employee Queries ---\n");

        // TODO 6: Get all employees
        // List<Employee> allEmployees = employeeService.getActiveEmployees();
        // System.out.println("Active employees:");
        // for (Employee e : allEmployees) {
        //     System.out.println("  " + e);
        // }

        // TODO 7: Get employees by department
        // List<Employee> engineers = employeeService.getEmployeesByDepartment(Department.ENGINEERING);
        // System.out.println("\nEngineers: " + engineers.size());

        // TODO 8: Get department headcounts
        // Map<Department, Long> headcounts = employeeService.getDepartmentHeadcounts();
        // System.out.println("\nDepartment headcounts:");
        // headcounts.forEach((dept, count) ->
        //     System.out.println("  " + dept + ": " + count));

        // =====================================================================
        // STEP 4: PAYROLL (Polymorphism in action!)
        // =====================================================================

        System.out.println("\n--- Payroll ---\n");

        // TODO 9: Calculate payroll — NOTICE: polymorphism handles
        // full-time and part-time salary calculations automatically!
        //
        // System.out.println("Monthly payroll: $" +
        //     String.format("%.2f", payrollService.calculateTotalMonthlyPayroll(allEmployees)));
        // System.out.println("Annual payroll: $" +
        //     String.format("%.2f", payrollService.calculateTotalAnnualPayroll(allEmployees)));
        // System.out.println("Average salary: $" +
        //     String.format("%.2f", payrollService.calculateAverageSalary(allEmployees)));
        //
        // Employee topEarner = payrollService.getHighestPaidEmployee(allEmployees);
        // if (topEarner != null) {
        //     System.out.println("Highest paid: " + topEarner.getFullName() +
        //         " ($" + String.format("%.2f", topEarner.calculateAnnualSalary()) + ")");
        // }

        // =====================================================================
        // STEP 5: GENERATE REPORT (File I/O)
        // =====================================================================

        System.out.println("\n--- Reports ---\n");

        // TODO 10: Generate and save a report
        // String report = reportService.generateReport(allEmployees, payrollService);
        // System.out.println(report);
        // reportService.saveReportToFile(report, "data/employee_report.txt");

        // =====================================================================
        // STEP 6: TERMINATE AN EMPLOYEE
        // =====================================================================

        System.out.println("\n--- Employee Termination ---\n");

        // TODO 11: Terminate an employee and verify
        // employeeService.terminateEmployee("E003");
        // System.out.println("Active after termination: " +
        //     employeeService.getActiveEmployees().size());

        // =====================================================================
        // STEP 7: ERROR HANDLING DEMO
        // =====================================================================

        System.out.println("\n--- Error Handling ---\n");

        // TODO 12: Try to hire a duplicate employee (should throw exception)
        // try {
        //     employeeService.hireEmployee(emp1); // Already exists!
        // } catch (IllegalArgumentException e) {
        //     System.out.println("Expected error: " + e.getMessage());
        // }

        // TODO 13: Try to find a non-existent employee
        // try {
        //     employeeService.getEmployeeById("E999");
        // } catch (Exception e) {
        //     System.out.println("Expected error: " + e.getMessage());
        // }

        // TODO 14: Try to create an employee with invalid data
        // try {
        //     EmployeeFactory.createEmployee("FULL_TIME",
        //         "E099", "", "bad", "no-email",
        //         Department.ENGINEERING, 2025, -50000, 10);
        // } catch (IllegalArgumentException e) {
        //     System.out.println("Validation works: " + e.getMessage());
        // }

        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║     SYSTEM DEMO COMPLETE                    ║");
        System.out.println("║     All concepts demonstrated!              ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}

