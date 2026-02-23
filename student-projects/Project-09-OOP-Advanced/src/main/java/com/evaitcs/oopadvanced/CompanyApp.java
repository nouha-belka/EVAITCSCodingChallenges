package com.evaitcs.oopadvanced;

/**
 * ============================================================================
 * MAIN APPLICATION: CompanyApp
 * TOPIC: Putting it all together — Composition, Enums, Records, Serialization
 * ============================================================================
 *
 * This is where you test ALL the advanced OOP concepts together.
 * Follow the TODOs to build and manage a company!
 * ============================================================================
 */
public class CompanyApp {

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("   COMPANY ORGANIZATION SYSTEM");
        System.out.println("============================================\n");

        // =====================================================================
        // STEP 1: TEST ENUMS
        // =====================================================================

        System.out.println("--- Testing Enums ---\n");

        // TODO 1: Print all departments using Department.values()
        // for (Department dept : Department.values()) {
        //     System.out.println(dept);
        // }

        // TODO 2: Find a department by code
        // Department eng = Department.findByCode("ENG");
        // System.out.println("Found: " + eng);

        // TODO 3: Check if engineering is at capacity with 45 employees
        // System.out.println("At capacity? " + eng.isAtCapacity(45));

        // TODO 4: Demonstrate enum comparison (use == not .equals())
        // Department d1 = Department.ENGINEERING;
        // Department d2 = Department.ENGINEERING;
        // System.out.println("Same? " + (d1 == d2));  // true!

        // =====================================================================
        // STEP 2: TEST RECORDS
        // =====================================================================

        System.out.println("\n--- Testing Records ---\n");

        // TODO 5: Create EmployeeRecord instances
        // Note: Records use accessor methods named after fields (no "get" prefix!)
        // EmployeeRecord emp1 = new EmployeeRecord("E001", "Alice", "Johnson",
        //     Department.ENGINEERING, 9500.0, "alice@corp.com");
        //
        // System.out.println("Name: " + emp1.fullName());       // Alice Johnson
        // System.out.println("Salary: " + emp1.salary());       // 9500.0 (not getSalary!)
        // System.out.println("Annual: " + emp1.getAnnualSalary()); // 114000.0
        // System.out.println("High earner? " + emp1.isHighEarner()); // true

        // TODO 6: Test record equality (auto-generated equals!)
        // EmployeeRecord emp1Copy = new EmployeeRecord("E001", "Alice", "Johnson",
        //     Department.ENGINEERING, 9500.0, "alice@corp.com");
        // System.out.println("Equal? " + emp1.equals(emp1Copy)); // true!

        // TODO 7: Test record toString (auto-generated!)
        // System.out.println(emp1);  // Prints all fields automatically

        // =====================================================================
        // STEP 3: TEST COMPOSITION
        // =====================================================================

        System.out.println("\n--- Testing Composition ---\n");

        // TODO 8: Create an Address (component)
        // Address hq = new Address("100 Innovation Way", "Austin", "TX", "73301", "USA");

        // TODO 9: Create a Company (composed of Address)
        // Company company = new Company("InnovateTech", hq, 2022);

        // TODO 10: Add employees to the company
        // company.addEmployee(new EmployeeRecord("E001", "Alice", "Johnson",
        //     Department.ENGINEERING, 9500.0, "alice@corp.com"));
        // company.addEmployee(new EmployeeRecord("E002", "Bob", "Williams",
        //     Department.MARKETING, 7500.0, "bob@corp.com"));
        // company.addEmployee(new EmployeeRecord("E003", "Charlie", "Brown",
        //     Department.ENGINEERING, 8500.0, "charlie@corp.com"));
        // company.addEmployee(new EmployeeRecord("E004", "Diana", "Ross",
        //     Department.FINANCE, 8000.0, "diana@corp.com"));

        // TODO 11: Test company operations
        // System.out.println("Total employees: " + company.getEmployeeCount());
        // System.out.println("Total payroll: $" + company.calculateTotalPayroll());

        // TODO 12: Find employees by department
        // List<EmployeeRecord> engineers = company.getEmployeesByDepartment(Department.ENGINEERING);
        // System.out.println("Engineers: " + engineers.size());

        // TODO 13: Find an employee by ID
        // EmployeeRecord found = company.findEmployee("E002");
        // System.out.println("Found: " + (found != null ? found.fullName() : "Not found"));

        // =====================================================================
        // STEP 4: TEST SERIALIZATION
        // =====================================================================

        System.out.println("\n--- Testing Serialization ---\n");

        // TODO 14: Save the company to a file
        // SerializationDemo.saveCompany(company, "company.dat");

        // TODO 15: Load it back and verify
        // Company loaded = SerializationDemo.loadCompany("company.dat");
        // if (loaded != null) {
        //     System.out.println("Loaded company: " + loaded.getCompanyName());
        //     System.out.println("Loaded employees: " + loaded.getEmployeeCount());
        // }

        System.out.println("\n============================================");
        System.out.println("   COMPANY SYSTEM DEMO COMPLETE");
        System.out.println("============================================");
    }
}

