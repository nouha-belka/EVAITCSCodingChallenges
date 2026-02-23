package com.evaitcs.basicsyntax;

import java.util.Scanner;

/**
 * ============================================================================
 * PROJECT: Command-Line Calculator
 * TOPIC: Java Basic Syntax — Variables, Data Types, Operations, Control Flow
 * ============================================================================
 *
 * INSTRUCTIONS:
 * Complete each TODO section below. Each section practices a key Java concept.
 * Read the comments carefully — they will guide you through each task!
 *
 * LEARNING GOALS:
 * - Declare and use variables with appropriate data types
 * - Perform arithmetic, comparison, and logical operations
 * - Use if/else statements and switch statements
 * - Write and call methods
 * - Use loops (for, while, do-while)
 * - Read user input with Scanner
 * ============================================================================
 */
public class Calculator {

    // =========================================================================
    // SECTION 1: VARIABLES AND DATA TYPES
    // =========================================================================
    // In Java, every variable must have a type declared before it can be used.
    // Common types: int, double, String, boolean, char, long, float
    // =========================================================================

    // TODO 1: Declare a class-level constant for PI (use the 'final' keyword)
    // Hint: public static final double PI = ???;


    // TODO 2: Declare a class-level constant for the calculator's name
    // Hint: public static final String CALCULATOR_NAME = ???;


    // =========================================================================
    // SECTION 2: ARITHMETIC METHODS
    // =========================================================================
    // Methods in Java are declared with: accessModifier returnType methodName(parameters)
    // Example: public static int add(int a, int b) { return a + b; }
    // =========================================================================

    /**
     * TODO 3: Complete the add method
     * This method should return the sum of two double numbers.
     *
     * @param a the first number
     * @param b the second number
     * @return the sum of a and b
     */
    public static double add(double a, double b) {
        // TODO: Return the sum of a and b
        return 0; // Replace this line
    }

    /**
     * TODO 4: Complete the subtract method
     *
     * @param a the first number
     * @param b the second number
     * @return the difference of a and b
     */
    public static double subtract(double a, double b) {
        // TODO: Return the difference of a and b
        return 0; // Replace this line
    }

    /**
     * TODO 5: Complete the multiply method
     *
     * @param a the first number
     * @param b the second number
     * @return the product of a and b
     */
    public static double multiply(double a, double b) {
        // TODO: Return the product of a and b
        return 0; // Replace this line
    }

    /**
     * TODO 6: Complete the divide method
     * IMPORTANT: You must handle division by zero!
     * If b is 0, print an error message and return 0.
     *
     * @param a the dividend
     * @param b the divisor (must not be zero)
     * @return the quotient of a divided by b, or 0 if b is zero
     */
    public static double divide(double a, double b) {
        // TODO: Check if b is zero first (use an if statement)
        // If b IS zero, print "Error: Cannot divide by zero!" and return 0
        // If b is NOT zero, return a / b

        return 0; // Replace this line
    }

    /**
     * TODO 7: Write a modulo (remainder) method from scratch!
     * Method name: modulo
     * Parameters: two integers (int a, int b)
     * Returns: the remainder when a is divided by b
     * Handle: division by zero (same as divide method)
     *
     * Hint: The modulo operator in Java is %
     */
    // YOUR CODE HERE — write the entire method


    // =========================================================================
    // SECTION 3: COMPARISON AND LOGICAL OPERATIONS
    // =========================================================================
    // Comparison operators: == != > < >= <=
    // Logical operators: && (AND) || (OR) ! (NOT)
    // =========================================================================

    /**
     * TODO 8: Complete this method to find the maximum of two numbers
     * Use a comparison operator (> or <) with an if/else statement.
     *
     * @param a first number
     * @param b second number
     * @return the larger of the two numbers
     */
    public static double findMax(double a, double b) {
        // TODO: Use an if/else to return the larger number
        // Hint: if (a > b) { ... } else { ... }

        return 0; // Replace this line
    }

    /**
     * TODO 9: Complete this method to check if a number is within a range
     * Use logical operators (&& or ||)
     *
     * @param number the number to check
     * @param min    the minimum value (inclusive)
     * @param max    the maximum value (inclusive)
     * @return true if number is between min and max (inclusive), false otherwise
     */
    public static boolean isInRange(double number, double min, double max) {
        // TODO: Return true if number >= min AND number <= max
        // Hint: Use the && operator

        return false; // Replace this line
    }

    /**
     * TODO 10: Complete this method to check if a number is positive, negative, or zero
     * Use if/else if/else chain
     *
     * @param number the number to check
     * @return "positive", "negative", or "zero"
     */
    public static String checkSign(double number) {
        // TODO: Use if/else if/else to check the sign
        // Return "positive" if number > 0
        // Return "negative" if number < 0
        // Return "zero" if number == 0

        return ""; // Replace this line
    }

    // =========================================================================
    // SECTION 4: LOOPS AND ADVANCED CALCULATIONS
    // =========================================================================
    // for loop:    for (int i = 0; i < n; i++) { ... }
    // while loop:  while (condition) { ... }
    // do-while:    do { ... } while (condition);
    // =========================================================================

    /**
     * TODO 11: Calculate the power of a number using a for loop
     * Do NOT use Math.pow() — practice using a loop instead!
     *
     * @param base     the base number
     * @param exponent the exponent (assume non-negative integer)
     * @return base raised to the power of exponent
     */
    public static double power(double base, int exponent) {
        // TODO: Use a for loop to multiply base by itself 'exponent' times
        // Start with result = 1, then multiply by base in each iteration
        // Example: power(2, 3) = 2 * 2 * 2 = 8

        return 0; // Replace this line
    }

    /**
     * TODO 12: Calculate the factorial of a number using a while loop
     * Factorial: n! = n × (n-1) × (n-2) × ... × 1
     * Example: 5! = 5 × 4 × 3 × 2 × 1 = 120
     *
     * @param n the number (assume non-negative)
     * @return the factorial of n
     */
    public static long factorial(int n) {
        // TODO: Use a while loop to calculate n!
        // Handle edge case: 0! = 1

        return 0; // Replace this line
    }

    /**
     * TODO 13: Sum all numbers from 1 to n using a do-while loop
     *
     * @param n the upper limit
     * @return the sum of numbers from 1 to n
     */
    public static int sumUpTo(int n) {
        // TODO: Use a do-while loop to sum 1 + 2 + 3 + ... + n

        return 0; // Replace this line
    }

    // =========================================================================
    // SECTION 5: SWITCH STATEMENT — MENU SYSTEM
    // =========================================================================
    // switch statements are great for menus and fixed choices.
    // switch (variable) {
    //     case value1: ... break;
    //     case value2: ... break;
    //     default: ...
    // }
    // =========================================================================

    /**
     * TODO 14: Complete the main method to create an interactive calculator
     * Use a switch statement to handle the user's menu choice.
     *
     * The menu should look like:
     * ================================
     *   JAVA CALCULATOR
     * ================================
     * 1. Add
     * 2. Subtract
     * 3. Multiply
     * 4. Divide
     * 5. Power
     * 6. Factorial
     * 7. Exit
     * ================================
     * Choose an operation:
     */
    public static void main(String[] args) {
        // Scanner is used to read user input from the console
        Scanner scanner = new Scanner(System.in);

        // TODO: Create a boolean variable called 'running' set to true
        // This will control our while loop


        // TODO: Create a while loop that runs as long as 'running' is true
        // Inside the loop:
        //
        // Step 1: Print the menu (see format above)
        //
        // Step 2: Read the user's choice (an integer)
        //         Hint: int choice = scanner.nextInt();
        //
        // Step 3: Use a switch statement on 'choice':
        //   case 1: Ask for two numbers, call add(), print the result
        //   case 2: Ask for two numbers, call subtract(), print the result
        //   case 3: Ask for two numbers, call multiply(), print the result
        //   case 4: Ask for two numbers, call divide(), print the result
        //   case 5: Ask for base and exponent, call power(), print the result
        //   case 6: Ask for a number, call factorial(), print the result
        //   case 7: Print "Goodbye!", set running = false
        //   default: Print "Invalid choice! Please try again."
        //
        // Step 4: For cases 1-4, reading two numbers looks like:
        //         System.out.print("Enter first number: ");
        //         double num1 = scanner.nextDouble();
        //         System.out.print("Enter second number: ");
        //         double num2 = scanner.nextDouble();


        // Don't forget to close the scanner when done!
        scanner.close();
    }
}

