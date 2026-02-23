package com.evaitcs.exceptionfileio;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * CLASS: ContactFileManager
 * TOPIC: File I/O with proper exception handling
 * ============================================================================
 *
 * This class handles reading and writing contacts to/from CSV files.
 * It demonstrates:
 * - try-with-resources (automatic resource cleanup)
 * - BufferedReader/BufferedWriter (efficient I/O)
 * - Java NIO (modern file operations)
 * - Proper exception handling patterns
 *
 * BEST PRACTICES:
 * 1. ALWAYS use try-with-resources for file operations
 * 2. Use BufferedReader/Writer for performance
 * 3. Handle specific exceptions, not just Exception
 * 4. Provide meaningful error messages
 * 5. Clean up resources in finally blocks (or use try-with-resources)
 *
 * INTERVIEW TIP:
 * "I always use try-with-resources for file operations to ensure streams
 *  are properly closed even if an exception occurs. I also use buffered
 *  streams for better performance."
 * ============================================================================
 */
public class ContactFileManager {

    private final String filePath;

    /**
     * TODO 1: Create a constructor that takes a file path
     * Also create the directory if it doesn't exist!
     *
     * @param filePath path to the contacts CSV file
     */
    public ContactFileManager(String filePath) {
        this.filePath = filePath;
        // TODO: Create parent directories if they don't exist
        // Hint: new File(filePath).getParentFile().mkdirs();
        // But handle the case where there's no parent directory
    }

    // =========================================================================
    // WRITE OPERATIONS
    // =========================================================================

    /**
     * TODO 2: Write a list of contacts to a CSV file (OVERWRITES existing file)
     * Use try-with-resources with BufferedWriter!
     *
     * Steps:
     * 1. Open a BufferedWriter wrapped around a FileWriter
     * 2. Write a header line: "id,firstName,lastName,email,phoneNumber"
     * 3. Loop through contacts and write each one using toCsvString()
     * 4. Handle IOException
     *
     * @param contacts the list of contacts to write
     * @throws IOException if file writing fails
     */
    public void writeContacts(List<Contact> contacts) throws IOException {
        // TODO: Implement using try-with-resources
        // try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        //     writer.write("id,firstName,lastName,email,phoneNumber");
        //     writer.newLine();
        //     for (Contact contact : contacts) {
        //         writer.write(contact.toCsvString());
        //         writer.newLine();
        //     }
        //     System.out.println("Wrote " + contacts.size() + " contacts to " + filePath);
        // }
        // The try-with-resources automatically closes the writer!
    }

    /**
     * TODO 3: Append a single contact to the file
     * Use FileWriter with append=true!
     *
     * @param contact the contact to append
     * @throws IOException if file writing fails
     */
    public void appendContact(Contact contact) throws IOException {
        // TODO: Check if file exists; if not, write header first
        // Use: new FileWriter(filePath, true) for append mode
        // try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
        //     ...
        // }
    }

    // =========================================================================
    // READ OPERATIONS
    // =========================================================================

    /**
     * TODO 4: Read all contacts from the CSV file
     * Use try-with-resources with BufferedReader!
     *
     * Steps:
     * 1. Open a BufferedReader wrapped around a FileReader
     * 2. Read and skip the header line
     * 3. Read each subsequent line and convert to Contact using fromCsvString()
     * 4. Handle IOException and InvalidContactException (for bad data lines)
     * 5. If a line has bad data, SKIP it and continue (don't crash!)
     *
     * @return list of contacts read from the file
     * @throws IOException if file reading fails
     */
    public List<Contact> readContacts() throws IOException {
        List<Contact> contacts = new ArrayList<>();

        // TODO: Implement reading with try-with-resources
        // try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        //     String header = reader.readLine(); // Skip header
        //     String line;
        //     int lineNumber = 1;
        //     while ((line = reader.readLine()) != null) {
        //         lineNumber++;
        //         try {
        //             contacts.add(Contact.fromCsvString(line));
        //         } catch (InvalidContactException e) {
        //             // Skip bad lines but log the error!
        //             System.err.println("Skipping invalid data at line " + lineNumber + ": " + e.getMessage());
        //         }
        //     }
        // }

        return contacts;
    }

    /**
     * TODO 5: Find a contact by ID in the file
     * Uses readContacts() internally, then searches the list.
     *
     * @param contactId the ID to search for
     * @return the found Contact
     * @throws ContactNotFoundException if no contact with that ID exists (CHECKED!)
     * @throws IOException if file reading fails
     */
    public Contact findContactById(String contactId) throws ContactNotFoundException, IOException {
        // TODO: Read all contacts, search for matching ID
        // If not found: throw new ContactNotFoundException("Contact with ID '" + contactId + "' not found");

        return null; // Replace this line
    }

    /**
     * TODO 6: Delete a contact by ID
     * Read all contacts, remove the one with matching ID, write back.
     *
     * @param contactId the ID to delete
     * @throws ContactNotFoundException if contact not found
     * @throws IOException if file operations fail
     */
    public void deleteContact(String contactId) throws ContactNotFoundException, IOException {
        // TODO: Read contacts, find and remove the matching one
        // If not found, throw ContactNotFoundException
        // If found, write the updated list back to the file
    }

    // =========================================================================
    // JAVA NIO — Modern File Operations
    // =========================================================================

    /**
     * TODO 7: Read entire file content as a String using Java NIO
     * This is a simpler approach for small files.
     *
     * @return the file content as a string
     * @throws IOException if reading fails
     */
    public String readFileContent() throws IOException {
        // TODO: Use Files.readString(Path.of(filePath))
        // This is the modern Java NIO way — much simpler!

        return ""; // Replace this line
    }

    /**
     * TODO 8: Check if the contacts file exists
     *
     * @return true if the file exists
     */
    public boolean fileExists() {
        // TODO: Use Files.exists(Path.of(filePath))
        return false; // Replace this line
    }

    /**
     * TODO 9: Create a backup of the contacts file
     * Copy the file to a backup location (e.g., filename.bak)
     *
     * @throws IOException if copy fails
     */
    public void createBackup() throws IOException {
        // TODO: Use Files.copy() from Java NIO
        // Path source = Path.of(filePath);
        // Path backup = Path.of(filePath + ".bak");
        // Files.copy(source, backup, StandardCopyOption.REPLACE_EXISTING);
    }
}

