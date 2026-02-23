# Structural Design Patterns in Java

## Understanding Structural Patterns

Structural patterns focus on how classes and objects are composed to form larger structures. Think of them as the blueprints for how different pieces of a software system fit together, much like how architects design buildings by combining various structural elements. These patterns help ensure that when we make changes to one part of our system, we don't need to change all other parts.

## Adapter Pattern

The Adapter pattern allows incompatible interfaces to work together by wrapping an object in an adapter to make it compatible with another class. It's similar to how a power adapter lets you plug a device from one country into an outlet in another country.

### Real-World Analogy

Imagine you're traveling internationally with a laptop. Your laptop charger has a US-style plug, but you're in Europe with different wall sockets. You need a power adapter to make your US plug work with European sockets. The adapter doesn't change how your laptop charger works internally; it just makes it compatible with a different interface.

### Implementation

```java
// The interface our code expects to work with
public interface MediaPlayer {
    void play(String audioType, String fileName);
}

// The interface we need to adapt to
public interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}

// Concrete implementation of the interface we're adapting
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // Do nothing
    }
}

public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // Do nothing
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file: " + fileName);
    }
}

// The adapter that makes everything work together
public class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedMusicPlayer;

    // Constructor determines which advanced player to use
    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer = new Mp4Player();
        }
    }

    // The adapter method that brings it all together
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}

```

## Bridge Pattern

The Bridge pattern decouples an abstraction from its implementation, allowing both to vary independently. Think of it as building a bridge between two independent systems that need to work together.

### Real-World Analogy

Consider a remote control (abstraction) and a TV (implementation). You can change the TV without changing how the remote control works, and you can upgrade the remote control without changing the TV. The bridge pattern creates this kind of flexible relationship in software.

### Implementation

```java
// Implementor interface
public interface Device {
    boolean isEnabled();
    void enable();
    void disable();
    int getVolume();
    void setVolume(int volume);
    int getChannel();
    void setChannel(int channel);
}

// Concrete Implementor
public class TV implements Device {
    private boolean on = false;
    private int volume = 30;
    private int channel = 1;

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
    }

    @Override
    public void disable() {
        on = false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        if (volume > 100) {
            this.volume = 100;
        } else if (volume < 0) {
            this.volume = 0;
        } else {
            this.volume = volume;
        }
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
    }
}

// Abstraction
public abstract class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public void togglePower() {
        if (device.isEnabled()) {
            device.disable();
        } else {
            device.enable();
        }
    }

    public void volumeDown() {
        device.setVolume(device.getVolume() - 10);
    }

    public void volumeUp() {
        device.setVolume(device.getVolume() + 10);
    }

    public void channelDown() {
        device.setChannel(device.getChannel() - 1);
    }

    public void channelUp() {
        device.setChannel(device.getChannel() + 1);
    }
}

// Refined Abstraction
public class AdvancedRemoteControl extends RemoteControl {
    public AdvancedRemoteControl(Device device) {
        super(device);
    }

    public void mute() {
        device.setVolume(0);
    }
}

```

## Composite Pattern

The Composite pattern lets you compose objects into tree structures to represent part-whole hierarchies. It lets clients treat individual objects and compositions uniformly.

### Real-World Analogy

Think of an organizational chart in a company. Each element can be either an individual employee or a department containing other employees and departments. Whether you're dealing with an individual or a department, you can perform the same operations like getting their cost to the company or sending communications.

### Implementation

```java
// Component interface
public interface Employee {
    void add(Employee employee);
    void remove(Employee employee);
    Employee getChild(int i);
    String getName();
    double getSalary();
    void print();
}

// Leaf class
public class Developer implements Employee {
    private String name;
    private double salary;

    public Developer(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public void add(Employee employee) {
        // Leaf node - operation not supported
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Employee employee) {
        // Leaf node - operation not supported
        throw new UnsupportedOperationException();
    }

    @Override
    public Employee getChild(int i) {
        // Leaf node - operation not supported
        throw new UnsupportedOperationException();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void print() {
        System.out.println("Developer [Name: " + name + ", Salary: " + salary + "]");
    }
}

// Composite class
public class Manager implements Employee {
    private String name;
    private double salary;
    private List<Employee> employees = new ArrayList<>();

    public Manager(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public void add(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void remove(Employee employee) {
        employees.remove(employee);
    }

    @Override
    public Employee getChild(int i) {
        return employees.get(i);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSalary() {
        // Calculate total salary including subordinates
        double totalSalary = salary;
        for (Employee employee : employees) {
            totalSalary += employee.getSalary();
        }
        return totalSalary;
    }

    @Override
    public void print() {
        System.out.println("Manager [Name: " + name + ", Salary: " + salary + "]");
        System.out.println("Employees reporting to " + name + ":");
        for (Employee employee : employees) {
            employee.print();
        }
    }
}

```

## Decorator Pattern

The Decorator pattern lets you attach new behaviors to objects by placing these objects inside wrapper objects that contain the behaviors. It's like wrapping a gift - each wrapper adds a new layer of functionality.

### Real-World Analogy

Think of ordering a coffee. You start with a basic coffee, but you can "decorate" it with extras like milk, sugar, whipped cream, or caramel. Each addition wraps the previous version and adds its own behavior (and cost).

### Implementation

```java
// Component interface
public interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete component
public class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double getCost() {
        return 1.0;
    }
}

// Decorator base class
public abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// Concrete decorators
public class Milk extends CoffeeDecorator {
    public Milk(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.5;
    }
}

public class WhippedCream extends CoffeeDecorator {
    public WhippedCream(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Whipped Cream";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.7;
    }
}

```

## Facade Pattern

The Facade pattern provides a simplified interface to a complex subsystem. It's like having a concierge at a hotel who provides a simple interface to the complex network of services the hotel offers.

### Real-World Analogy

When you order food for delivery, you interact with a simple app interface (the facade). Behind the scenes, there's a complex system involving restaurants, payment processing, GPS tracking, and delivery drivers, but you don't need to understand all of that complexity.

### Implementation

```java
// Complex subsystem classes
public class CPU {
    public void freeze() {
        System.out.println("CPU: Freezing...");
    }

    public void jump(long position) {
        System.out.println("CPU: Jumping to position " + position);
    }

    public void execute() {
        System.out.println("CPU: Executing...");
    }
}

public class Memory {
    public void load(long position, String data) {
        System.out.println("Memory: Loading data at position " + position);
    }
}

public class HardDrive {
    public String read(long lba, int size) {
        System.out.println("HardDrive: Reading data from sector " + lba);
        return "data";
    }
}

// Facade
public class ComputerFacade {
    private CPU processor;
    private Memory ram;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.processor = new CPU();
        this.ram = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void start() {
        processor.freeze();
        ram.load(0, hardDrive.read(0, 1024));
        processor.jump(0);
        processor.execute();
    }
}

```

## Best Practices and Implementation Tips

When working with structural patterns, keep these key principles in mind:

1. Pattern Selection: Choose patterns based on your specific needs and constraints. Don't force a pattern where it's not needed just because you're familiar with it.
2. Composition vs Inheritance: Many structural patterns favor composition over inheritance. This provides more flexibility and avoids the pitfalls of deep inheritance hierarchies.
3. Interface Segregation: Keep interfaces small and focused. This makes patterns like Adapter and Bridge more effective and maintainable.
4. Open/Closed Principle: Design your patterns so that you can add new functionality without modifying existing code. The Decorator pattern is particularly good at this.
5. Testing Considerations: Structural patterns can sometimes make unit testing more challenging. Consider using dependency injection and interfaces to make your code more testable.

Remember that patterns are tools to help solve problems, not goals in themselves. Always consider the specific context and requirements of your project when choosing and implementing patterns.

## Common Pitfalls to Avoid

1. Overcomplication: Don't use structural patterns when simpler solutions would suffice. Sometimes a straightforward inheritance hierarchy or simple composition is all you need.
2. Performance Impact: Be aware that some structural patterns, particularly Decorator and Composite, can impact performance when used extensively. Profile your application if performance is critical.
3. Pattern Interference: Be careful when using multiple structural patterns together. While they can work well in combination, they can also lead to unnecessarily complex code if not carefully managed.
4. Tight Coupling: Even when using structural patterns, be careful not to create tight coupling between components. The goal is to make your code more flexible, not less.

## Choosing the Right Pattern

To help you choose the right structural pattern, consider these questions:

1. Do you need to make incompatible interfaces work together? → Consider Adapter
2. Do you need to vary both abstractions and implementations independently? → Consider Bridge
3. Do you need to treat individual objects and compositions uniformly? → Consider Composite
4. Do you need to add behaviors dynamically? → Consider Decorator
5. Do you need to simplify a complex subsystem? → Consider Facade

Remember that patterns can be combined to solve more complex problems. For example, you might use a Facade to provide a simple interface to a subsystem, and then use an Adapter to make that Facade work with an existing system.