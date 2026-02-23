# Polymorphism

# Understanding Polymorphism in Java

Polymorphism is one of the fundamental principles of Object-Oriented Programming that allows objects to take multiple forms. In Java, there are two types of polymorphism:

## 1. Compile-time Polymorphism (Static)

Also known as method overloading, this occurs when multiple methods in the same class have the same name but different parameters.

```java
class Calculator {
    // Method overloading example
    int add(int a, int b) {
        return a + b;
    }
    
    double add(double a, double b) {
        return a + b;
    }
}
```

## 2. Runtime Polymorphism (Dynamic)

Also known as method overriding, this occurs when a subclass provides a specific implementation of a method that is already defined in its parent class.

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

## Key Concepts

- **Method Overloading:**
    - Same method name in same class
    - Different parameters (number, type, or order)
    - Return type may or may not be different
- **Method Overriding:**
    - Same method signature in parent and child class
    - Runtime binding using virtual method invocation
    - Requires inheritance relationship

## Benefits of Polymorphism

- **Code Reusability:** Write flexible and reusable code
- **Method Overriding:** Achieve runtime polymorphism through inheritance
- **Interface Implementation:** Multiple classes can provide different implementations of the same interface
- **Loose Coupling:** Reduce dependencies between different parts of an application

## Practical Example

```java
// Interface
interface Shape {
    double calculateArea();
}

// Classes implementing the interface
class Circle implements Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Shape {
    private double width;
    private double height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
}
```

## Best Practices

- Always use the @Override annotation when overriding methods
- Keep method signatures consistent when overriding
- Use interfaces to implement polymorphic behavior when possible
- Consider using abstract classes when sharing common functionality

## Common Pitfalls

- Confusing overloading with overriding
- Forgetting to use the @Override annotation
- Incorrect method signatures in overridden methods
- Not considering access modifiers when overriding

Understanding polymorphism is crucial for writing flexible and maintainable Java code. It enables you to write programs that can work with objects of different types through a common interface, making your code more modular and easier to extend.