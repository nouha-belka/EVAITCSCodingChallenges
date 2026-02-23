# Lambda Expressions

## What are Lambda Expressions?

Lambda expressions are anonymous functions that provide a way to pass functionality as a method argument. They represent a key feature in Java 8, enabling more concise and functional programming approaches.

## Why Use Lambda Expressions?

- Enable cleaner and more readable code
- Reduce boilerplate code significantly
- Support functional programming concepts
- Enable easier implementation of functional interfaces

## Understanding Functional Programming

Functional programming is a programming paradigm that treats computation as the evaluation of mathematical functions while avoiding changing state and mutable data. Key principles include:

- Immutability - Once data is created, it cannot be changed
- Pure Functions - Functions that always return the same output for the same input
- First-class Functions - Functions can be passed as arguments
- No Side Effects - Functions don't modify external state

## Basic Lambda Syntax

```java
// Single parameter
parameter -> expression

// Multiple parameters
(param1, param2) -> expression

// With code block
(param1, param2) -> {
    // Multiple statements
    return result;
}

```

## Common Use Cases

### 1. Collection Processing

```java
List<String> names = Arrays.asList("John", "Mary", "Bob");
names.forEach(name -> System.out.println(name));
names.stream()
    .filter(name -> name.length() > 3)
    .forEach(System.out::println);

```

### 2. Event Handling

```java
button.addActionListener(e -> System.out.println("Button clicked!"));

```

### 3. Comparators

```java
Collections.sort(names, (a, b) -> a.compareTo(b));

```

## Functional Interfaces

Common functional interfaces used with lambda expressions:

Predicate - Takes one argument, returns boolean
Consumer - Takes one argument, returns void

- Function<T,R> - Takes one argument, returns a result
Supplier - Takes no arguments, returns a result

## Best Practices

- Keep lambda expressions simple and focused
- Use method references when possible (String::length vs s -> s.length())
- Consider readability when choosing between lambda and traditional approaches
- Avoid side effects in lambda expressions

Lambda expressions have transformed Java programming by introducing functional programming concepts and enabling more concise, readable code while maintaining type safety.