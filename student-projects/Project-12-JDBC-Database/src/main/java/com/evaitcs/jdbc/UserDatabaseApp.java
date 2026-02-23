package com.evaitcs.jdbc;

import java.util.List;

/**
 * ============================================================================
 * MAIN APPLICATION: UserDatabaseApp
 * TOPIC: JDBC — Full CRUD demo with database
 * ============================================================================
 *
 * BEFORE RUNNING:
 * 1. Make sure MySQL/PostgreSQL is running
 * 2. Run the setup.sql script to create the database and table
 * 3. Update DatabaseConnection.java with your credentials
 * 4. Add the JDBC driver JAR to your classpath
 * ============================================================================
 */
public class UserDatabaseApp {

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("   USER DATABASE MANAGEMENT SYSTEM");
        System.out.println("============================================\n");

        // TODO 1: Create a UserDAO instance
        // UserDAO userDAO = new UserDAO();

        // =====================================================================
        // CREATE — Adding users to the database
        // =====================================================================

        System.out.println("--- CREATE: Adding Users ---\n");

        // TODO 2: Create and insert new users
        // User user1 = new User("alice_dev", "alice@dev.com", "Alice", "Johnson", 28);
        // User user2 = new User("bob_eng", "bob@eng.com", "Bob", "Williams", 32);
        // User user3 = new User("carol_qa", "carol@qa.com", "Carol", "Davis", 26);
        //
        // System.out.println("Created user1: " + userDAO.createUser(user1));
        // System.out.println("Created user2: " + userDAO.createUser(user2));
        // System.out.println("Created user3: " + userDAO.createUser(user3));

        // =====================================================================
        // READ — Retrieving users from the database
        // =====================================================================

        System.out.println("\n--- READ: Retrieving Users ---\n");

        // TODO 3: Get all users
        // List<User> allUsers = userDAO.getAllUsers();
        // System.out.println("All users (" + allUsers.size() + "):");
        // for (User u : allUsers) {
        //     System.out.println("  " + u);
        // }

        // TODO 4: Get a specific user by ID
        // User foundUser = userDAO.getUserById(1);
        // System.out.println("\nFound user with ID 1: " + foundUser);

        // TODO 5: Search users by name
        // List<User> searchResults = userDAO.searchUsersByName("alice");
        // System.out.println("\nSearch for 'alice': " + searchResults.size() + " results");
        // for (User u : searchResults) {
        //     System.out.println("  " + u);
        // }

        // =====================================================================
        // UPDATE — Modifying user data
        // =====================================================================

        System.out.println("\n--- UPDATE: Modifying Users ---\n");

        // TODO 6: Update a user's information
        // User userToUpdate = userDAO.getUserById(1);
        // if (userToUpdate != null) {
        //     userToUpdate.setEmail("alice_updated@dev.com");
        //     userToUpdate.setAge(29);
        //     boolean updated = userDAO.updateUser(userToUpdate);
        //     System.out.println("Updated user: " + updated);
        //     System.out.println("After update: " + userDAO.getUserById(1));
        // }

        // =====================================================================
        // TRANSACTION — Atomic operations
        // =====================================================================

        System.out.println("\n--- TRANSACTION: Swap Emails ---\n");

        // TODO 7: Test the transaction (swap emails between two users)
        // System.out.println("Before swap:");
        // System.out.println("  User 1: " + userDAO.getUserById(1));
        // System.out.println("  User 2: " + userDAO.getUserById(2));
        //
        // boolean swapped = userDAO.swapEmails(1, 2);
        // System.out.println("Swap successful: " + swapped);
        //
        // System.out.println("After swap:");
        // System.out.println("  User 1: " + userDAO.getUserById(1));
        // System.out.println("  User 2: " + userDAO.getUserById(2));

        // =====================================================================
        // DELETE — Removing users
        // =====================================================================

        System.out.println("\n--- DELETE: Removing Users ---\n");

        // TODO 8: Delete a user
        // boolean deleted = userDAO.deleteUser(3);
        // System.out.println("Deleted user 3: " + deleted);
        //
        // List<User> remaining = userDAO.getAllUsers();
        // System.out.println("Remaining users: " + remaining.size());

        // =====================================================================
        // CLEANUP
        // =====================================================================

        // TODO 9: Close the database connection when done
        // DatabaseConnection.getInstance().closeConnection();

        System.out.println("\n============================================");
        System.out.println("   DATABASE DEMO COMPLETE");
        System.out.println("============================================");
    }
}

