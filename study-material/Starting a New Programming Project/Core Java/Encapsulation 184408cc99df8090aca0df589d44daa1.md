# Encapsulation

# Understanding Encapsulation in Java

Encapsulation is one of the four fundamental Object-Oriented Programming (OOP) concepts in Java. It's the mechanism of wrapping data and code together as a single unit and restricting access to internal details.

## Key Principles of Encapsulation

- **Data Hiding:** Making instance variables private and accessible only through methods
- **Access Control:** Using access modifiers (private, protected, public) to control visibility
- **Getter/Setter Methods:** Providing controlled access to class members

## Implementation Example

```java
public class BankAccount {
    private double balance;  // private instance variable
    private String accountNumber;

    // Getter method
    public double getBalance() {
        return balance;
    }

    // Setter method
    public void setBalance(double amount) {
        if (amount >= 0) {
            this.balance = amount;
        }
    }
}
```

## Benefits of Encapsulation

- **Better Control:** Control how data is accessed and modified
- **Data Security:** Prevent unauthorized access to internal implementation
- **Flexibility:** Change implementation without affecting code that uses the class
- **Maintainability:** Easier to maintain and modify code

## Best Practices

1. Declare instance variables as private
2. Provide public getter and setter methods as needed
3. Include validation in setter methods
4. Use meaningful method names (get/set followed by variable name)

## Common Mistakes to Avoid

- Making instance variables public
- Not including validation in setters
- Breaking encapsulation by exposing internal details

## Practical Exercise

```java
// Create a Student class with encapsulation
public class Student {
    private String name;
    private int age;
    private double gpa;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0 && age < 120) {
            this.age = age;
        }
    }
}
```

## Key Takeaways

Encapsulation is essential for building robust and maintainable Java applications. It provides data hiding, improves security, and makes code more flexible and easier to maintain. When implementing encapsulation, always consider the appropriate access levels and include proper validation in setter methods.