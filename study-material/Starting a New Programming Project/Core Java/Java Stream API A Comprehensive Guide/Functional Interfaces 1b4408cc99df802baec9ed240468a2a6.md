# Functional Interfaces

Functional interfaces are a cornerstone of Java's support for functional programming, introduced in Java 8. They enable lambda expressions and method references, making code more concise and expressive.

## What is a Functional Interface?

A functional interface is an interface that contains exactly one abstract method. It can have multiple default or static methods, but must have only one method that needs implementation.

```java
@FunctionalInterface  // Optional but recommended annotation
interface Calculator {
    int calculate(int a, int b);  // Single abstract method
    
    // Can have default methods
    default void printInfo() {
        System.out.println("Calculator interface");
    }
}

```

## Built-in Functional Interfaces

Java provides several built-in functional interfaces in the java.util.function package:

### Function<T,R>

Takes an input of type T and returns a result of type R

```java
Function<String, Integer> strLength = str -> str.length();
Integer length = strLength.apply("Hello");  // Returns 5

Predicate
```

Takes an input and returns a boolean

```java
Predicate<String> isEmpty = str -> str.isEmpty();
boolean result = isEmpty.test("");  // Returns true

Consumer
```

Accepts an input and performs an operation (returns void)

```java
Consumer<String> printer = str -> System.out.println(str);
printer.accept("Hello World");  // Prints: Hello World

Supplier
```

Takes no input but returns a result

```java
Supplier<Double> random = () -> Math.random();
Double value = random.get();  // Returns a random number

```

### BiFunction<T,U,R>

Takes two inputs and returns a result

```java
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
Integer sum = add.apply(5, 3);  // Returns 8

```

## Creating Custom Functional Interfaces

You can create your own functional interfaces for specific use cases:

```java
@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

// Usage
TriFunction<Integer, Integer, Integer, Integer> addThree = 
    (a, b, c) -> a + b + c;
Integer result = addThree.apply(1, 2, 3);  // Returns 6

```

## Using with Streams

Functional interfaces are extensively used with the Stream API:

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// Using Predicate
names.stream()
    .filter(name -> name.length() > 4)
    .forEach(System.out::println);

// Using Function
List<Integer> lengths = names.stream()
    .map(String::length)
    .collect(Collectors.toList());

// Using Consumer
names.stream()
    .forEach(name -> System.out.println("Hello " + name));

```

## Method References

Method references provide a shorthand notation for lambda expressions:

```java
// Instead of: str -> System.out.println(str)
Consumer<String> printer = System.out::println;

// Instead of: (str) -> str.length()
Function<String, Integer> lengthFunc = String::length;

// Constructor reference
Supplier<ArrayList<String>> listSupplier = ArrayList::new;

```

## Common Use Cases

### 1. Event Handling

```java
button.addActionListener(e -> System.out.println("Button clicked"));

```

### 2. Callback Functions

```java
public void processData(String data, Consumer<String> callback) {
    // Process the data
    String result = data.toUpperCase();
    // Call the callback
    callback.accept(result);
}

```

### 3. Strategy Pattern

```java
interface ValidationStrategy {
    boolean validate(String text);
}

// Usage
ValidationStrategy numberOnly = text -> text.matches("[0-9]+");
ValidationStrategy noSpecialChars = text -> text.matches("[A-Za-z0-9]+");

```

Remember that functional interfaces are the foundation of lambda expressions in Java. They enable more concise and readable code while promoting functional programming practices.