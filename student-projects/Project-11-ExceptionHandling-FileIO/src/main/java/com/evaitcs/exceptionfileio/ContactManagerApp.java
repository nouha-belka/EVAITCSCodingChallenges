package com.evaitcs.exceptionfileio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * MAIN APPLICATION: ContactManagerApp
 * TOPIC: Exception Handling + File I/O in a real application
 * ============================================================================
 *
 * This app demonstrates proper exception handling patterns:
 * 1. Catching SPECIFIC exceptions (not just Exception)
 * 2. Using checked exceptions for recoverable errors
 * 3. Using unchecked exceptions for programming errors
 * 4. try-with-resources for all I/O
 * 5. Meaningful error messages for the user
 * ============================================================================
 */
public class ContactManagerApp {

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("   CONTACT MANAGER APPLICATION");
        System.out.println("============================================\n");


        // TODO 1: Create a ContactFileManager with a file path
        ContactFileManager fileManager = new ContactFileManager("data/contacts.csv");

        // TODO 2: Create a list to hold contacts in memory
        List<Contact> contacts = new ArrayList<>();

        // TODO 3: Try to load existing contacts from file
        // Use a try/catch block — the file might not exist yet!

        try {
            if (fileManager.fileExists()) {
                contacts = fileManager.readContacts();
                System.out.println("Loaded " + contacts.size() + " contacts from file.");
             } else {
                System.out.println("No existing contacts file. Starting fresh.");
            }
        } catch (IOException e) {
            System.err.println("Error loading contacts: " + e.getMessage());
            System.out.println("Starting with empty contact list.");
        }

        // =====================================================================
        // SECTION: TESTING EXCEPTION HANDLING
        // =====================================================================

        System.out.println("\n--- Testing Invalid Contact (Unchecked Exception) ---\n");

        // TODO 4: Try creating a contact with INVALID data
        // This should throw InvalidContactException (unchecked)
        //
        try {
            Contact badContact = new Contact("1", "", "Doe", "bad-email", "123");
        } catch (InvalidContactException e) {
            System.out.println("Caught invalid contact: " + e.getMessage());
            System.out.println("Invalid field: " + e.getFieldName());
        }

        System.out.println("\n--- Creating Valid Contacts ---\n");

        // TODO 5: Create valid contacts and add them to the list
        //
        try {
            contacts.add(new Contact("1", "John", "Smith", "john@email.com", "5551234567"));
            contacts.add(new Contact("2", "Jane", "Doe", "jane@email.com", "5559876543"));
            contacts.add(new Contact("3", "Bob", "Wilson", "bob@email.com", "5555551234"));
            System.out.println("Created " + contacts.size() + " contacts.");
        } catch (InvalidContactException e) {
            System.err.println("Error creating contact: " + e.getMessage());
        }

        System.out.println("\n--- Testing File I/O ---\n");

        // TODO 6: Write contacts to file
        try {
            fileManager.writeContacts(contacts);
            System.out.println("Contacts saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving contacts: " + e.getMessage());
        }

        // TODO 7: Read contacts back from file
        try {
            List<Contact> loadedContacts = fileManager.readContacts();
            System.out.println("Read back " + loadedContacts.size() + " contacts:");
            for (Contact c : loadedContacts) {
                System.out.println("  " + c);
            }
        } catch (IOException e) {
            System.err.println("Error reading contacts: " + e.getMessage());
        }

        System.out.println("\n--- Testing Contact Search (Checked Exception) ---\n");

        // TODO 8: Search for a contact that EXISTS
        // try {
        //     Contact found = fileManager.findContactById("2");
        //     System.out.println("Found: " + found);
        // } catch (ContactNotFoundException e) {
        //     System.out.println("Not found: " + e.getMessage());
        // } catch (IOException e) {
        //     System.err.println("File error: " + e.getMessage());
        // }

        // TODO 9: Search for a contact that DOESN'T EXIST
        // try {
        //     Contact notFound = fileManager.findContactById("999");
        //     System.out.println("Found: " + notFound);
        // } catch (ContactNotFoundException e) {
        //     // This is EXPECTED — we handle it gracefully
        //     System.out.println("Expected: " + e.getMessage());
        // } catch (IOException e) {
        //     System.err.println("File error: " + e.getMessage());
        // }

        System.out.println("\n--- Testing Backup ---\n");

        // TODO 10: Create a backup of the contacts file
        // try {
        //     fileManager.createBackup();
        //     System.out.println("Backup created successfully!");
        // } catch (IOException e) {
        //     System.err.println("Backup failed: " + e.getMessage());
        // }

        System.out.println("\n--- Testing Delete ---\n");

        // TODO 11: Delete a contact and verify
        // try {
        //     fileManager.deleteContact("2");
        //     System.out.println("Contact deleted.");
        //
        //     List<Contact> remaining = fileManager.readContacts();
        //     System.out.println("Remaining contacts: " + remaining.size());
        // } catch (ContactNotFoundException e) {
        //     System.out.println("Cannot delete: " + e.getMessage());
        // } catch (IOException e) {
        //     System.err.println("File error: " + e.getMessage());
        // }

        System.out.println("\n============================================");
        System.out.println("   CONTACT MANAGER DEMO COMPLETE");
        System.out.println("============================================");
    }
}

