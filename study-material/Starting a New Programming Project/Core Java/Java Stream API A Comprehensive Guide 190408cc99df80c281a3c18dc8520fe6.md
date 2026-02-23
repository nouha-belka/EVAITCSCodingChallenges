# Java Stream API: A Comprehensive Guide

## Understanding Streams

A Stream in Java represents a sequence of elements that can be processed in a declarative way. Think of it as a conveyor belt in a factory where items pass through different processing stations. Each station can modify, filter, or transform the items without affecting the original source.

### The Power of Streams

Streams revolutionize the way we process data in Java by enabling:

- Declarative programming (describing what you want rather than how to do it)
- Parallel processing with minimal effort
- Chainable operations that create readable, maintainable code
- Reduced boilerplate compared to traditional iteration

### Stream Creation

Let's explore the various ways to create streams:

```java
// From Collections
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
Stream<String> streamFromList = names.stream();

// From Arrays
String[] array = {"Alice", "Bob", "Charlie"};
Stream<String> streamFromArray = Arrays.stream(array);

// Stream.of()
Stream<String> streamOf = Stream.of("Alice", "Bob", "Charlie");

// Infinite Streams
Stream<Integer> infiniteNumbers = Stream.iterate(1, n -> n + 1);
Stream<Double> randomNumbers = Stream.generate(Math::random);

// From files (each line becomes a stream element)
Stream<String> linesFromFile = Files.lines(Paths.get("data.txt"));

```

## Stream Operations

Stream operations are divided into two categories: intermediate and terminal operations. Understanding this distinction is crucial for effective stream usage.

### Intermediate Operations

These operations transform a stream into another stream. They are lazy, meaning they won't execute until a terminal operation is called.

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

Stream<String> processedStream = names.stream()
    .filter(name -> name.length() > 4)    // Only names longer than 4 characters
    .map(String::toUpperCase)             // Convert to uppercase
    .peek(System.out::println)            // Debugging operation
    .distinct();                          // Remove duplicates

// Nothing happens until terminal operation is called

```

Let's examine each intermediate operation:

### filter()

```java
// Finding even numbers
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
List<Integer> evenNumbers = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList()); // [2, 4, 6]

```

### map()

```java
// Converting strings to their lengths
List<String> words = Arrays.asList("hello", "world");
List<Integer> wordLengths = words.stream()
    .map(String::length)
    .collect(Collectors.toList()); // [5, 5]

```

### flatMap()

```java
// Flattening a list of lists
List<List<Integer>> nestedNumbers = Arrays.asList(
    Arrays.asList(1, 2),
    Arrays.asList(3, 4)
);
List<Integer> flattenedNumbers = nestedNumbers.stream()
    .flatMap(Collection::stream)
    .collect(Collectors.toList()); // [1, 2, 3, 4]

```

### sorted()

```java
// Custom sorting
List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
List<String> sortedNames = names.stream()
    .sorted(Comparator.reverseOrder())
    .collect(Collectors.toList()); // [Charlie, Bob, Alice]

```

### Terminal Operations

Terminal operations produce a result from a stream pipeline. They are eager and start the entire stream processing.

### collect()

```java
// Various collection operations
Stream<String> stream = Stream.of("apple", "banana", "cherry");

// To List
List<String> list = stream.collect(Collectors.toList());

// To Set
Set<String> set = stream.collect(Collectors.toSet());

// To Map
Map<String, Integer> map = stream.collect(
    Collectors.toMap(
        str -> str,           // Key mapper
        String::length        // Value mapper
    )
);

// Joining strings
String joined = stream.collect(Collectors.joining(", "));

```

### reduce()

```java
// Finding the sum of numbers
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
Optional<Integer> sum = numbers.stream()
    .reduce((a, b) -> a + b);
// or
Integer sum2 = numbers.stream()
    .reduce(0, Integer::sum);

// Finding the longest string
Optional<String> longest = Stream.of("cat", "rabbit", "horse")
    .reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2);

```

### forEach()

```java
// Processing each element
Stream.of("apple", "banana", "cherry")
    .forEach(fruit -> System.out.println("I like " + fruit));

```

## Advanced Stream Concepts

### Parallel Streams

Parallel streams can significantly improve performance for large datasets by utilizing multiple threads:

```java
// Converting to parallel stream
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
int sum = numbers.parallelStream()
    .reduce(0, Integer::sum);

// Or converting regular stream to parallel
int sum2 = numbers.stream()
    .parallel()
    .reduce(0, Integer::sum);

```

### Custom Collectors

Creating custom collectors for specialized collection operations:

```java
public class CustomCollector {
    public static <T> Collector<T, ?, List<List<T>>> partitionBySize(int size) {
        return Collectors.collectingAndThen(
            Collectors.toList(),
            list -> {
                List<List<T>> partitioned = new ArrayList<>();
                for (int i = 0; i < list.size(); i += size) {
                    partitioned.add(new ArrayList<>(
                        list.subList(i, Math.min(i + size, list.size()))
                    ));
                }
                return partitioned;
            }
        );
    }
}

```

### Stream Best Practices

1. Use Method References When Possible

```java
// Instead of:
stream.map(str -> str.toLowerCase())
// Use:
stream.map(String::toLowerCase)

```

1. Avoid Side Effects in Stream Operations

```java
// Bad practice:
List<String> results = new ArrayList<>();
stream.forEach(results::add);

// Good practice:
List<String> results = stream.collect(Collectors.toList());

```

1. Consider Performance Implications

```java
// Unnecessary intermediate operations
stream.sorted().filter(x -> x > 0).sorted()

// Optimized
stream.filter(x -> x > 0).sorted()

```

## Common Interview Questions and Solutions

1. Find the Average of Numbers

```java
double average = numbers.stream()
    .mapToInt(Integer::intValue)
    .average()
    .orElse(0.0);

```

1. Group Elements by a Property

```java
Map<Integer, List<String>> groupedByLength = strings.stream()
    .collect(Collectors.groupingBy(String::length));

```

1. Find Top N Elements

```java
List<Integer> topThree = numbers.stream()
    .sorted(Comparator.reverseOrder())
    .limit(3)
    .collect(Collectors.toList());

```

1. Count Frequency of Elements

```java
Map<String, Long> frequency = strings.stream()
    .collect(Collectors.groupingBy(
        Function.identity(),
        Collectors.counting()
    ));

```

## Debugging Streams

Using peek() for debugging:

```java
List<Integer> result = numbers.stream()
    .peek(n -> System.out.println("Before filter: " + n))
    .filter(n -> n > 3)
    .peek(n -> System.out.println("After filter: " + n))
    .map(n -> n * 2)
    .peek(n -> System.out.println("After map: " + n))
    .collect(Collectors.toList());

```

## Common Pitfalls and Solutions

1. Stream Reuse

```java
// Wrong - Stream has already been operated upon or closed
Stream<String> stream = list.stream();
stream.forEach(System.out::println);
stream.forEach(System.out::println); // IllegalStateException

// Correct - Create new stream for each operation
list.stream().forEach(System.out::println);
list.stream().forEach(System.out::println);

```

1. Infinite Streams

```java
// Wrong - Will process indefinitely
Stream.iterate(1, n -> n + 1).forEach(System.out::println);

// Correct - Limit the stream
Stream.iterate(1, n -> n + 1)
    .limit(10)
    .forEach(System.out::println);

```

## Practice Exercises

1. Data Transformation Exercise

```java
// Given a list of employees, find the average salary per department
class Employee {
    String department;
    double salary;
}

Map<String, Double> avgSalaryByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.averagingDouble(Employee::getSalary)
    ));

```

1. Filtering and Mapping Exercise

```java
// Find all unique words longer than 5 characters, converted to uppercase
Set<String> uniqueLongWords = text.split("\\\\s+").stream()
    .filter(word -> word.length() > 5)
    .map(String::toUpperCase)
    .collect(Collectors.toSet());

```

Remember that Stream operations are most powerful when combined thoughtfully. The key is to understand how each operation transforms the stream and to choose the right combination of operations to achieve your goal efficiently.

[Functional Interfaces](Java%20Stream%20API%20A%20Comprehensive%20Guide/Functional%20Interfaces%201b4408cc99df802baec9ed240468a2a6.md)

[Lambda Expressions](Java%20Stream%20API%20A%20Comprehensive%20Guide/Lambda%20Expressions%201b4408cc99df809ba897fc5cb35e83d7.md)