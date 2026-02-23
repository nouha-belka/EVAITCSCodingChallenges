# Variables And Datatypes

# Understanding Java Variables

A variable in Java is a container that holds a value. It must be declared with a specific type before use.

## Variable Declaration Syntax

```java
dataType variableName = value;
// Example:
int age = 25;
String name = "John";
```

## Primitive Data Types

| Type | Size | Description | Example |
| --- | --- | --- | --- |
| byte | 1 byte | Whole numbers from -128 to 127 | byte b = 100; |
| short | 2 bytes | Whole numbers from -32,768 to 32,767 | short s = 5000; |
| int | 4 bytes | Whole numbers from -2^31 to 2^31-1 | int i = 100000; |
| long | 8 bytes | Whole numbers from -2^63 to 2^63-1 | long l = 15000000000L; |
| float | 4 bytes | Decimal numbers up to 7 decimal digits | float f = 5.75f; |
| double | 8 bytes | Decimal numbers up to 15 decimal digits | double d = 19.99; |
| boolean | 1 bit | True or false values | boolean b = true; |
| char | 2 bytes | Single character/letter or ASCII value | char c = 'A'; |

## Reference Data Types

Reference types are used to store references to objects. Some common reference types include:

- **String:** For text values
    
    ```java
    String name = "John Doe";
    ```
    
- **Arrays:** For collections of values
    
    ```java
    int[] numbers = {1, 2, 3, 4, 5};
    String[] names = new String[5];
    ```
    
- **Classes:** User-defined types
    
    ```java
    Person person = new Person();
    ```
    

## Variable Naming Rules

- Must begin with a letter, underscore (_), or dollar sign ($)
- Cannot start with a number
- Case-sensitive (age and Age are different variables)
- Cannot use Java keywords as variable names
- Should use camelCase convention for naming

## Variable Scope

Variables in Java have different scopes depending on where they are declared:

- **Instance Variables:** Declared inside a class but outside any method
- **Local Variables:** Declared inside a method or block
- **Static Variables:** Declared with static keyword, shared across all instances of a class

## Type Casting

Java supports two types of casting:

- **Widening Casting:** (automatic) - converting a smaller type to a larger type
    
    ```java
    int myInt = 9;
    double myDouble = myInt; // Automatic casting: int to double
    ```
    
- **Narrowing Casting:** (manual) - converting a larger type to a smaller type
    
    ```java
    double myDouble = 9.78;
    int myInt = (int) myDouble; // Manual casting: double to int
    ```
    

## Best Practices

- Always initialize variables before use
- Use meaningful variable names that describe their purpose
- Choose the appropriate data type for your needs
- Use final keyword for constants
- Declare variables in the smallest scope possible

[Understanding Reference Data Types in Java](Variables%20And%20Datatypes/Understanding%20Reference%20Data%20Types%20in%20Java%20190408cc99df8029a801f1a51a268c1b.md)