package com.evaitcs.oopadvanced;

/**
 * ============================================================================
 * ENUM: Department
 * TOPIC: Enums — Type-safe constants with behavior
 * ============================================================================
 *
 * WHAT ARE ENUMS?
 * Enums represent a FIXED set of constants. Instead of using magic strings
 * like "Engineering" or integers like 1, 2, 3 — enums give you type safety.
 *
 * Before enums: String dept = "Engneering";  // Typo! No compiler error!
 * With enums:   Department dept = Department.ENGNEERING;  // Compiler error!
 *
 * ENUMS CAN HAVE:
 * - Fields (like regular classes)
 * - Constructors (must be private)
 * - Methods (both regular and abstract)
 *
 * INTERVIEW TIP:
 * "Enums provide type safety, prevent invalid values, and can contain
 *  behavior. They're perfect for representing fixed sets of options like
 *  status codes, departments, or directions."
 * ============================================================================
 */
public enum Department {

    // =========================================================================
    // TODO 1: Define enum constants with fields
    // =========================================================================
    // Each constant should have: name (String), code (String), maxCapacity (int)
    //
    // Define these departments:
    //   ENGINEERING("Engineering", "ENG", 50)
    //   MARKETING("Marketing", "MKT", 30)
    //   HUMAN_RESOURCES("Human Resources", "HR", 20)
    //   FINANCE("Finance", "FIN", 25)
    //   SALES("Sales", "SLS", 40)
    //   IT_SUPPORT("IT Support", "ITS", 15)
    //
    // Syntax:
    //   CONSTANT_NAME("value1", "value2", intValue),
    //   NEXT_CONSTANT("value1", "value2", intValue);  // Last one ends with semicolon!


    ; // Remove this semicolon once you add your constants above

    // =========================================================================
    // FIELDS
    // =========================================================================

    // TODO 2: Declare private final fields for:
    //   - displayName (String)
    //   - code (String)
    //   - maxCapacity (int)
    //
    // Fields in enums should be FINAL because enum constants are immutable.


    // =========================================================================
    // CONSTRUCTOR
    // =========================================================================

    /**
     * TODO 3: Create a private constructor
     * Enum constructors are ALWAYS private (even if you don't write 'private').
     * Initialize all fields from the parameters.
     */
    // YOUR CONSTRUCTOR HERE


    // =========================================================================
    // GETTERS
    // =========================================================================

    /**
     * TODO 4: Create getters for displayName, code, and maxCapacity
     */
    // YOUR GETTERS HERE


    // =========================================================================
    // CUSTOM METHODS
    // =========================================================================

    /**
     * TODO 5: Create a method to check if a department is at capacity
     *
     * @param currentCount the current number of employees
     * @return true if currentCount >= maxCapacity
     */
    public boolean isAtCapacity(int currentCount) {
        // TODO: Implement this

        return false; // Replace this line
    }

    /**
     * TODO 6: Create a static method to find a Department by its code
     * Loop through all Department.values() and find the one matching the code.
     *
     * @param code the department code (e.g., "ENG")
     * @return the matching Department, or null if not found
     */
    public static Department findByCode(String code) {
        // TODO: Use a for-each loop: for (Department dept : Department.values())
        // Check if dept.getCode().equalsIgnoreCase(code)

        return null; // Replace this line
    }

    /**
     * TODO 7: Override toString() to return a formatted string
     * Format: "Department{name='Engineering', code='ENG', capacity=50}"
     */
    @Override
    public String toString() {
        return ""; // Replace this line
    }
}

