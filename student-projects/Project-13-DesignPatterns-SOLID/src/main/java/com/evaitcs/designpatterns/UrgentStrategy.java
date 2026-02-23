package com.evaitcs.designpatterns;

/**
 * ============================================================================
 * CLASS: UrgentStrategy
 * TOPIC: Strategy Pattern — Urgent delivery: send via ALL channels
 * ============================================================================
 */
public class UrgentStrategy implements NotificationStrategy {

    /**
     * TODO 1: Implement execute()
     * For URGENT notifications, send via ALL channels (email, SMS, and push).
     * Print: "[URGENT] Sending via all channels to [recipient]"
     * Then call notification.send() with "[URGENT] " prepended to the message.
     */
    @Override
    public void execute(Notification notification, String recipient, String message) {
        // TODO: Implement urgent delivery
        // System.out.println("[URGENT] Sending via all channels to " + recipient);
        // notification.send(recipient, "[URGENT] " + message);
    }

    @Override
    public String getStrategyName() {
        return "URGENT"; // This one is done for you!
    }
}

