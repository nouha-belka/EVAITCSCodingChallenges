# Inheritance

# Understanding Inheritance in Java

Inheritance is a fundamental concept in Object-Oriented Programming that allows a class to inherit attributes and methods from another class. This creates a parent-child (or superclass-subclass) relationship between classes.

## Basic Syntax

```java
class Parent {
    // Parent class members
}

class Child extends Parent {
    // Child class members
}
```

## Types of Inheritance

- **Single Inheritance:** A class inherits from one parent class
- **Multilevel Inheritance:** A class inherits from a child class
- **Hierarchical Inheritance:** Multiple classes inherit from one parent class

## Key Concepts

### 1. extends Keyword

The 'extends' keyword is used to establish inheritance between classes.

```java
public class Animal {
    void eat() {
        System.out.println("This animal eats food");
    }
}

public class Dog extends Animal {
    void bark() {
        System.out.println("The dog barks");
    }
}
```

### 2. super Keyword

The 'super' keyword is used to refer to the parent class members.

```java
class Child extends Parent {
    Child() {
        super(); // Calls parent constructor
    }
    
    void display() {
        super.display(); // Calls parent method
    }
}
```

### 3. Method Overriding

Child classes can override methods from parent classes to provide specific implementations.

```java
class Animal {
    void makeSound() {
        System.out.println("Some sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Woof!");
    }
}
```

## Important Rules

- **Private members** of the parent class are not inherited by the child class
- **Constructors** are not inherited, but the parent's constructor is called when creating a child object
- Java doesn't support multiple inheritance through classes (a class can't extend multiple classes)
- All Java classes inherit from the `Object` class by default

## Best Practices

- Use inheritance to model "is-a" relationships
- Keep the inheritance hierarchy simple and shallow
- Override `toString()`, `equals()`, and `hashCode()` methods when necessary
- Use composition over inheritance when possible

## Common Use Cases

- Creating specialized versions of classes
- Implementing common behavior in parent classes
- Building hierarchical class structures
- Code reuse and maintenance

Understanding inheritance is crucial for creating well-structured, maintainable Java applications. It promotes code reuse and helps in creating logical hierarchies of classes.