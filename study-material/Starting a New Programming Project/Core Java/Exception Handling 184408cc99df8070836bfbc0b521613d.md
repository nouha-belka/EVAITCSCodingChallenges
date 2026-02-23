# Exception Handling

# Understanding Exception Handling in Java

Exception handling is a crucial mechanism in Java that helps manage runtime errors and maintain program flow. Let's explore the hierarchy and concepts:

## 1. Throwable Class

The Throwable class is the root of Java's exception hierarchy. It's the superclass of all errors and exceptions.

```java
try {
    throw new Throwable("This is a throwable object");
} catch (Throwable t) {
    System.out.println(t.getMessage());
}
```

## 2. Error

Errors represent serious problems that a reasonable application should not try to catch. They typically indicate problems that cannot be handled.

- **Examples:** OutOfMemoryError, StackOverflowError

```java
// Example of an Error (don't try to catch these in practice)
public void infiniteRecursion() {
    infiniteRecursion(); // Will cause StackOverflowError
}
```

## 3. Exception Types

### 3.1 Checked Exceptions (Compile-Time)

These must be either caught or declared in the method signature using 'throws'. They are checked at compile time.

- **Examples:** IOException, SQLException, ClassNotFoundException

```java
public void readFile() throws IOException {
    FileReader file = new FileReader("nonexistent.txt");
    // Code that might throw IOException
}
```

### 3.2 Unchecked Exceptions (Runtime)

These are exceptions that occur during runtime and don't need to be explicitly caught or declared.

- **Examples:** NullPointerException, ArrayIndexOutOfBoundsException, ArithmeticException

```java
public void divideNumbers(int a, int b) {
    try {
        int result = a / b; // Might throw ArithmeticException
    } catch (ArithmeticException e) {
        System.out.println("Cannot divide by zero!");
    }
}
```

## 4. Creating Custom Exceptions

You can create your own exception classes by extending either Exception (checked) or RuntimeException (unchecked).

```java
// Custom checked exception
public class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Custom unchecked exception
public class InvalidUserDataException extends RuntimeException {
    public InvalidUserDataException(String message) {
        super(message);
    }
}

// Using custom exceptions
public void validateAge(int age) throws InvalidAgeException {
    if (age < 0) {
        throw new InvalidAgeException("Age cannot be negative");
    }
}
```

## 5. Best Practices

- Always catch the most specific exception first
- Use finally block for cleanup code
- Create custom exceptions for business-specific error cases
- Include meaningful error messages in exceptions
- Avoid catching Throwable or Error

```java
try {
    // Risky code here
} catch (SpecificException e) {
    // Handle specific exception
} catch (Exception e) {
    // Handle general exceptions
} finally {
    // Cleanup code
}
```

Understanding this hierarchy and when to use each type of exception is crucial for writing robust Java applications.