package com.evaitcs.exceptionfileio;

/**
 * ============================================================================
 * CUSTOM EXCEPTION: ContactNotFoundException (CHECKED)
 * TOPIC: Exception Handling — Creating custom checked exceptions
 * ============================================================================
 *
 * CHECKED EXCEPTIONS:
 * - Extend Exception (not RuntimeException)
 * - The compiler FORCES you to handle them (try/catch or throws)
 * - Use for RECOVERABLE situations (e.g., "contact not found, try again")
 *
 * WHEN TO USE:
 * When the caller CAN and SHOULD do something about the error.
 * "Contact not found" → The user can try a different search term.
 *
 * INTERVIEW TIP:
 * "I use checked exceptions for recoverable errors that the caller should
 *  handle, like file not found or network timeout. I use unchecked exceptions
 *  for programming errors like null arguments or invalid state."
 * ============================================================================
 */

// TODO 1: Create ContactNotFoundException that extends Exception
// It should have:
//   - A constructor that takes a String message: super(message);
//   - A constructor that takes a String message AND a Throwable cause: super(message, cause);
//
// Example:
// public class ContactNotFoundException extends Exception {
//     public ContactNotFoundException(String message) {
//         super(message);
//     }
//     public ContactNotFoundException(String message, Throwable cause) {
//         super(message, cause);
//     }
// }

public class ContactNotFoundException extends Exception {
    // YOUR CONSTRUCTORS HERE
    public ContactNotFoundException(String message){
        super(message);
    }
    public ContactNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}

