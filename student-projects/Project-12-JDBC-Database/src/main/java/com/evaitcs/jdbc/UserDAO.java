package com.evaitcs.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * CLASS: UserDAO (Data Access Object)
 * TOPIC: JDBC CRUD Operations with PreparedStatement
 * ============================================================================
 *
 * DAO PATTERN:
 * The DAO pattern separates database logic from business logic.
 * This class ONLY handles database operations — nothing else.
 *
 * KEY RULES:
 * 1. ALWAYS use PreparedStatement (NOT Statement) — prevents SQL injection!
 * 2. ALWAYS use try-with-resources for ResultSet and PreparedStatement
 * 3. NEVER build SQL by concatenating user input strings
 *
 * SQL INJECTION EXAMPLE:
 *   BAD:  "SELECT * FROM users WHERE name = '" + userInput + "'"
 *   If userInput = "'; DROP TABLE users; --" → DATABASE DELETED!
 *
 *   GOOD: "SELECT * FROM users WHERE name = ?"  + pstmt.setString(1, userInput)
 *   PreparedStatement escapes the input, preventing attacks.
 *
 * INTERVIEW TIP:
 * "I always use PreparedStatement to prevent SQL injection attacks.
 *  I also follow the DAO pattern to separate data access from business logic,
 *  making the code testable and maintainable."
 * ============================================================================
 */
public class UserDAO {

    private Connection connection;

    /**
     * TODO 1: Create a constructor that gets the connection from DatabaseConnection singleton
     *
     * public UserDAO() {
     *     this.connection = DatabaseConnection.getInstance().getConnection();
     * }
     */
    // YOUR CONSTRUCTOR HERE


    // =========================================================================
    // CREATE
    // =========================================================================

    /**
     * TODO 2: Insert a new user into the database
     *
     * SQL: INSERT INTO users (username, email, first_name, last_name, age) VALUES (?, ?, ?, ?, ?)
     *
     * Steps:
     * 1. Create the SQL string with ? placeholders
     * 2. Create a PreparedStatement
     * 3. Set each parameter (pstmt.setString(1, user.getUsername()), etc.)
     * 4. Execute the update
     * 5. Return true if rows were affected
     *
     * @param user the user to create (id will be auto-generated)
     * @return true if successful
     */
    public boolean createUser(User user) {
        // String sql = "INSERT INTO users (username, email, first_name, last_name, age) VALUES (?, ?, ?, ?, ?)";
        //
        // try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        //     pstmt.setString(1, user.getUsername());
        //     pstmt.setString(2, user.getEmail());
        //     pstmt.setString(3, user.getFirstName());
        //     pstmt.setString(4, user.getLastName());
        //     pstmt.setInt(5, user.getAge());
        //
        //     int rowsAffected = pstmt.executeUpdate();
        //     return rowsAffected > 0;
        // } catch (SQLException e) {
        //     System.err.println("Error creating user: " + e.getMessage());
        //     return false;
        // }

        return false; // Replace this line
    }

    // =========================================================================
    // READ
    // =========================================================================

    /**
     * TODO 3: Get a user by ID
     *
     * SQL: SELECT * FROM users WHERE id = ?
     *
     * Steps:
     * 1. Execute the query with PreparedStatement
     * 2. If ResultSet has a row, map it to a User object
     * 3. Return the User, or null if not found
     *
     * MAPPING: User user = new User(
     *     rs.getInt("id"),
     *     rs.getString("username"),
     *     rs.getString("email"),
     *     rs.getString("first_name"),
     *     rs.getString("last_name"),
     *     rs.getInt("age")
     * );
     *
     * @param id the user ID
     * @return the User object, or null if not found
     */
    public User getUserById(int id) {
        // TODO: Implement using PreparedStatement and ResultSet

        return null; // Replace this line
    }

    /**
     * TODO 4: Get all users from the database
     *
     * SQL: SELECT * FROM users ORDER BY id
     *
     * Loop through the ResultSet and create a User for each row.
     *
     * @return a list of all users
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        // TODO: Implement — loop through ResultSet with while (rs.next())

        return users;
    }

    /**
     * TODO 5: Search users by name (partial match)
     *
     * SQL: SELECT * FROM users WHERE first_name LIKE ? OR last_name LIKE ?
     *
     * Use % wildcards for partial matching:
     *   pstmt.setString(1, "%" + searchTerm + "%");
     *
     * @param searchTerm the name to search for
     * @return list of matching users
     */
    public List<User> searchUsersByName(String searchTerm) {
        List<User> users = new ArrayList<>();
        // TODO: Implement with LIKE query

        return users;
    }

    // =========================================================================
    // UPDATE
    // =========================================================================

    /**
     * TODO 6: Update an existing user
     *
     * SQL: UPDATE users SET username=?, email=?, first_name=?, last_name=?, age=? WHERE id=?
     *
     * @param user the user with updated data (must have valid id)
     * @return true if a row was updated
     */
    public boolean updateUser(User user) {
        // TODO: Implement using PreparedStatement

        return false; // Replace this line
    }

    // =========================================================================
    // DELETE
    // =========================================================================

    /**
     * TODO 7: Delete a user by ID
     *
     * SQL: DELETE FROM users WHERE id = ?
     *
     * @param id the user ID to delete
     * @return true if a row was deleted
     */
    public boolean deleteUser(int id) {
        // TODO: Implement using PreparedStatement

        return false; // Replace this line
    }

    // =========================================================================
    // TRANSACTION EXAMPLE
    // =========================================================================

    /**
     * TODO 8: Transfer data between two users using a TRANSACTION
     * Transactions ensure that BOTH operations succeed, or NEITHER does.
     *
     * Steps:
     * 1. Disable auto-commit: connection.setAutoCommit(false)
     * 2. Perform operation 1 (update user 1)
     * 3. Perform operation 2 (update user 2)
     * 4. If both succeed: connection.commit()
     * 5. If either fails: connection.rollback()
     * 6. Finally: connection.setAutoCommit(true)
     *
     * Example: Swap the emails of two users (just for practice!)
     *
     * @param userId1 first user's ID
     * @param userId2 second user's ID
     * @return true if transaction succeeded
     */
    public boolean swapEmails(int userId1, int userId2) {
        // TODO: Implement using transaction management
        // try {
        //     connection.setAutoCommit(false);
        //
        //     User user1 = getUserById(userId1);
        //     User user2 = getUserById(userId2);
        //
        //     String tempEmail = user1.getEmail();
        //     user1.setEmail(user2.getEmail());
        //     user2.setEmail(tempEmail);
        //
        //     updateUser(user1);
        //     updateUser(user2);
        //
        //     connection.commit();
        //     return true;
        // } catch (SQLException e) {
        //     try { connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        //     return false;
        // } finally {
        //     try { connection.setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); }
        // }

        return false; // Replace this line
    }
}

