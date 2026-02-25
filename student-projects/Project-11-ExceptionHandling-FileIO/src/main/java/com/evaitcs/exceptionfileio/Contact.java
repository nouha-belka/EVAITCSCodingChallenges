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

    private final String regex = "^(\\(\\d{3}\\)|\\d{3})[ -]?\\d{3}[ -]?\\d{4}$";
    /**
     * TODO 1: Create a constructor with validation
     * Throw InvalidContactException (unchecked) for invalid data:
     * - firstName: must not be null/empty → throw new InvalidContactException("firstName", "First name is required")
     * - lastName: must not be null/empty
     * - email: must contain '@' if not null
     * - phoneNumber: must be 10 digits if not null
     */
    // YOUR CONSTRUCTOR HERE

    public Contact(String id, String firstName, String lastName, String email, String phoneNumber){
        if(firstName == null || firstName.isEmpty()) throw new InvalidContactException("firstName", "First name is required");
        if(lastName == null || lastName.isEmpty()) throw new InvalidContactException("lastName", "Last name is required");
        if(email == null || !email.contains("@")) throw new InvalidContactException("email", "Invalid email");
        if(phoneNumber == null || !phoneNumber.matches(regex)) throw new InvalidContactException("phoneNumber", "Invalid phone number");
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    // TODO 2: Create getters for all fields

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    
    public String getLastName() {
        return lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    // TODO 3: Create setters with the SAME validation as the constructor
    public void setFirstName(String firstName) {
        if(firstName == null || firstName.isEmpty()) throw new InvalidContactException("firstName", "First name is required");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if(lastName == null || lastName.isEmpty()) throw new InvalidContactException("lastName", "Last name is required");
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        if(email == null || email.contains("@")) throw new InvalidContactException("email", "Invalid email");
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber == null || phoneNumber.matches(regex)) throw new InvalidContactException("phoneNumber", "Invalid phone number");
        this.phoneNumber = phoneNumber;
    }

    /**
     * TODO 4: Create a method to convert Contact to a CSV-formatted string
     * Format: "id,firstName,lastName,email,phoneNumber"
     * This will be used when WRITING to files.
     *
     * @return CSV string representation
     */
    public String toCsvString() {
        // TODO: Return comma-separated values
        return id + "," + firstName + "," + lastName + "," + email + "," + phoneNumber ; // Replace this line
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
        String[] parts = csvLine.split(",");
        // Validate that there are exactly 5 parts
         // If format is wrong, throw InvalidContactException
        if (parts.length != 5) {
            throw new InvalidContactException(
                "csvLine",
                "Invalid CSV format. Expected 5 fields but got " + parts.length
            );

        }
        // Create and return a new Contact
        String id = parts[0];
        String firstName = parts[1];
        String lastName = parts[2];
        String email = parts[3];
        String phoneNumber = parts[4];

        return new Contact(id, firstName, lastName, email, phoneNumber);
       
    }

    @Override
    public String toString() {
        return String.format("Contact{id='%s', name='%s %s', email='%s', phone='%s'}",
                id, firstName, lastName, email, phoneNumber);
    }

}

