# Java While Loops and Do-While Loops - A Student's Guide

## Introduction

Loops are fundamental programming constructs that allow you to execute a block of code repeatedly based on a condition. In Java, there are two types of while loops: the standard `while` loop and the `do-while` loop. Let's explore both in detail!

## The While Loop

### What is a While Loop?

A while loop repeatedly executes a block of code as long as a given condition is true. Think of it like this: "While something is true, keep doing this."

### Basic Syntax

```java
while (condition) {
    // code block to be executed
}

```

### How It Works

1. The condition is checked BEFORE each iteration
2. If the condition is true, the code block executes
3. After execution, it goes back to step 1
4. If the condition is false, the loop ends

### Example 1: Counting to 5

```java
int count = 1;
while (count <= 5) {
    System.out.println("Count is: " + count);
    count++;
}

```

Output:

```
Count is: 1
Count is: 2
Count is: 3
Count is: 4
Count is: 5

```

### Example 2: Input Validation

```java
Scanner scanner = new Scanner(System.in);
int number = 0;
while (number <= 0) {
    System.out.println("Please enter a positive number: ");
    number = scanner.nextInt();
}
System.out.println("You entered: " + number);

```

## The Do-While Loop

### What is a Do-While Loop?

A do-while loop is similar to a while loop, but with one key difference: the code block is executed BEFORE checking the condition. This means the code block will always execute at least once!

### Basic Syntax

```java
do {
    // code block to be executed
} while (condition);

```

### How It Works

1. The code block executes first
2. THEN the condition is checked
3. If the condition is true, go back to step 1
4. If the condition is false, the loop ends

### Example 1: Menu System

```java
Scanner scanner = new Scanner(System.in);
int choice;

do {
    System.out.println("\\nMenu Options:");
    System.out.println("1. Start Game");
    System.out.println("2. Load Save");
    System.out.println("3. Exit");
    System.out.print("Enter your choice: ");
    choice = scanner.nextInt();
} while (choice != 3);

```

### Example 2: Number Guessing Game

```java
Scanner scanner = new Scanner(System.in);
int secretNumber = 7;
int guess;

do {
    System.out.print("Guess the number (1-10): ");
    guess = scanner.nextInt();

    if (guess > secretNumber) {
        System.out.println("Too high!");
    } else if (guess < secretNumber) {
        System.out.println("Too low!");
    }
} while (guess != secretNumber);

System.out.println("Congratulations! You guessed it!");

```

## When to Use Which?

### Use a While Loop When:

- You need to check the condition BEFORE executing any code
- The loop might not need to run at all
- You're not sure how many times the loop will need to run

Example: Reading file contents until reaching the end

### Use a Do-While Loop When:

- You want the code to execute AT LEAST ONCE before checking the condition
- You're creating menu systems or input validation where you always need at least one iteration
- You want to ensure the code block runs once regardless of the condition

Example: Getting valid user input

## Common Pitfalls to Avoid

1. **Infinite Loops**
    
    ```java
    // DON'T DO THIS!
    while (true) {
        System.out.println("This will never end!");
    }
    
    ```
    
    Always ensure there's a way to change the condition and exit the loop.
    
2. **Forgetting to Update the Counter**
    
    ```java
    // DON'T DO THIS!
    int count = 1;
    while (count <= 5) {
        System.out.println(count);
        // Forgot count++
    }
    
    ```
    
3. **Off-by-One Errors**
    
    ```java
    // Be careful with <= vs <
    int count = 1;
    while (count <= 5) { // Will run 5 times
        System.out.println(count);
        count++;
    }
    
    count = 1;
    while (count < 5) { // Will run 4 times
        System.out.println(count);
        count++;
    }
    
    ```
    

## Practice Exercises

1. Write a while loop that prints even numbers from 2 to 20.
2. Create a do-while loop that asks for a password until the correct one is entered.
3. Write a program that uses a while loop to calculate the sum of numbers from 1 to n, where n is input by the user.

## Conclusion

Both while and do-while loops are powerful tools in Java programming. The key is understanding their differences and knowing when to use each one. Remember:

- While loop: Check first, then maybe execute
- Do-while loop: Execute first, then check

Happy coding!