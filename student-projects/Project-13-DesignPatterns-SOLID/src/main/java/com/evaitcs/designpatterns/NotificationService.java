package com.evaitcs.designpatterns;

/**
 * ============================================================================
 * CLASS: NotificationService (SINGLETON PATTERN)
 * TOPIC: Singleton — Only one instance ever exists
 * ============================================================================
 *
 * SINGLETON PATTERN:
 * Ensures only ONE NotificationService exists in the entire application.
 * This is useful because:
 * - Centralized notification management
 * - Shared configuration (e.g., rate limits)
 * - Resource efficiency (one connection pool for sending)
 *
 * INTERVIEW TIP:
 * "The Singleton pattern guarantees a single instance with a global access
 *  point. I use double-checked locking for thread safety. In Spring,
 *  beans are singletons by default."
 * ============================================================================
 */
public class NotificationService {

    // TODO 1: Declare a private static volatile instance
    // private static volatile NotificationService instance;


    // TODO 2: Declare any fields the service needs
    // private int notificationsSent;  // Track how many notifications sent


    /**
     * TODO 3: Create a PRIVATE constructor
     * Initialize notificationsSent to 0.
     * Print: "NotificationService initialized."
     */
    // YOUR PRIVATE CONSTRUCTOR HERE


    /**
     * TODO 4: Create getInstance() with double-checked locking
     *
     * public static NotificationService getInstance() {
     *     if (instance == null) {
     *         synchronized (NotificationService.class) {
     *             if (instance == null) {
     *                 instance = new NotificationService();
     *             }
     *         }
     *     }
     *     return instance;
     * }
     */
    // YOUR getInstance() METHOD HERE


    /**
     * TODO 5: Create a sendNotification method
     * This method uses the Factory pattern to create the right notification type
     * and sends it.
     *
     * Steps:
     * 1. Use NotificationFactory.createNotification(type) to get the right object
     * 2. Call notification.send(recipient, message)
     * 3. Increment notificationsSent counter
     * 4. Print: "Notification #[count] sent via [type]"
     *
     * @param type the notification type ("EMAIL", "SMS", "PUSH")
     * @param recipient who to send to
     * @param message what to send
     */
    public void sendNotification(String type, String recipient, String message) {
        // TODO: Use the factory to create and send notification
    }

    /**
     * TODO 6: Create a getter for notificationsSent
     */
    // YOUR GETTER HERE
}

