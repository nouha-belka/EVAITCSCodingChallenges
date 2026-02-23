package com.evaitcs.capstone.dao;

import com.evaitcs.capstone.model.Employee;
import java.io.*;
import java.util.*;

/**
 * ============================================================================
 * CLASS: FileEmployeeRepository
 * TOPICS: File I/O, Collections (Map), Exception Handling, Generics
 * ============================================================================
 *
 * This class implements EmployeeRepository using FILE-based storage.
 * Employees are stored in memory using a Map and persisted to a file
 * using Java Serialization.
 *
 * DATA STRUCTURE:
 * - Map<String, Employee> for O(1) lookup by ID
 * - Serialized to file for persistence between app restarts
 *
 * INTERVIEW TIP:
 * "I used a Map internally for O(1) lookups by employee ID, and Java
 *  serialization for persistence. The repository implements a generic
 *  interface, so we could swap to JDBC without changing the service layer."
 * ============================================================================
 */
public class FileEmployeeRepository implements EmployeeRepository<Employee> {

    // TODO 1: Declare fields:
    //   - employees (Map<String, Employee>) — in-memory storage
    //   - filePath (String) — where to save/load data


    // TODO 2: Create a constructor that:
    //   - Takes a filePath parameter
    //   - Initializes the Map as a HashMap
    //   - Calls a private loadFromFile() method to load existing data


    // =========================================================================
    // IMPLEMENT REPOSITORY METHODS
    // =========================================================================

    /**
     * TODO 3: Implement save(Employee employee)
     *   - Check if ID already exists (throw exception if it does)
     *   - Add to the map
     *   - Call saveToFile() to persist
     *   - Print: "Employee saved: [id]"
     */


    /**
     * TODO 4: Implement findById(String id)
     *   - Return Optional.ofNullable(employees.get(id))
     */


    /**
     * TODO 5: Implement findAll()
     *   - Return a new ArrayList<>(employees.values())
     */


    /**
     * TODO 6: Implement update(Employee employee)
     *   - Check if ID exists (throw exception if it doesn't)
     *   - Replace in the map
     *   - Call saveToFile()
     */


    /**
     * TODO 7: Implement deleteById(String id)
     *   - Remove from map, return true if it existed
     *   - Call saveToFile()
     */


    /**
     * TODO 8: Implement existsById(String id)
     *   - Return employees.containsKey(id)
     */


    /**
     * TODO 9: Implement count()
     *   - Return employees.size()
     */


    // =========================================================================
    // FILE I/O — Persistence
    // =========================================================================

    /**
     * TODO 10: Implement private saveToFile()
     * Use ObjectOutputStream to serialize the entire Map to the file.
     * Use try-with-resources!
     *
     * private void saveToFile() {
     *     try (ObjectOutputStream oos = new ObjectOutputStream(
     *             new FileOutputStream(filePath))) {
     *         oos.writeObject(employees);
     *     } catch (IOException e) {
     *         System.err.println("Error saving data: " + e.getMessage());
     *     }
     * }
     */


    /**
     * TODO 11: Implement private loadFromFile()
     * Use ObjectInputStream to deserialize the Map from the file.
     * If the file doesn't exist, start with an empty Map.
     *
     * @SuppressWarnings("unchecked")
     * private void loadFromFile() {
     *     File file = new File(filePath);
     *     if (file.exists()) {
     *         try (ObjectInputStream ois = new ObjectInputStream(
     *                 new FileInputStream(filePath))) {
     *             employees = (Map<String, Employee>) ois.readObject();
     *         } catch (IOException | ClassNotFoundException e) {
     *             System.err.println("Error loading data: " + e.getMessage());
     *             employees = new HashMap<>();
     *         }
     *     }
     * }
     */
}

