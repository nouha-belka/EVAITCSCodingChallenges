package com.evaitcs.designpatterns;

/**
 * ============================================================================
 * INTERFACE: Notification
 * TOPIC: SOLID — Interface Segregation Principle (ISP) + Open/Closed (OCP)
 * ============================================================================
 *
 * ISP: "Clients should not be forced to depend on methods they don't use."
 * This interface is SMALL and FOCUSED — it only defines what a notification does.
 *
 * OCP: "Open for extension, closed for modification."
 * To add a NEW notification type (e.g., Slack), just create a new class
 * that implements this interface — NO existing code needs to change!
 *
 * INTERVIEW TIP:
 * "I follow the Open/Closed Principle by programming to interfaces.
 *  Adding new notification channels requires zero changes to existing code."
 * ============================================================================
 */
public interface Notification {

    /**
     * TODO 1: Declare a method to send a notification
     * @param recipient who to send it to
     * @param message the message content
     */
    // void send(String recipient, String message);

    /**
     * TODO 2: Declare a method to get the notification type name
     * @return the type as a String (e.g., "EMAIL", "SMS", "PUSH")
     */
    // String getType();
}

