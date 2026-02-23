package com.evaitcs.designpatterns;

/**
 * ============================================================================
 * CLASS: NormalStrategy
 * TOPIC: Strategy Pattern — Normal delivery: send via the specified channel only
 * ============================================================================
 */
public class NormalStrategy implements NotificationStrategy {

    /**
     * TODO 1: Implement execute()
     * For NORMAL notifications, just send via the given notification channel.
     * Print: "[NORMAL] Standard delivery to [recipient]"
     * Then call notification.send(recipient, message).
     */
    @Override
    public void execute(Notification notification, String recipient, String message) {
        // TODO: Implement normal delivery
    }

    @Override
    public String getStrategyName() {
        return "NORMAL";
    }
}

