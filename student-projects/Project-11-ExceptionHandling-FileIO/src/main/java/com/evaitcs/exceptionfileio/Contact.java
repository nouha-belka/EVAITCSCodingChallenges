package com.evaitcs.exceptionfileio;

/**
 * ============================================================================
 * CLASS: Contact
 * TOPIC: Model class with validation using custom exceptions
 * ============================================================================
 */
public class Contact {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    /**
     * TODO 1: Create a constructor with validation
     * Throw InvalidContactException (unchecked) for invalid data:
     * - firstName: must not be null/empty → throw new InvalidContactException("firstName", "First name is required")
     * - lastName: must not be null/empty
     * - email: must contain '@' if not null
     * - phoneNumber: must be 10 digits if not null
     */
    // YOUR CONSTRUCTOR HERE


    // TODO 2: Create getters for all fields

    // TODO 3: Create setters with the SAME validation as the constructor

    /**
     * TODO 4: Create a method to convert Contact to a CSV-formatted string
     * Format: "id,firstName,lastName,email,phoneNumber"
     * This will be used when WRITING to files.
     *
     * @return CSV string representation
     */
    public String toCsvString() {
        // TODO: Return comma-separated values
        return ""; // Replace this line
    }

    /**
     * TODO 5: Create a static factory method to create a Contact FROM a CSV string
     * This will be used when READING from files.
     *
     * @param csvLine a comma-separated string like "1,John,Doe,john@email.com,5551234567"
     * @return a new Contact object
     * @throws InvalidContactException if the CSV format is wrong
     */
    public static Contact fromCsvString(String csvLine) {
        // TODO: Split the string by comma
        // Validate that there are exactly 5 parts
        // Create and return a new Contact
        // If format is wrong, throw InvalidContactException

        return null; // Replace this line
    }

    @Override
    public String toString() {
        return String.format("Contact{id='%s', name='%s %s', email='%s', phone='%s'}",
                id, firstName, lastName, email, phoneNumber);
    }
}

