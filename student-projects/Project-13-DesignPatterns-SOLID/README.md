# Project 13: Design Patterns & SOLID - "BuildMyNotificationSystem"

## 🎯 Objective
Build a **Notification System** that demonstrates key Design Patterns and
SOLID Principles. You'll implement Singleton, Factory, Observer, and Strategy
patterns — the most commonly asked patterns in technical interviews!

## 📚 Topics Covered (from Study Material)
- SOLID Principles (SRP, OCP, LSP, ISP, DIP)
- Singleton Pattern (one instance)
- Factory Method Pattern (object creation)
- Observer Pattern (event-driven notifications)
- Strategy Pattern (interchangeable algorithms)

## 📁 Project Structure
```
Project-13-DesignPatterns-SOLID/
└── src/main/java/com/evaitcs/designpatterns/
    ├── NotificationService.java       ← Singleton Pattern
    ├── NotificationFactory.java       ← Factory Pattern
    ├── Notification.java              ← Base interface (OCP, ISP)
    ├── EmailNotification.java         ← Concrete implementation
    ├── SmsNotification.java           ← Concrete implementation
    ├── PushNotification.java          ← Concrete implementation
    ├── NotificationObserver.java      ← Observer interface
    ├── EventManager.java              ← Observer Pattern (Subject)
    ├── NotificationStrategy.java      ← Strategy Pattern interface
    ├── UrgentStrategy.java            ← Strategy implementation
    ├── NormalStrategy.java            ← Strategy implementation
    └── NotificationApp.java           ← Main application
```

