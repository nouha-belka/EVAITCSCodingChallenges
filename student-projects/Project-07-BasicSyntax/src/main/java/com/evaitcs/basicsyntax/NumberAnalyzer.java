package com.evaitcs.basicsyntax;

/**
 * ============================================================================
 * PROJECT: Number Analyzer
 * TOPIC: Loops, Conditionals, Methods, Big O Notation Awareness
 * ============================================================================
 *
 * SCENARIO:
 * Build a utility class that performs various analyses on numbers.
 * This will strengthen your understanding of loops, conditionals,
 * and introduce you to thinking about algorithm efficiency (Big O).
 *
 * INSTRUCTIONS:
 * Complete each TODO. Pay attention to the Big O comments — understanding
 * time complexity will help you in interviews!
 * ============================================================================
 */
public class NumberAnalyzer {

    // =========================================================================
    // SECTION 1: BASIC NUMBER OPERATIONS
    // =========================================================================

    /**
     * TODO 1: Check if a number is even or odd
     * Time Complexity: O(1) — constant time, no loops needed!
     *
     * @param number the number to check
     * @return true if the number is even, false if odd
     */
    public static boolean isEven(int number) {
        // TODO: Use the modulo operator (%)
        // A number is even if number % 2 == 0

        return false; // Replace this line
    }

    /**
     * TODO 2: Check if a number is prime
     * A prime number is only divisible by 1 and itself.
     * Time Complexity: O(√n) — we only need to check up to square root of n!
     *
     * @param number the number to check (assume positive)
     * @return true if the number is prime
     */
    public static boolean isPrime(int number) {
        // TODO: Handle edge cases first:
        //   - Numbers less than 2 are NOT prime
        //   - 2 is prime
        //   - Even numbers greater than 2 are NOT prime

        // TODO: Use a for loop from 3 to Math.sqrt(number)
        //   Check if number is divisible by i (number % i == 0)
        //   If yes → not prime (return false)
        //   Only check odd numbers (increment by 2)

        // If no divisor found → it IS prime (return true)

        return false; // Replace this line
    }

    /**
     * TODO 3: Reverse the digits of an integer
     * Example: 12345 → 54321
     * Time Complexity: O(d) where d = number of digits
     *
     * @param number the number to reverse
     * @return the reversed number
     */
    public static int reverseNumber(int number) {
        // TODO: Use a while loop:
        //   reversed = reversed * 10 + number % 10;
        //   number = number / 10;
        // Continue while number != 0

        return 0; // Replace this line
    }

    /**
     * TODO 4: Check if a number is a palindrome
     * A palindrome reads the same forwards and backwards.
     * Example: 12321 is a palindrome, 12345 is not.
     *
     * @param number the number to check
     * @return true if the number is a palindrome
     */
    public static boolean isPalindrome(int number) {
        // TODO: Use the reverseNumber method you just wrote!
        // A number is a palindrome if it equals its reverse
        // Hint: Handle negative numbers (they are NOT palindromes)

        return false; // Replace this line
    }

    // =========================================================================
    // SECTION 2: ARRAY ANALYSIS (Thinking about Big O)
    // =========================================================================

    /**
     * TODO 5: Find the sum of all elements in an array
     * Time Complexity: O(n) — we must visit every element once
     *
     * @param numbers the array of integers
     * @return the sum of all elements
     */
    public static int sumArray(int[] numbers) {
        // TODO: Use a for-each loop: for (int num : numbers) { ... }

        return 0; // Replace this line
    }

    /**
     * TODO 6: Check if an array contains a specific value
     * Time Complexity: O(n) — worst case, we check every element
     * This is called a "linear search"
     *
     * @param numbers the array to search
     * @param target  the value to find
     * @return true if the target is found in the array
     */
    public static boolean contains(int[] numbers, int target) {
        // TODO: Loop through the array
        // If you find the target, return true immediately
        // If the loop finishes without finding it, return false

        return false; // Replace this line
    }

    /**
     * TODO 7: Count how many times a value appears in an array
     * Time Complexity: O(n) — must check every element
     *
     * @param numbers the array to search
     * @param target  the value to count
     * @return how many times target appears in the array
     */
    public static int countOccurrences(int[] numbers, int target) {
        // TODO: Use a for loop and a counter variable

        return 0; // Replace this line
    }

    /**
     * TODO 8: Find the second largest element in an array
     * Time Complexity: O(n) — single pass through the array
     * This is a classic interview question!
     *
     * @param numbers the array (assume at least 2 elements)
     * @return the second largest element
     */
    public static int findSecondLargest(int[] numbers) {
        // TODO: Track two variables: 'largest' and 'secondLargest'
        // Initialize both to Integer.MIN_VALUE
        // Loop through the array:
        //   If current > largest → secondLargest = largest, largest = current
        //   Else if current > secondLargest → secondLargest = current

        return 0; // Replace this line
    }

    // =========================================================================
    // SECTION 3: PATTERN PRINTING (Loop mastery!)
    // =========================================================================

    /**
     * TODO 9: Print a right triangle pattern
     * For n = 5, print:
     * *
     * **
     * ***
     * ****
     * *****
     *
     * @param n the height of the triangle
     */
    public static void printTriangle(int n) {
        // TODO: Use nested for loops
        // Outer loop: rows (1 to n)
        // Inner loop: print '*' for each column in the current row
        // Don't forget System.out.println() after each row!
    }

    /**
     * TODO 10: Print a multiplication table
     * For n = 5, print:
     * 1  2  3  4  5
     * 2  4  6  8  10
     * 3  6  9  12 15
     * 4  8  12 16 20
     * 5  10 15 20 25
     *
     * Time Complexity: O(n²) — this is quadratic because of nested loops!
     *
     * @param n the size of the multiplication table
     */
    public static void printMultiplicationTable(int n) {
        // TODO: Use nested for loops
        // Outer loop: rows (1 to n)
        // Inner loop: columns (1 to n)
        // Print row * col, use \t for tab spacing
    }

    // =========================================================================
    // SECTION 4: FIBONACCI (Classic interview question!)
    // =========================================================================

    /**
     * TODO 11: Generate the first n Fibonacci numbers
     * Fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, ...
     * Each number is the sum of the two preceding ones.
     *
     * @param n how many Fibonacci numbers to generate
     * @return an array containing the first n Fibonacci numbers
     */
    public static int[] generateFibonacci(int n) {
        // TODO: Create an int array of size n
        // Set fib[0] = 0, fib[1] = 1
        // Loop from 2 to n-1: fib[i] = fib[i-1] + fib[i-2]
        // Return the array

        return new int[0]; // Replace this line
    }

    // =========================================================================
    // MAIN METHOD — Test everything!
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=== Number Analyzer Tests ===\n");

        // TODO 12: Test all your methods and print the results!

        // Example tests to get you started:
        // System.out.println("Is 4 even? " + isEven(4));         // true
        // System.out.println("Is 7 prime? " + isPrime(7));       // true
        // System.out.println("Reverse 12345: " + reverseNumber(12345)); // 54321
        // System.out.println("Is 12321 palindrome? " + isPalindrome(12321)); // true

        // int[] testArray = {3, 7, 2, 9, 1, 5, 9, 3};
        // System.out.println("Sum: " + sumArray(testArray));
        // System.out.println("Contains 5? " + contains(testArray, 5));
        // System.out.println("Count of 9: " + countOccurrences(testArray, 9));
        // System.out.println("Second largest: " + findSecondLargest(testArray));

        // System.out.println("\nTriangle (5):");
        // printTriangle(5);

        // System.out.println("\nMultiplication Table (5):");
        // printMultiplicationTable(5);

        // int[] fib = generateFibonacci(10);
        // System.out.print("Fibonacci: ");
        // for (int f : fib) System.out.print(f + " ");
    }
}

