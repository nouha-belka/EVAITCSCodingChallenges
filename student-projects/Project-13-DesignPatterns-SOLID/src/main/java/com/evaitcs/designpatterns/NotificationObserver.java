package com.evaitcs.designpatterns;

/**
 * ============================================================================
 * INTERFACE: NotificationObserver
 * TOPIC: Observer Pattern — The "listener" side
 * ============================================================================
 *
 * OBSERVER PATTERN:
 * When something happens (an "event"), all registered observers get notified.
 * Think of it like subscribing to a YouTube channel — when a new video is
 * posted, all subscribers get notified.
 *
 * Components:
 *   Subject (EventManager) — holds a list of observers, notifies them
 *   Observer (this interface) — defines what happens when notified
 *
 * INTERVIEW TIP:
 * "The Observer pattern defines a one-to-many dependency. When one object
 *  changes state, all dependents are notified automatically. It's the
 *  foundation of event-driven programming."
 * ============================================================================
 */
public interface NotificationObserver {

    /**
     * TODO 1: Declare the update method
     * This is called when the observer is notified of an event.
     *
     * @param eventType what happened (e.g., "USER_REGISTERED", "ORDER_PLACED")
     * @param data additional data about the event
     */
    // void onEvent(String eventType, String data);
}

