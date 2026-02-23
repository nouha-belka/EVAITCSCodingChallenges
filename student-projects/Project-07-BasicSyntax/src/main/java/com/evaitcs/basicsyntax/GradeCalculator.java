package com.evaitcs.basicsyntax;

/**
 * ============================================================================
 * PROJECT: Grade Calculator
 * TOPIC: Variables, Data Types, Control Structures, Methods
 * ============================================================================
 *
 * SCENARIO:
 * You are building a grade calculator for a school. The system needs to:
 * - Store student grades
 * - Calculate averages
 * - Determine letter grades
 * - Find the highest and lowest grades
 * - Determine if a student passes or fails
 *
 * INSTRUCTIONS:
 * Complete each TODO section. Each method practices specific Java concepts.
 * ============================================================================
 */
public class GradeCalculator {

    // =========================================================================
    // CONSTANTS
    // =========================================================================
    // Use constants for values that never change. This makes code easier to
    // maintain — if the passing grade changes, you only update ONE place!
    // =========================================================================

    // TODO 1: Declare a constant for the passing grade threshold (60.0)
    // Hint: public static final double PASSING_GRADE = ???;


    // TODO 2: Declare a constant for the maximum possible grade (100.0)


    // TODO 3: Declare a constant for the minimum possible grade (0.0)


    // =========================================================================
    // GRADE CALCULATIONS
    // =========================================================================

    /**
     * TODO 4: Calculate the average of an array of grades
     * Use a for loop to sum all grades, then divide by the count.
     *
     * @param grades an array of double values representing grades
     * @return the average grade, or 0.0 if the array is empty
     */
    public static double calculateAverage(double[] grades) {
        // TODO: Handle edge case — if grades is null or empty, return 0.0

        // TODO: Use a for loop to sum all grades
        // Hint: for (int i = 0; i < grades.length; i++) { sum += grades[i]; }

        // TODO: Return sum divided by grades.length

        return 0.0; // Replace this line
    }

    /**
     * TODO 5: Determine the letter grade based on a numeric grade
     * Use if/else if/else chain:
     *   90-100 → "A"
     *   80-89  → "B"
     *   70-79  → "C"
     *   60-69  → "D"
     *   Below 60 → "F"
     *
     * @param grade the numeric grade (0-100)
     * @return the letter grade as a String
     */
    public static String getLetterGrade(double grade) {
        // TODO: Use if/else if/else to determine the letter grade
        // Remember: check the highest range first!

        return ""; // Replace this line
    }

    /**
     * TODO 6: Find the highest grade in an array
     * Use a for loop and a comparison operator.
     *
     * @param grades an array of grades
     * @return the highest grade in the array
     */
    public static double findHighestGrade(double[] grades) {
        // TODO: Start with the first element as the highest
        // Loop through the rest and update if you find a higher one
        // Handle null/empty array edge case

        return 0.0; // Replace this line
    }

    /**
     * TODO 7: Find the lowest grade in an array
     *
     * @param grades an array of grades
     * @return the lowest grade in the array
     */
    public static double findLowestGrade(double[] grades) {
        // TODO: Similar to findHighestGrade, but find the minimum

        return 0.0; // Replace this line
    }

    /**
     * TODO 8: Check if a student passes based on their average grade
     * A student passes if their grade is >= PASSING_GRADE
     *
     * @param grade the student's grade
     * @return true if the student passes, false otherwise
     */
    public static boolean isPassingGrade(double grade) {
        // TODO: Return whether the grade meets or exceeds PASSING_GRADE
        // This should be a one-liner using a comparison operator!

        return false; // Replace this line
    }

    /**
     * TODO 9: Count how many students passed and failed
     * Use a for loop with an if/else inside.
     *
     * @param grades an array of all student grades
     * @return an int array where [0] = pass count, [1] = fail count
     */
    public static int[] countPassFail(double[] grades) {
        // TODO: Create an int array of size 2: int[] results = new int[2];
        // Loop through grades:
        //   If grade >= PASSING_GRADE → increment results[0]
        //   Else → increment results[1]
        // Return results

        return new int[]{0, 0}; // Replace this line
    }

    /**
     * TODO 10: Validate that a grade is within the valid range (0-100)
     * Use logical operators (&&)
     *
     * @param grade the grade to validate
     * @return true if grade is between MINIMUM and MAXIMUM (inclusive)
     */
    public static boolean isValidGrade(double grade) {
        // TODO: Return true if grade >= MIN AND grade <= MAX

        return false; // Replace this line
    }

    /**
     * TODO 11: Generate a report card string for a student
     * Use String concatenation or String.format()
     *
     * @param studentName the student's name
     * @param grades      the student's grades
     * @return a formatted report card string
     *
     * Expected output format:
     * ================================
     * REPORT CARD: John Smith
     * ================================
     * Average Grade: 85.5
     * Letter Grade: B
     * Highest Grade: 95.0
     * Lowest Grade: 72.0
     * Status: PASSED
     * ================================
     */
    public static String generateReportCard(String studentName, double[] grades) {
        // TODO: Use the methods you've already written above!
        // Call calculateAverage(), getLetterGrade(), findHighestGrade(), etc.
        // Build a formatted string and return it
        // Hint: You can use String.format("%.1f", number) to format decimals

        return ""; // Replace this line
    }

    // =========================================================================
    // MAIN METHOD — Test your code!
    // =========================================================================

    public static void main(String[] args) {
        // TODO 12: Test ALL of your methods here!
        // Create a sample array of grades and call each method.
        // Print the results to verify they work correctly.

        // Example:
        // double[] grades = {85.5, 92.0, 78.3, 65.0, 95.5, 88.0, 72.0};
        // System.out.println("Average: " + calculateAverage(grades));
        // System.out.println("Letter Grade: " + getLetterGrade(calculateAverage(grades)));
        // ... test all other methods ...
        // System.out.println(generateReportCard("John Smith", grades));

        System.out.println("=== Grade Calculator Tests ===");
        // YOUR TEST CODE HERE
    }
}

