package com.evaitcs.exceptionfileio;

/**
 * ============================================================================
 * CUSTOM EXCEPTION: InvalidContactException (UNCHECKED)
 * TOPIC: Exception Handling — Creating custom unchecked exceptions
 * ============================================================================
 *
 * UNCHECKED EXCEPTIONS:
 * - Extend RuntimeException
 * - The compiler does NOT force you to handle them
 * - Use for PROGRAMMING ERRORS (e.g., null name, invalid email format)
 *
 * WHEN TO USE:
 * When the error indicates a BUG in the code that should be FIXED,
 * not caught. "Name is null" → Fix the calling code!
 * ============================================================================
 */

// TODO 1: Create InvalidContactException that extends RuntimeException
// Include:
//   - Constructor with String message
//   - Constructor with String message and Throwable cause
//   - A field for the invalid field name (e.g., "email", "phone")
//   - A getter for the field name

public class InvalidContactException extends RuntimeException {

    // TODO: Add a private final String field called 'fieldName'

    // TODO: Constructor(String fieldName, String message)
    //   Call super(message) and set this.fieldName

    // TODO: Constructor(String fieldName, String message, Throwable cause)
    //   Call super(message, cause)

    // TODO: Getter for fieldName
}

