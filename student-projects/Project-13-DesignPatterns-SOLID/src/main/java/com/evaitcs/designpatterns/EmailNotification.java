package com.evaitcs.designpatterns;

/**
 * ============================================================================
 * CLASS: EmailNotification
 * TOPIC: SOLID — Single Responsibility Principle (SRP)
 * ============================================================================
 *
 * SRP: This class has ONE job — send email notifications. Nothing else.
 * It doesn't handle SMS, doesn't manage a database, doesn't log events.
 *
 * INTERVIEW TIP:
 * "Each class should have only one reason to change. If I need to modify
 *  how emails are sent, I only change EmailNotification — nothing else breaks."
 * ============================================================================
 */
public class EmailNotification implements Notification {

    /**
     * TODO 1: Implement the send() method
     * Print: "[EMAIL] Sending to [recipient]: [message]"
     * In a real app, this would use JavaMail API or a service like SendGrid.
     */
    @Override
    public void send(String recipient, String message) {
        // TODO: Print the email notification
        // System.out.println("[EMAIL] Sending to " + recipient + ": " + message);
    }

    /**
     * TODO 2: Implement getType()
     * Return "EMAIL"
     */
    @Override
    public String getType() {
        return ""; // Replace this line
    }
}

