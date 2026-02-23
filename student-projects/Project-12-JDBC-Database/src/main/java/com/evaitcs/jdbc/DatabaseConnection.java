package com.evaitcs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ============================================================================
 * CLASS: DatabaseConnection (SINGLETON PATTERN)
 * TOPIC: JDBC Connection + Design Pattern
 * ============================================================================
 *
 * SINGLETON PATTERN:
 * Only ONE instance of this class ever exists. This prevents creating
 * too many database connections (which is expensive and can crash the DB).
 *
 * Think of it like having ONE key to the database that everyone shares,
 * instead of everyone making their own key.
 *
 * THREAD SAFETY:
 * The double-checked locking pattern ensures only one thread can create
 * the instance, even in a multi-threaded application.
 *
 * INTERVIEW TIP:
 * "I use the Singleton pattern for database connections to ensure we don't
 *  create excessive connections. In production, I'd use a connection pool
 *  like HikariCP, but understanding the Singleton pattern is fundamental."
 * ============================================================================
 */
public class DatabaseConnection {

    // TODO 1: Declare a private static volatile instance variable
    // private static volatile DatabaseConnection instance;
    //
    // 'volatile' ensures all threads see the latest value (thread safety)


    // TODO 2: Declare a private Connection field
    // private Connection connection;


    // TODO 3: Declare database configuration constants
    // Update these to match YOUR database setup!
    //
    // private static final String URL = "jdbc:mysql://localhost:3306/evaitcs_users";
    // private static final String USER = "root";
    // private static final String PASSWORD = "your_password_here";


    /**
     * TODO 4: Create a PRIVATE constructor
     * - Private prevents other classes from creating instances
     * - Load the JDBC driver class
     * - Establish the connection
     *
     * private DatabaseConnection() {
     *     try {
     *         Class.forName("com.mysql.cj.jdbc.Driver");
     *         this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
     *         System.out.println("Database connection established.");
     *     } catch (ClassNotFoundException e) {
     *         throw new RuntimeException("MySQL JDBC Driver not found!", e);
     *     } catch (SQLException e) {
     *         throw new RuntimeException("Failed to connect to database!", e);
     *     }
     * }
     */
    // YOUR PRIVATE CONSTRUCTOR HERE


    /**
     * TODO 5: Create the getInstance() method with double-checked locking
     * This is the ONLY way to get the DatabaseConnection instance.
     *
     * Double-checked locking:
     * 1. First check: if instance is null (fast, no synchronization)
     * 2. Synchronize: only one thread enters this block
     * 3. Second check: another thread might have created it while we waited
     *
     * public static DatabaseConnection getInstance() {
     *     if (instance == null) {
     *         synchronized (DatabaseConnection.class) {
     *             if (instance == null) {
     *                 instance = new DatabaseConnection();
     *             }
     *         }
     *     }
     *     return instance;
     * }
     */
    // YOUR getInstance() METHOD HERE


    /**
     * TODO 6: Create a getConnection() method that returns the Connection
     * Also check if the connection is closed and reconnect if needed.
     *
     * public Connection getConnection() {
     *     try {
     *         if (connection == null || connection.isClosed()) {
     *             connection = DriverManager.getConnection(URL, USER, PASSWORD);
     *         }
     *     } catch (SQLException e) {
     *         throw new RuntimeException("Failed to get connection", e);
     *     }
     *     return connection;
     * }
     */
    // YOUR getConnection() METHOD HERE


    /**
     * TODO 7: Create a closeConnection() method
     * Clean up when the application shuts down.
     */
    // YOUR closeConnection() METHOD HERE
}

