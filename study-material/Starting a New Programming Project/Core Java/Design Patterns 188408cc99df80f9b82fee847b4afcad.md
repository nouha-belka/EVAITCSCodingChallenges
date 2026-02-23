# Design Patterns

# Understanding Design Patterns

Design patterns are reusable solutions to common problems in software design. They provide a template for solving issues that can occur repeatedly in software development, helping to create more maintainable and flexible code.

## Types of Design Patterns

Design patterns are typically categorized into three main types:

### 1. Creational Patterns

These patterns deal with object creation mechanisms, trying to create objects in a manner suitable to the situation.

- **Singleton Pattern:** Ensures a class has only one instance and provides a global point of access to it.
    
    ```java
    public class Singleton {
        private static Singleton instance;
        
        private Singleton() {}
        
        public static Singleton getInstance() {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }
    ```
    
- **Factory Method:** Creates objects without explicitly specifying their exact classes
- **Abstract Factory:** Creates families of related objects
- **Builder:** Constructs complex objects step by step
- **Prototype:** Creates new objects by cloning an existing object

### 2. Structural Patterns

These patterns deal with object composition and typically identify simple ways to realize relationships between entities.

- **Adapter Pattern:** Allows incompatible interfaces to work together
    
    ```java
    public interface MediaPlayer {
        void play(String audioType, String fileName);
    }
    
    public class AdvancedMediaPlayer {
        void playVlc(String fileName) {
            System.out.println("Playing vlc file: " + fileName);
        }
    }
    
    public class MediaAdapter implements MediaPlayer {
        AdvancedMediaPlayer advancedMusicPlayer;
    
        public MediaAdapter(String audioType) {
            if(audioType.equalsIgnoreCase("vlc")) {
                advancedMusicPlayer = new AdvancedMediaPlayer();
            }
        }
    
        @Override
        public void play(String audioType, String fileName) {
            if(audioType.equalsIgnoreCase("vlc")) {
                advancedMusicPlayer.playVlc(fileName);
            }
        }
    }
    ```
    
- **Bridge:** Separates an object's interface from its implementation
- **Composite:** Composes objects into tree structures
- **Decorator:** Adds responsibilities to objects dynamically
- **Facade:** Provides a unified interface to a set of interfaces

### 3. Behavioral Patterns

These patterns are concerned with communication between objects, how objects interact and distribute responsibility.

- **Observer Pattern:** Defines a one-to-many dependency between objects
    
    ```java
    public interface Observer {
        void update(String message);
    }
    
    public class Subject {
        private List<Observer> observers = new ArrayList<>();
        
        public void attach(Observer observer) {
            observers.add(observer);
        }
        
        public void notifyObservers(String message) {
            for (Observer observer : observers) {
                observer.update(message);
            }
        }
    }
    ```
    
- **Strategy:** Defines a family of algorithms and makes them interchangeable
- **Command:** Encapsulates a request as an object
- **State:** Allows an object to alter its behavior when its internal state changes
- **Template Method:** Defines the skeleton of an algorithm in a method

## When to Use Design Patterns

- Use when you need proven solutions to common problems
- When you want to make your code more flexible and reusable
- To communicate patterns and solutions with other developers
- When you need to reduce complexity in your code

## Best Practices

- Don't force patterns where they're not needed
- Understand the problem before applying a pattern
- Consider the maintenance implications
- Keep it simple - patterns should reduce complexity, not add to it

Remember that design patterns are guidelines, not rules. They should be adapted to fit your specific needs while maintaining their core principles.