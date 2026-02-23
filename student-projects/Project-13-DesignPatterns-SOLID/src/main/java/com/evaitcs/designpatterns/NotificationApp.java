package com.evaitcs.designpatterns;

/**
 * ============================================================================
 * MAIN APPLICATION: NotificationApp
 * TOPIC: All Design Patterns and SOLID Principles working together
 * ============================================================================
 *
 * This app demonstrates:
 * 1. SINGLETON — NotificationService has one instance
 * 2. FACTORY — NotificationFactory creates the right notification type
 * 3. OBSERVER — EventManager notifies observers when events happen
 * 4. STRATEGY — UrgentStrategy vs NormalStrategy for delivery
 * 5. SOLID — All five principles in action throughout the code
 * ============================================================================
 */
public class NotificationApp {

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("   NOTIFICATION SYSTEM");
        System.out.println("============================================\n");

        // =====================================================================
        // DEMO 1: SINGLETON PATTERN
        // =====================================================================

        System.out.println("--- Singleton Pattern Demo ---\n");

        // TODO 1: Get the NotificationService singleton instance TWICE
        // NotificationService service1 = NotificationService.getInstance();
        // NotificationService service2 = NotificationService.getInstance();
        //
        // Prove they're the same object:
        // System.out.println("Same instance? " + (service1 == service2)); // true!

        // =====================================================================
        // DEMO 2: FACTORY PATTERN
        // =====================================================================

        System.out.println("\n--- Factory Pattern Demo ---\n");

        // TODO 2: Use the factory to create different notification types
        // Notification emailNotif = NotificationFactory.createNotification("EMAIL");
        // Notification smsNotif = NotificationFactory.createNotification("SMS");
        // Notification pushNotif = NotificationFactory.createNotification("PUSH");
        //
        // Notice: we don't use 'new' anywhere! The factory handles creation.
        // System.out.println("Created: " + emailNotif.getType());
        // System.out.println("Created: " + smsNotif.getType());
        // System.out.println("Created: " + pushNotif.getType());

        // TODO 3: Send notifications using the factory-created objects
        // emailNotif.send("alice@company.com", "Welcome to the team!");
        // smsNotif.send("555-1234", "Your code is 123456");
        // pushNotif.send("user123", "New message received");

        // =====================================================================
        // DEMO 3: SINGLETON + FACTORY (Combined)
        // =====================================================================

        System.out.println("\n--- Singleton + Factory Combined ---\n");

        // TODO 4: Use the NotificationService to send notifications
        // It uses Factory internally!
        // service1.sendNotification("EMAIL", "bob@company.com", "Meeting at 3pm");
        // service1.sendNotification("SMS", "555-5678", "Reminder: standup in 5 min");
        // service1.sendNotification("PUSH", "user456", "Build succeeded!");
        //
        // System.out.println("Total notifications sent: " + service1.getNotificationsSent());

        // =====================================================================
        // DEMO 4: OBSERVER PATTERN
        // =====================================================================

        System.out.println("\n--- Observer Pattern Demo ---\n");

        // TODO 5: Create an EventManager
        // EventManager eventManager = new EventManager();

        // TODO 6: Create observers (using anonymous classes or lambda expressions)
        // Observer 1: Sends email when a user registers
        // NotificationObserver emailOnRegister = (eventType, data) -> {
        //     System.out.println("  📧 Sending welcome email to: " + data);
        // };
        //
        // Observer 2: Sends SMS when an order is placed
        // NotificationObserver smsOnOrder = (eventType, data) -> {
        //     System.out.println("  📱 Sending SMS confirmation for order: " + data);
        // };
        //
        // Observer 3: Logs all events
        // NotificationObserver logger = (eventType, data) -> {
        //     System.out.println("  📋 LOG: Event=" + eventType + ", Data=" + data);
        // };

        // TODO 7: Subscribe observers to events
        // eventManager.subscribe("USER_REGISTERED", emailOnRegister);
        // eventManager.subscribe("USER_REGISTERED", logger);
        // eventManager.subscribe("ORDER_PLACED", smsOnOrder);
        // eventManager.subscribe("ORDER_PLACED", logger);

        // TODO 8: Fire events and watch observers react!
        // eventManager.notify("USER_REGISTERED", "alice@email.com");
        // System.out.println();
        // eventManager.notify("ORDER_PLACED", "Order #12345");

        // =====================================================================
        // DEMO 5: STRATEGY PATTERN
        // =====================================================================

        System.out.println("\n--- Strategy Pattern Demo ---\n");

        // TODO 9: Create different strategies
        // NotificationStrategy normalStrategy = new NormalStrategy();
        // NotificationStrategy urgentStrategy = new UrgentStrategy();

        // TODO 10: Use normal strategy for a regular notification
        // Notification email = NotificationFactory.createNotification("EMAIL");
        // normalStrategy.execute(email, "user@company.com", "Weekly newsletter");

        // TODO 11: Switch to urgent strategy for a critical notification
        // urgentStrategy.execute(email, "admin@company.com", "SERVER DOWN!");

        // NOTICE: Same notification object, different behavior — that's Strategy!

        // =====================================================================
        // DEMO 6: SOLID PRINCIPLES SUMMARY
        // =====================================================================

        System.out.println("\n--- SOLID Principles in This Project ---\n");

        System.out.println("S - Single Responsibility:");
        System.out.println("    Each notification class handles ONE channel only.\n");

        System.out.println("O - Open/Closed:");
        System.out.println("    Add new notifications (Slack, Teams) without changing existing code.\n");

        System.out.println("L - Liskov Substitution:");
        System.out.println("    Any Notification implementation can replace the interface.\n");

        System.out.println("I - Interface Segregation:");
        System.out.println("    Small, focused interfaces (Notification, NotificationStrategy).\n");

        System.out.println("D - Dependency Inversion:");
        System.out.println("    EventManager depends on NotificationObserver interface, not concrete classes.\n");

        System.out.println("============================================");
        System.out.println("   NOTIFICATION SYSTEM DEMO COMPLETE");
        System.out.println("============================================");
    }
}

