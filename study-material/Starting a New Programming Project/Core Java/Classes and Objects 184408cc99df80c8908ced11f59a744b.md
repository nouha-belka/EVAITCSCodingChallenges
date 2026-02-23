# Classes and Objects

# Understanding Classes and Objects in Java

Classes and objects are fundamental concepts in object-oriented programming (OOP). They form the building blocks of Java applications.

## What is a Class?

A class is a blueprint or template for creating objects. It defines the properties (attributes) and behaviors (methods) that objects of that type will have.

```java
public class Car {
    // Attributes
    private String brand;
    private String model;
    private int year;
    
    // Constructor
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
    
    // Methods
    public void startEngine() {
        System.out.println("The engine is starting...");
    }
}
```

## What is an Object?

An object is an instance of a class. It represents a specific entity with its own set of data based on the class template.

```java
// Creating objects of the Car class
Car myCar = new Car("Toyota", "Camry", 2024);
Car anotherCar = new Car("Honda", "Civic", 2023);
```

## Key Components of a Class

### 1. Attributes (Fields)

- Instance variables that store object data
- Can have different access modifiers (private, public, protected)
- Define the state of an object

### 2. Constructors

- Special methods used to initialize objects
- Can have multiple constructors (constructor overloading)
- Must have the same name as the class

### 3. Methods

- Define the behavior of objects
- Can accept parameters and return values
- Represent what the object can do

## Object-Oriented Principles

1. EncapsulationBundling data and methods that operate on that data within a single unit (class). This is typically achieved using access modifiers.
    
    ```java
    public class BankAccount {
        private double balance;  // encapsulated data
        
        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
            }
        }
    }
    ```
    
2. InheritanceMechanism that allows a class to inherit properties and methods from another class.
    
    ```java
    public class ElectricCar extends Car {
        private int batteryCapacity;
        
        public void charge() {
            System.out.println("Charging the battery...");
        }
    }
    ```
    

## Best Practices

- Always initialize object attributes to ensure consistent object state
- Use meaningful names for classes, methods, and attributes
- Follow encapsulation by making attributes private and providing getter/setter methods
- Document your classes and methods using Javadoc comments
- Keep classes focused on a single responsibility

## Common Operations with Objects

```java
// Object creation
Car myCar = new Car("Tesla", "Model 3", 2024);

// Accessing methods
myCar.startEngine();

// Object comparison
Car car1 = new Car("BMW", "X5", 2024);
Car car2 = new Car("BMW", "X5", 2024);
boolean areEqual = car1.equals(car2);  // requires proper equals() implementation

// Object reference
Car carRef = myCar;  // both variables refer to the same object
```

Understanding classes and objects is crucial for Java development as they form the foundation for building scalable and maintainable applications.