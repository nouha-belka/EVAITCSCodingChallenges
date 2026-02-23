# Statement Vs PreparedStatement Vs CallableStatement

# Understanding JDBC Statement Types with Real-World Examples

## 1. Statement: The Basic Query Tool

Think of Statement as a simple calculator - it performs basic operations but lacks advanced features.

### Real-World Example:

<aside>
Imagine you're running a small library system where you just need to display all books:

</aside>

```java
Statement stmt = connection.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM books");
while (rs.next()) {
    System.out.println("Book: " + rs.getString("title"));
}
```

However, this approach has limitations:

- ❌ If a user inputs a title like "The Art of SQL'; DROP TABLE books; --", your database could be compromised
- ❌ Each query needs to be parsed and compiled separately
- ❌ No caching of query plans

## 2. PreparedStatement: The Smart Query Tool

Think of PreparedStatement as a modern calculator with memory function - it remembers and optimizes repeated calculations.

### Real-World Example: Online Bookstore

<aside>
Consider an e-commerce scenario where users search for books by different criteria:

</aside>

```java
// Safe way to handle user input
PreparedStatement pstmt = connection.prepareStatement(
    "SELECT * FROM books WHERE title LIKE ? AND price <= ?"
);

// Search for Java books under $50
pstmt.setString(1, "%Java%");
pstmt.setDouble(2, 50.00);
ResultSet rs = pstmt.executeQuery();

// Later, reuse the same statement for Python books
pstmt.setString(1, "%Python%");
pstmt.setDouble(2, 50.00);
rs = pstmt.executeQuery();
```

### Common Use Cases:

- 🛒 Shopping cart operations
- 🔍 Search functionality
- 👤 User authentication
- 📝 Form submissions

## 3. CallableStatement: The Specialized Tool

Think of CallableStatement as a specialized machine in a factory - designed for specific, complex operations.

### Real-World Example: Banking System

<aside>
Imagine a banking application where you need to transfer money between accounts:

</aside>

```java
// Stored procedure call for money transfer
CallableStatement cstmt = connection.prepareCall("{call transfer_money(?, ?, ?, ?)}");

// Set input parameters
cstmt.setInt(1, fromAccount);    // From account
cstmt.setInt(2, toAccount);      // To account
cstmt.setDouble(3, amount);      // Transfer amount

// Register output parameter for transfer status
cstmt.registerOutParameter(4, Types.VARCHAR);

// Execute transfer
cstmt.execute();

// Get result
String transferStatus = cstmt.getString(4);
```

## Practical Comparison: Student Registration System

| **Operation** | **Statement** | **PreparedStatement** | **CallableStatement** |
| --- | --- | --- | --- |
| View All Students | Good ✅ | Overkill ⚠️ | Unnecessary ❌ |
| Student Login | Dangerous ❌ | Perfect ✅ | Overkill ⚠️ |
| Course Registration | Risky ❌ | Good ✅ | Best ✅ |

## Error Handling with Context

Let's look at a real-world error handling scenario:

```java
public void registerStudent(Student student) {
    try (PreparedStatement pstmt = connection.prepareStatement(
        "INSERT INTO students (name, email, course_id) VALUES (?, ?, ?)"
    )) {
        pstmt.setString(1, student.getName());
        pstmt.setString(2, student.getEmail());
        pstmt.setInt(3, student.getCourseId());
        pstmt.executeUpdate();
        
    } catch (SQLIntegrityConstraintViolationException e) {
        // Specific handling for duplicate entries
        throw new BusinessException("Student already registered for this course");
        
    } catch (SQLException e) {
        // Log the technical details for IT team
        logger.error("Database error: " + e.getMessage(), e);
        // Show user-friendly message
        throw new BusinessException("Registration failed - please try again later");
        
    } finally {
        // Cleanup code if needed
    }
}
```

## Performance Best Practices

- **Batch Processing:** Use addBatch() for multiple similar operations
- **Connection Pooling:** Implement connection pooling using HikariCP or similar
- **Resource Management:** Always use try-with-resources
- **Query Optimization:** Use appropriate indexes and analyze query plans

<aside>
💡 Pro Tip: When developing a new application, start with PreparedStatement as your default choice. Only use Statement for very simple, one-off queries, and CallableStatement when you need to execute stored procedures.

</aside>

## Security Considerations

> Remember: Security is not an afterthought. Always validate input and use PreparedStatement to prevent SQL injection attacks.
> 

This comprehensive approach to JDBC statements will help you build more secure, efficient, and maintainable applications.