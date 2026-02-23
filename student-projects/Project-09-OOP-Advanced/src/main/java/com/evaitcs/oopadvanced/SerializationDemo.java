package com.evaitcs.oopadvanced;

import java.io.*;

/**
 * ============================================================================
 * CLASS: SerializationDemo
 * TOPIC: Serialization — Saving objects to files and loading them back
 * ============================================================================
 *
 * WHAT IS SERIALIZATION?
 * Converting an object into a byte stream so it can be:
 *   - Saved to a file (persistence)
 *   - Sent over a network
 *   - Stored in a cache
 *
 * DESERIALIZATION is the reverse: reading bytes and reconstructing the object.
 *
 * KEY CONCEPTS:
 * - Serializable interface: marks a class as serializable (marker interface)
 * - serialVersionUID: version control for serialized classes
 * - transient keyword: marks fields that should NOT be serialized (e.g., passwords)
 *
 * INTERVIEW TIP:
 * "Serialization converts objects to bytes for storage or transmission.
 *  The serialVersionUID ensures compatibility between serialized data and
 *  the class definition. I use transient for sensitive data like passwords."
 * ============================================================================
 */
public class SerializationDemo {

    // =========================================================================
    // TODO 1: Create a method to serialize (save) a Company object to a file
    // =========================================================================

    /**
     * Save a Company object to a file using ObjectOutputStream.
     *
     * Steps:
     * 1. Create a FileOutputStream with the given filename
     * 2. Wrap it in an ObjectOutputStream
     * 3. Call writeObject() to serialize the company
     * 4. Use try-with-resources for automatic resource cleanup!
     *
     * @param company  the Company object to save
     * @param filename the file to save to (e.g., "company.dat")
     */
    public static void saveCompany(Company company, String filename) {
        // TODO: Implement serialization
        // try (ObjectOutputStream oos = new ObjectOutputStream(
        //         new FileOutputStream(filename))) {
        //     oos.writeObject(company);
        //     System.out.println("Company saved to " + filename);
        // } catch (IOException e) {
        //     System.err.println("Error saving company: " + e.getMessage());
        // }
    }

    // =========================================================================
    // TODO 2: Create a method to deserialize (load) a Company object from a file
    // =========================================================================

    /**
     * Load a Company object from a file using ObjectInputStream.
     *
     * Steps:
     * 1. Create a FileInputStream with the given filename
     * 2. Wrap it in an ObjectInputStream
     * 3. Call readObject() and cast it to Company
     * 4. Handle ClassNotFoundException (in case the class has changed)
     *
     * @param filename the file to load from
     * @return the deserialized Company object, or null if an error occurs
     */
    public static Company loadCompany(String filename) {
        // TODO: Implement deserialization
        // try (ObjectInputStream ois = new ObjectInputStream(
        //         new FileInputStream(filename))) {
        //     Company company = (Company) ois.readObject();
        //     System.out.println("Company loaded from " + filename);
        //     return company;
        // } catch (IOException | ClassNotFoundException e) {
        //     System.err.println("Error loading company: " + e.getMessage());
        //     return null;
        // }

        return null; // Replace this line
    }

    // =========================================================================
    // TODO 3: Create a method to check if a serialized file exists
    // =========================================================================

    /**
     * Check if the given file exists.
     *
     * @param filename the file to check
     * @return true if the file exists
     */
    public static boolean fileExists(String filename) {
        // TODO: Use new File(filename).exists()
        return false; // Replace this line
    }

    // =========================================================================
    // MAIN METHOD — Demo
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=== Serialization Demo ===\n");

        // TODO 4: Create a Company with some employees
        // Address hq = new Address("100 Tech Blvd", "San Jose", "CA", "95101", "USA");
        // Company company = new Company("TechCorp", hq, 2020);
        //
        // Add a few employees:
        // company.addEmployee(new EmployeeRecord("E001", "Alice", "Johnson",
        //     Department.ENGINEERING, 9500.0, "alice@techcorp.com"));
        // company.addEmployee(new EmployeeRecord("E002", "Bob", "Williams",
        //     Department.MARKETING, 7500.0, "bob@techcorp.com"));


        // TODO 5: Save the company to a file
        // String filename = "company_data.dat";
        // saveCompany(company, filename);


        // TODO 6: Load the company back from the file
        // Company loadedCompany = loadCompany(filename);
        // if (loadedCompany != null) {
        //     System.out.println("Loaded: " + loadedCompany);
        //     System.out.println("Employees: " + loadedCompany.getEmployeeCount());
        // }


        // TODO 7: Verify the data is the same
        // Print both company and loadedCompany to confirm they match!
    }
}

