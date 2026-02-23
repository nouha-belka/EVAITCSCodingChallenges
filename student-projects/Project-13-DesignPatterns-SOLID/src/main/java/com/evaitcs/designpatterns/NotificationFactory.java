package com.evaitcs.designpatterns;

/**
 * ============================================================================
 * CLASS: NotificationFactory
 * TOPIC: Factory Method Pattern — Creating objects without exposing logic
 * ============================================================================
 *
 * FACTORY PATTERN:
 * Instead of the client code doing: new EmailNotification(), new SmsNotification()
 * They call: NotificationFactory.create("EMAIL")
 *
 * WHY?
 * 1. Client code doesn't need to know about concrete classes
 * 2. Adding a new type requires changing ONLY the factory (OCP-ish)
 * 3. Centralized object creation logic
 *
 * INTERVIEW TIP:
 * "The Factory pattern encapsulates object creation. The client asks for
 *  what it needs ('EMAIL'), and the factory decides which class to instantiate.
 *  This reduces coupling between client code and concrete implementations."
 * ============================================================================
 */
public class NotificationFactory {

    /**
     * TODO 1: Create a static factory method
     *
     * Method: createNotification(String type)
     * Returns: Notification
     *
     * Use a switch or if/else to return the correct implementation:
     *   "EMAIL" → new EmailNotification()
     *   "SMS"   → new SmsNotification()
     *   "PUSH"  → new PushNotification()
     *   default → throw IllegalArgumentException("Unknown notification type: " + type)
     *
     * @param type the type of notification to create
     * @return the appropriate Notification implementation
     */
    public static Notification createNotification(String type) {
        // TODO: Implement the factory method using a switch statement
        // switch (type.toUpperCase()) {
        //     case "EMAIL": return new EmailNotification();
        //     case "SMS":   return new SmsNotification();
        //     case "PUSH":  return new PushNotification();
        //     default: throw new IllegalArgumentException("Unknown type: " + type);
        // }

        return null; // Replace this line
    }

    // =========================================================================
    // BONUS: Factory with configuration
    // =========================================================================

    /**
     * TODO 2 (BONUS): Create an overloaded factory method that accepts
     * a type AND a priority level, returning a notification pre-configured
     * with the appropriate strategy.
     *
     * This combines Factory + Strategy patterns!
     */
    // YOUR BONUS CODE HERE
}

