# SOLID Principles

# Detailed Explanation of SOLID Principles

## 1. Single Responsibility Principle (SRP) - In Depth

The Single Responsibility Principle is fundamental to clean code design. Think of it as the "one thing, one class" rule.

### Key Aspects of SRP:

- Each class should focus on doing one thing exceptionally well
- If a class has multiple responsibilities, changes to one aspect might affect others unexpectedly
- Easier testing and maintenance when classes have a single focus

```java
// Example showing violation of SRP
class OrderProcessor {
    public void processOrder(Order order) {
        // Validates order
        validateOrder(order);
        
        // Saves to database
        saveToDatabase(order);
        
        // Sends confirmation email
        sendConfirmationEmail(order);
        
        // Generates invoice
        generateInvoice(order);
    }
}

// Better approach following SRP
class OrderValidator {
    public boolean validateOrder(Order order) {
        // Validation logic
        return true;
    }
}

class OrderRepository {
    public void saveOrder(Order order) {
        // Database operations
    }
}

class EmailService {
    public void sendConfirmation(Order order) {
        // Email sending logic
    }
}

class InvoiceGenerator {
    public void generateInvoice(Order order) {
        // Invoice generation logic
    }
}
```

## 2. Open/Closed Principle (OCP) - Detailed Analysis

The Open/Closed Principle is about designing your code to be extensible without modification. This reduces the risk of introducing bugs in existing code.

### Key Benefits of OCP:

- Reduces risk when adding new features
- Promotes use of interfaces and abstract classes
- Makes code more modular and reusable

```java
// Advanced OCP Example
interface PaymentProcessor {
    void processPayment(Payment payment);
}

class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void processPayment(Payment payment) {
        // Credit card specific logic
    }
}

class PayPalProcessor implements PaymentProcessor {
    @Override
    public void processPayment(Payment payment) {
        // PayPal specific logic
    }
}

// Adding new payment method doesn't require modifying existing code
class CryptoCurrencyProcessor implements PaymentProcessor {
    @Override
    public void processPayment(Payment payment) {
        // Cryptocurrency specific logic
    }
}
```

## 3. Liskov Substitution Principle (LSP) - Comprehensive Guide

LSP is about ensuring that inheritance is used correctly. Subtypes must be substitutable for their base types without altering the correctness of the program.

```java
// Complex LSP Example
abstract class Account {
    protected double balance;
    
    public abstract void withdraw(double amount);
    public abstract void deposit(double amount);
}

class SavingsAccount extends Account {
    @Override
    public void withdraw(double amount) {
        if (balance - amount >= 0) {
            balance -= amount;
        }
    }
    
    @Override
    public void deposit(double amount) {
        balance += amount;
    }
}

class CurrentAccount extends Account {
    private double overdraftLimit;
    
    @Override
    public void withdraw(double amount) {
        if (balance - amount >= -overdraftLimit) {
            balance -= amount;
        }
    }
    
    @Override
    public void deposit(double amount) {
        balance += amount;
    }
}
```

## 4. Interface Segregation Principle (ISP) - Practical Implementation

ISP promotes the idea of creating specific interfaces rather than one large, general-purpose interface. This prevents classes from implementing methods they don't need.

```java
// Comprehensive ISP Example
interface Printer {
    void print(Document document);
}

interface Scanner {
    void scan(Document document);
}

interface Fax {
    void fax(Document document);
}

// Basic printer only needs to implement Printer
class BasicPrinter implements Printer {
    @Override
    public void print(Document document) {
        // Print logic
    }
}

// All-in-one implements all interfaces
class AllInOnePrinter implements Printer, Scanner, Fax {
    @Override
    public void print(Document document) {
        // Print logic
    }
    
    @Override
    public void scan(Document document) {
        // Scan logic
    }
    
    @Override
    public void fax(Document document) {
        // Fax logic
    }
}
```

## 5. Dependency Inversion Principle (DIP) - Advanced Concepts

DIP is crucial for creating loosely coupled applications. It suggests that high-level modules should depend on abstractions, not concrete implementations.

```java
// Advanced DIP Example with Dependency Injection
interface Logger {
    void log(String message);
}

class FileLogger implements Logger {
    @Override
    public void log(String message) {
        // File logging logic
    }
}

class DatabaseLogger implements Logger {
    @Override
    public void log(String message) {
        // Database logging logic
    }
}

class UserService {
    private final Logger logger;
    
    // Constructor injection
    public UserService(Logger logger) {
        this.logger = logger;
    }
    
    public void createUser(User user) {
        try {
            // User creation logic
            logger.log("User created: " + user.getName());
        } catch (Exception e) {
            logger.log("Error creating user: " + e.getMessage());
        }
    }
}
```

## Practical Implementation Tips

- Start with SRP when refactoring existing code
- Use interfaces to achieve OCP and DIP
- Consider composition over inheritance when LSP becomes difficult to maintain
- Keep interfaces focused and specific (ISP)
- Use dependency injection frameworks to help with DIP

Remember that SOLID principles are guidelines, not strict rules. Use them wisely and pragmatically based on your specific use case and requirements.