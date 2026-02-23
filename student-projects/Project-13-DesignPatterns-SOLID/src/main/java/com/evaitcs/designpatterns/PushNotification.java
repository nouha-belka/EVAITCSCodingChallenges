package com.evaitcs.designpatterns;

/**
 * ============================================================================
 * CLASS: PushNotification
 * TOPIC: SRP — This class ONLY handles push notifications
 * ============================================================================
 */
public class PushNotification implements Notification {

    /**
     * TODO 1: Implement send()
     * Print: "[PUSH] Notifying [recipient]: [message]"
     */
    @Override
    public void send(String recipient, String message) {
        // TODO: Implement
    }

    /**
     * TODO 2: Implement getType()
     * Return "PUSH"
     */
    @Override
    public String getType() {
        return ""; // Replace this line
    }
}

