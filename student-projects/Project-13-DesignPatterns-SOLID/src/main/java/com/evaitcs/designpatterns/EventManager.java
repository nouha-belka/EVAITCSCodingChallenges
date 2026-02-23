package com.evaitcs.designpatterns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ============================================================================
 * CLASS: EventManager (Observer Pattern — Subject)
 * TOPIC: Observer Pattern — The "publisher" that notifies observers
 * ============================================================================
 *
 * This class manages event subscriptions. Observers register for specific
 * event types, and when that event fires, all registered observers are notified.
 *
 * REAL WORLD EXAMPLES:
 * - YouTube: subscribe to channel → notified of new videos
 * - Event listeners in UI: button.onClick(handler)
 * - Message queues: Kafka, RabbitMQ
 *
 * SOLID — Dependency Inversion Principle (DIP):
 * EventManager depends on the NotificationObserver INTERFACE, not concrete
 * classes. This means you can add new observers without changing EventManager.
 * ============================================================================
 */
public class EventManager {

    // TODO 1: Declare a Map<String, List<NotificationObserver>>
    // Key = event type (e.g., "USER_REGISTERED")
    // Value = list of observers interested in that event
    // private Map<String, List<NotificationObserver>> listeners;


    /**
     * TODO 2: Create a constructor that initializes the listeners map
     */
    // YOUR CONSTRUCTOR HERE


    /**
     * TODO 3: Subscribe an observer to an event type
     *
     * @param eventType the event to listen for
     * @param observer the observer to notify
     */
    public void subscribe(String eventType, NotificationObserver observer) {
        // TODO: Get the list for this event type (create if it doesn't exist)
        // Add the observer to the list
        // Hint: listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(observer);
    }

    /**
     * TODO 4: Unsubscribe an observer from an event type
     *
     * @param eventType the event to stop listening for
     * @param observer the observer to remove
     */
    public void unsubscribe(String eventType, NotificationObserver observer) {
        // TODO: Get the list for this event type and remove the observer
    }

    /**
     * TODO 5: Notify all observers of a specific event
     * Loop through all observers registered for this eventType and call onEvent().
     *
     * @param eventType the event that occurred
     * @param data information about the event
     */
    public void notify(String eventType, String data) {
        // TODO: Get the list of observers for this event type
        // If the list exists, loop through and call onEvent() on each observer
        // Print: "Event fired: [eventType] with data: [data]"
    }
}

