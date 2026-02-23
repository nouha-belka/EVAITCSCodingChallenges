package com.evaitcs.designpatterns;

/**
 * ============================================================================
 * INTERFACE: NotificationStrategy
 * TOPIC: Strategy Pattern — Interchangeable algorithms
 * ============================================================================
 *
 * STRATEGY PATTERN:
 * Define a family of algorithms, encapsulate each one, and make them
 * interchangeable. The strategy lets the algorithm vary independently
 * from the clients that use it.
 *
 * In our case: HOW to deliver a notification (urgent vs normal) is the
 * "algorithm" that can be swapped at runtime.
 *
 * INTERVIEW TIP:
 * "The Strategy pattern allows me to change behavior at runtime without
 *  modifying the class. Instead of if/else chains, each algorithm is
 *  encapsulated in its own class implementing a common interface."
 * ============================================================================
 */
public interface NotificationStrategy {

    /**
     * TODO 1: Declare a method to process a notification with a specific strategy
     *
     * @param notification the notification to process
     * @param recipient who to send it to
     * @param message the message content
     */
    // void execute(Notification notification, String recipient, String message);

    /**
     * TODO 2: Declare a method to get the strategy name
     * @return the strategy name (e.g., "URGENT", "NORMAL")
     */
    // String getStrategyName();
}

