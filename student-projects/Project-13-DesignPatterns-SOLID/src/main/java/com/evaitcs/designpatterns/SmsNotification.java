package com.evaitcs.designpatterns;

/**
 * ============================================================================
 * CLASS: SmsNotification
 * TOPIC: SRP — This class ONLY handles SMS notifications
 * ============================================================================
 */
public class SmsNotification implements Notification {

    /**
     * TODO 1: Implement send()
     * Print: "[SMS] Texting [recipient]: [message]"
     */
    @Override
    public void send(String recipient, String message) {
        // TODO: Implement
    }

    /**
     * TODO 2: Implement getType()
     * Return "SMS"
     */
    @Override
    public String getType() {
        return ""; // Replace this line
    }
}

