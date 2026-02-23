# Creational Design Patterns in Java

## Understanding Creational Patterns

Creational patterns focus on the process of object creation. They provide solutions to instantiate objects in ways that are suitable to the situation. The main purpose of these patterns is to create objects in a manner that increases flexibility and reuse of existing code.

In traditional programming, object creation might seem straightforward - we simply use the `new` keyword. However, as our applications grow in complexity, direct object creation can lead to design problems or added complexity. Creational patterns address this issue by controlling the creation process.

## Singleton Pattern

The Singleton pattern ensures that a class has only one instance throughout the application's lifecycle while providing global access to that instance. Think of it as a global variable, but with controlled access and guaranteed single instantiation.

### Implementation

```java
public class DatabaseConnection {
    // The single instance is stored in a static field
    private static DatabaseConnection instance;

    // Database configuration
    private String url;
    private String username;
    private String password;

    // Private constructor prevents direct instantiation
    private DatabaseConnection() {
        // Load configuration from properties file
        this.url = "jdbc:mysql://localhost:3306/mydb";
        this.username = "admin";
        this.password = "secure_password";
    }

    // Thread-safe implementation using double-checked locking
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public void connect() {
        // Establish database connection
        System.out.println("Connected to database at " + url);
    }
}

```

### When to Use Singleton

The Singleton pattern is ideal when:

1. You need exactly one instance of a class to coordinate actions across the system
2. You want to control access to a shared resource, like a configuration manager or connection pool
3. You need to ensure that all system components use the same state information

### Real-world Example

Consider a logging system in an application:

```java
public class Logger {
    private static Logger instance;
    private FileWriter logFile;

    private Logger() {
        try {
            logFile = new FileWriter("application.log", true);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create log file", e);
        }
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        try {
            logFile.write(new Date() + ": " + message + "\\n");
            logFile.flush();
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }
}

```

## Factory Method Pattern

The Factory Method pattern defines an interface for creating objects but lets subclasses decide which class to instantiate. It's like a manufacturing facility that can produce different types of products based on the request.

### Implementation

```java
// Product interface
public interface Vehicle {
    void start();
    void stop();
    void drive();
}

// Concrete products
public class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car engine started");
    }

    @Override
    public void stop() {
        System.out.println("Car engine stopped");
    }

    @Override
    public void drive() {
        System.out.println("Car is driving on the road");
    }
}

public class Motorcycle implements Vehicle {
    @Override
    public void start() {
        System.out.println("Motorcycle engine started");
    }

    @Override
    public void stop() {
        System.out.println("Motorcycle engine stopped");
    }

    @Override
    public void drive() {
        System.out.println("Motorcycle is riding on the road");
    }
}

// Creator abstract class
public abstract class VehicleFactory {
    // Factory method
    abstract Vehicle createVehicle();

    // Template method using factory method
    public void deliverVehicle() {
        Vehicle vehicle = createVehicle();
        vehicle.start();
        vehicle.drive();
        System.out.println("Vehicle delivered!");
    }
}

// Concrete creators
public class CarFactory extends VehicleFactory {
    @Override
    Vehicle createVehicle() {
        return new Car();
    }
}

public class MotorcycleFactory extends VehicleFactory {
    @Override
    Vehicle createVehicle() {
        return new Motorcycle();
    }
}

```

### When to Use Factory Method

Use the Factory Method pattern when:

1. You don't know ahead of time what class of objects you need to create
2. You want to delegate the responsibility of object creation to subclasses
3. You want to provide users of your framework with a way to extend its internal components

## Abstract Factory Pattern

The Abstract Factory pattern provides an interface for creating families of related or dependent objects without specifying their concrete classes. Think of it as a factory of factories.

### Implementation

```java
// Abstract products
public interface Button {
    void render();
    void onClick();
}

public interface Checkbox {
    void render();
    void toggle();
}

// Concrete products for Light theme
public class LightButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering light button");
    }

    @Override
    public void onClick() {
        System.out.println("Light button clicked");
    }
}

public class LightCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering light checkbox");
    }

    @Override
    public void toggle() {
        System.out.println("Light checkbox toggled");
    }
}

// Concrete products for Dark theme
public class DarkButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering dark button");
    }

    @Override
    public void onClick() {
        System.out.println("Dark button clicked");
    }
}

public class DarkCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering dark checkbox");
    }

    @Override
    public void toggle() {
        System.out.println("Dark checkbox toggled");
    }
}

// Abstract factory
public interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete factories
public class LightThemeFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new LightButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new LightCheckbox();
    }
}

public class DarkThemeFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new DarkButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new DarkCheckbox();
    }
}

```

### When to Use Abstract Factory

The Abstract Factory pattern is useful when:

1. A system needs to be independent of how its products are created, composed, and represented
2. A system needs to be configured with one of multiple families of products
3. A family of related product objects is designed to be used together

## Builder Pattern

The Builder pattern separates the construction of a complex object from its representation, allowing the same construction process to create different representations.

### Implementation

```java
public class Pizza {
    private String dough;
    private String sauce;
    private String topping;

    // Private constructor - only Builder can create Pizza objects
    private Pizza() {}

    public static class Builder {
        private Pizza pizza;

        public Builder() {
            pizza = new Pizza();
        }

        public Builder setDough(String dough) {
            pizza.dough = dough;
            return this;
        }

        public Builder setSauce(String sauce) {
            pizza.sauce = sauce;
            return this;
        }

        public Builder setTopping(String topping) {
            pizza.topping = topping;
            return this;
        }

        public Pizza build() {
            return pizza;
        }
    }

    @Override
    public String toString() {
        return "Pizza with " + dough + " dough, " + sauce + " sauce, and " + topping + " topping.";
    }
}

// Usage example
public class PizzaRestaurant {
    public static void main(String[] args) {
        Pizza pizza = new Pizza.Builder()
            .setDough("thin crust")
            .setSauce("marinara")
            .setTopping("pepperoni")
            .build();

        System.out.println(pizza);
    }
}

```

### When to Use Builder

The Builder pattern is particularly useful when:

1. The algorithm for creating a complex object should be independent of the parts that make up the object and how they're assembled
2. The construction process must allow different representations for the object that's constructed
3. You need to build objects step by step and want to ensure all required parameters are set

## Prototype Pattern

The Prototype pattern creates new objects by cloning an existing object, known as the prototype. This is useful when the cost of creating an object is more expensive than copying an existing one.

### Implementation

```java
// Prototype interface
public interface Cloneable {
    Object clone();
}

public class Document implements Cloneable {
    private String content;
    private String formatting;
    private List<String> comments;

    public Document(String content, String formatting) {
        this.content = content;
        this.formatting = formatting;
        this.comments = new ArrayList<>();
    }

    // Copy constructor
    private Document(Document source) {
        this.content = source.content;
        this.formatting = source.formatting;
        // Deep copy of comments
        this.comments = new ArrayList<>(source.comments);
    }

    @Override
    public Document clone() {
        return new Document(this);
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    // Getters and setters
}

// Usage example
public class DocumentManager {
    public static void main(String[] args) {
        Document original = new Document("Hello World", "bold");
        original.addComment("First draft");

        Document copy = original.clone();
        copy.addComment("Revised version");

        // Original and copy are independent
    }
}

```

### When to Use Prototype

The Prototype pattern is beneficial when:

1. A system should be independent of how its products are created, composed, and represented
2. You need to avoid building a class hierarchy of factories that parallels the class hierarchy of products
3. Instances of a class can have only a few different combinations of state

## Common Pitfalls and Best Practices

When working with creational patterns, keep these important considerations in mind:

1. Thread Safety: Ensure thread safety in Singleton implementations, especially in multi-threaded applications. The double-checked locking pattern or initialization-on-demand holder idiom are recommended approaches.
2. Performance Implications: Consider the performance impact of your chosen pattern. For example, prototype cloning might be more efficient than creating new objects from scratch, but deep copying large objects can be expensive.
3. Flexibility vs Complexity: While these patterns provide flexibility, they also add complexity to your code. Always evaluate whether the added complexity is justified by the benefits gained.
4. Testing Considerations: Creational patterns can make unit testing more challenging. For example, Singletons can make it difficult to isolate components during testing. Consider using dependency injection to improve testability.

Remember, these patterns are tools in your toolbox - choose the right one for your specific needs, and don't force a pattern where it's not needed.