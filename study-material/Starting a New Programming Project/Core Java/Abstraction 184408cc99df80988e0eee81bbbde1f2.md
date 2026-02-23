# Abstraction

# Understanding Abstraction in Java

Abstraction is one of the four fundamental Object-Oriented Programming (OOP) concepts. It is the process of hiding implementation details and showing only the functionality to the user.

## Two Ways to Achieve Abstraction

- **Abstract Classes:** Partial abstraction (0-100%)
- **Interfaces:** Complete abstraction (100%)

## Abstract Classes

An abstract class is declared using the 'abstract' keyword. It can have both abstract and concrete methods.

```java
abstract class Animal {
    abstract void makeSound(); // abstract method
    void eat() {              // concrete method
        System.out.println("I can eat.");
    }
}

class Dog extends Animal {
    void makeSound() {
        System.out.println("Woof!");
    }
}
```

## Key Points About Abstract Classes

- Cannot be instantiated (cannot create objects)
- Can have constructors and static methods
- Can have final methods
- Must be extended by concrete classes

## Interfaces

An interface is a completely abstract class that contains only abstract methods.

```java
interface Vehicle {
    void start();
    void stop();
    void accelerate();
}

class Car implements Vehicle {
    public void start() {
        System.out.println("Car started");
    }
    public void stop() {
        System.out.println("Car stopped");
    }
    public void accelerate() {
        System.out.println("Car is accelerating");
    }
}
```

## Key Points About Interfaces

- All methods are public and abstract by default
- All fields are public, static, and final by default
- A class can implement multiple interfaces
- Since Java 8, can have default and static methods

## Benefits of Abstraction

- **Reduced Complexity:** Hide implementation details and show only necessary features
- **Increased Security:** Important details are hidden from users
- **Easy Maintenance:** Code can be changed without affecting other classes
- **Code Reusability:** Abstract classes can be reused through inheritance

## When to Use What?

| **Abstract Class** | **Interface** |
| --- | --- |
| When some common functionality in several classes needs to be shared | When total abstraction is needed |
| When base class functionality needs to be extended | When multiple inheritance is required |
| When you want to share code among several closely related classes | When defining a contract for a class to implement |

## Best Practices

- Keep interfaces small and focused
- Use abstract classes when common functionality needs to be shared
- Follow the ISP (Interface Segregation Principle)
- Use interfaces to define types

Remember: Abstraction helps in managing complexity by hiding unnecessary details and exposing only the essential features of an object.