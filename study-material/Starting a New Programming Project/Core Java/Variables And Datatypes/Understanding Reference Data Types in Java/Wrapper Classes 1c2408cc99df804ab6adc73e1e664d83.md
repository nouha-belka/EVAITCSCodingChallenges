# Wrapper Classes

## Introduction to Wrapper Classes

Wrapper classes in Java provide a way to use primitive data types as objects. Each primitive data type has a corresponding wrapper class that encapsulates (or "wraps") the primitive value within an object.

## Types of Wrapper Classes

Java provides eight wrapper classes corresponding to the eight primitive data types:

| Primitive Type | Wrapper Class | Size in Bits |
| --- | --- | --- |
| byte | Byte | 8 |
| short | Short | 16 |
| int | Integer | 32 |
| long | Long | 64 |
| float | Float | 32 |
| double | Double | 64 |
| boolean | Boolean | 1 |
| char | Character | 16 |

## Boxing and Unboxing

Boxing and unboxing are the processes of converting between primitive types and their corresponding wrapper classes.

### Boxing

Boxing is the process of converting a primitive value into a wrapper object.

```java
// Manual boxing
Integer boxedInt = Integer.valueOf(42);

// Autoboxing (automatically done by Java)
Integer autoBoxed = 42; // Java automatically converts int to Integer

// Boxing with explicit constructor (deprecated since JDK 9)
// Integer oldWay = new Integer(42); // Not recommended

```

### Unboxing

Unboxing is the process of extracting the primitive value from a wrapper object.

```java
// Manual unboxing
Integer boxedValue = Integer.valueOf(42);
int primitiveValue = boxedValue.intValue();

// Auto-unboxing
Integer wrapper = 42;
int primitive = wrapper; // Java automatically converts Integer to int

// Auto-unboxing in expressions
Integer num1 = 10;
Integer num2 = 20;
int sum = num1 + num2; // Both numbers are auto-unboxed, added, then assigned to sum

```

## Common Methods and Utilities

### Parsing Methods

Each wrapper class provides methods to parse strings into their respective types:

```java
// Parsing strings to primitive values
int parsedInt = Integer.parseInt("42");
double parsedDouble = Double.parseDouble("3.14");
boolean parsedBoolean = Boolean.parseBoolean("true");
long parsedLong = Long.parseLong("1234567890");

// Parse with different radix (base)
int binaryNum = Integer.parseInt("1010", 2);  // Converts binary to decimal
int hexNum = Integer.parseInt("1A", 16);      // Converts hexadecimal to decimal

```

### Value Conversion Methods

Wrapper classes provide methods to convert between different numeric types:

```java
Double doubleObj = 42.5;

// Converting to other numeric types
byte byteValue = doubleObj.byteValue();
short shortValue = doubleObj.shortValue();
int intValue = doubleObj.intValue();
long longValue = doubleObj.longValue();
float floatValue = doubleObj.floatValue();

```

### Utility Methods

Wrapper classes come with various utility methods for common operations:

```java
// Integer utilities
System.out.println(Integer.MAX_VALUE);  // Maximum value for int
System.out.println(Integer.MIN_VALUE);  // Minimum value for int
System.out.println(Integer.SIZE);       // Size in bits
System.out.println(Integer.BYTES);      // Size in bytes

// Character utilities
System.out.println(Character.isDigit('5'));        // true
System.out.println(Character.isLetter('A'));       // true
System.out.println(Character.isWhitespace(' '));   // true
System.out.println(Character.toUpperCase('a'));    // 'A'

// Boolean utilities
System.out.println(Boolean.logicalAnd(true, false));  // false
System.out.println(Boolean.logicalOr(true, false));   // true
System.out.println(Boolean.logicalXor(true, false));  // true

```

## Important Considerations

### Memory and Performance

Wrapper objects require more memory than their primitive counterparts:

```java
// Memory comparison
int primitive = 42;           // Takes 4 bytes
Integer wrapper = 42;         // Takes 16 bytes (object overhead + int value)

// Performance consideration
long startTime = System.nanoTime();
int sum = 0;
for (int i = 0; i < 1000000; i++) {
    sum += i;  // Faster with primitives
}
long endTime = System.nanoTime();

```

### Null Handling

Unlike primitives, wrapper objects can be null:

```java
Integer nullableInteger = null;  // Valid
int primitive = nullableInteger; // Throws NullPointerException

// Safe handling
if (nullableInteger != null) {
    primitive = nullableInteger;
}

// Optional usage (modern Java)
Optional<Integer> optionalInteger = Optional.ofNullable(nullableInteger);
primitive = optionalInteger.orElse(0);

```

### Caching

Some wrapper classes cache commonly used values for better performance:

```java
// Integer caches values from -128 to 127 by default
Integer a = 127;
Integer b = 127;
System.out.println(a == b);    // true (same cached object)

Integer c = 128;
Integer d = 128;
System.out.println(c == d);    // false (different objects)

// Boolean caches TRUE and FALSE
Boolean bool1 = Boolean.valueOf(true);
Boolean bool2 = Boolean.valueOf(true);
System.out.println(bool1 == bool2);  // true (same cached object)

```

## Common Use Cases

- Collections: Java collections can only store objects, not primitives
- Null representation: When you need to represent the absence of a value
- Generic types: Type parameters in generic classes must be objects
- JSON/XML processing: Working with data serialization/deserialization
- Database operations: JDBC API uses wrapper classes for null handling

## Best Practices

- Use primitives for basic number storage and calculations
- Use wrapper classes when working with collections or when null values are needed
- Always use equals() instead of == when comparing wrapper objects
- Be cautious of automatic unboxing with null values
- Consider memory impact when using large arrays or collections of wrapper objects