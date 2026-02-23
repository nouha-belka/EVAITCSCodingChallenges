# MultiThreading

# Understanding Multithreading in Java

Multithreading is like managing multiple tasks simultaneously - imagine cooking different dishes in your kitchen at the same time instead of one after another.

## 1. Creating Threads - Different Approaches

### A. Extending Thread Class (Basic Approach)

When extending the Thread class, you create a custom class that inherits all Thread capabilities. This is the most straightforward but least flexible approach.

```java
// Brute Force Approach
class CookingThread extends Thread {
    public void run() {
        System.out.println("Cooking pasta in thread: " + Thread.currentThread().getName());
    }
}

// Usage
CookingThread thread = new CookingThread();
thread.start();  // Don't use thread.run() - common mistake!
```

In this basic example, we create a simple thread that simulates cooking pasta. The run() method contains the code that will be executed in the separate thread.

```java
// Best Practice
class CookingThread extends Thread {
    private final String dish;  // Store the dish name as a field
    
    public CookingThread(String dish) {
        this.dish = dish;
        setName("Cooking-" + dish);  // Give thread a meaningful name for debugging
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Cooking " + dish + " in thread: " + getName());
            // Add proper exception handling
        } catch (Exception e) {
            Thread.currentThread().interrupt();  // Properly handle thread interruption
            throw new RuntimeException("Cooking failed", e);
        }
    }
}
```

The improved version includes:

- Proper constructor to initialize thread state
- Meaningful thread naming for better debugging
- Exception handling to manage errors
- Thread interruption handling

### B. Implementing Runnable Interface (Preferred)

The Runnable interface provides better flexibility as it doesn't consume your single inheritance option. It's the recommended approach for most cases.

```java
// Brute Force
class DishWashing implements Runnable {
    public void run() {
        System.out.println("Washing dishes in thread: " + Thread.currentThread().getName());
    }
}

// Usage
Thread thread = new Thread(new DishWashing());
thread.start();
```

This basic implementation shows how to create a thread using Runnable. The task is wrapped in a Thread object before starting.

```java
// Best Practice
class DishWashing implements Runnable {
    private final String sinkName;
    private volatile boolean isRunning = true;  // Thread-safe flag for shutdown
    
    public DishWashing(String sinkName) {
        this.sinkName = sinkName;
    }
    
    public void stop() {
        isRunning = false;  // Graceful shutdown method
    }
    
    @Override
    public void run() {
        while (isRunning && !Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("Washing dishes in " + sinkName);
                Thread.sleep(1000);  // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
```

The improved Runnable implementation includes:

- Volatile flag for thread-safe state changes
- Graceful shutdown mechanism
- Proper interrupt handling
- Simulation of actual work with sleep()

### C. Using Callable (When You Need Return Values)

Callable is similar to Runnable but can return values and throw checked exceptions.

```java
// Brute Force
class BakingTask implements Callable<String> {
    public String call() {
        return "Cake is ready!";
    }
}

// Usage
ExecutorService executor = Executors.newSingleThreadExecutor();
Future<String> future = executor.submit(new BakingTask());
```

This simple example shows how to create a task that returns a value.

```java
// Best Practice
class BakingTask implements Callable<BakingResult> {
    private final String recipe;
    
    public BakingTask(String recipe) {
        this.recipe = recipe;
    }
    
    @Override
    public BakingResult call() throws BakingException {
        try {
            if (recipe == null) {
                throw new BakingException("Recipe cannot be null");
            }
            return new BakingResult(recipe, "success");
        } catch (Exception e) {
            throw new BakingException("Baking failed", e);
        }
    }
}
```

The improved Callable implementation includes:

- Custom return type (BakingResult)
- Proper parameter validation
- Custom exception handling
- Structured result object

## 2. Using ExecutorService (Modern Approach)

ExecutorService provides a higher-level replacement for working with threads directly, managing thread pools and task execution.

```java
// Brute Force
ExecutorService executor = Executors.newFixedThreadPool(3);
executor.submit(() -> System.out.println("Task executed"));
```

This basic example creates a thread pool with 3 threads and submits a simple task.

```java
// Best Practice
public class RestaurantService {
    private final ExecutorService executor;
    
    public RestaurantService(int threadPoolSize) {
        this.executor = Executors.newFixedThreadPool(threadPoolSize, 
            new ThreadFactoryBuilder()
                .setNameFormat("restaurant-worker-%d")
                .setUncaughtExceptionHandler((t, e) -> 
                    logger.error("Thread {} failed", t.getName(), e))
                .build());
    }
    
    public Future<Order> processOrder(Order order) {
        return executor.submit(() -> {
            try {
                return order.process();
            } catch (Exception e) {
                logger.error("Order processing failed", e);
                throw e;
            }
        });
    }
    
    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
```

The improved ExecutorService implementation includes:

- Custom thread factory with naming pattern
- Proper exception handling at thread level
- Graceful shutdown procedure
- Timeout handling for shutdown

## 5. Thread Synchronization Example

```java
public class Restaurant {
    private final Object orderLock = new Object();  // Dedicated lock object
    private volatile boolean isOpen = true;  // Thread-safe flag
    
    public void processOrder(Order order) {
        synchronized(orderLock) {  // Thread-safe critical section
            if (!isOpen) {
                throw new IllegalStateException("Restaurant is closed");
            }
            // Process order safely
        }
    }
    
    public void close() {
        synchronized(orderLock) {
            isOpen = false;
        }
    }
}
```

This synchronization example demonstrates:

- Use of a dedicated lock object for synchronization
- Volatile keyword for thread-safe flag access
- Synchronized blocks to protect critical sections
- Proper state checking in synchronized methods

## Key Threading Best Practices

- Always use meaningful thread names for easier debugging
- Implement proper shutdown procedures to avoid resource leaks
- Use appropriate pool sizes based on your application's needs
- Handle exceptions properly in worker threads
- Use thread-safe collections when sharing data between threads
- Never ignore InterruptedException - either handle it or propagate it
- Avoid sharing mutable state between threads when possible
- Use the volatile keyword for flags that control thread behavior

Remember: Threading adds complexity to your application. Only use multiple threads when parallel execution provides clear benefits to your application's performance or architecture.

[Multithreading vs Multiprogramming vs Multiprocessing vs Multitasking](MultiThreading/Multithreading%20vs%20Multiprogramming%20vs%20Multiprocess%20188408cc99df8034822edbdad889549f.md)

[volatile and transient keywords](MultiThreading/volatile%20and%20transient%20keywords%20188408cc99df803e8f14d395721ce70b.md)