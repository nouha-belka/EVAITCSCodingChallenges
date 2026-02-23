# Garbage Collection

# Advanced Guide to Java Garbage Collection Internals

## 1. Memory Management Architecture

### Heap Memory Structure

The JVM heap is divided into several distinct regions:

```
+------------------+
|  Young Generation|
|  +------------+ |
|  |   Eden     | |
|  +------------+ |
|  +-----++-----+ |
|  | S0  || S1  | |
|  +-----++-----+ |
+------------------+
|  Old Generation  |
|                  |
+------------------+
```

### Detailed Memory Spaces

- **Eden Space:**
    - Initial allocation point for most objects
    - Size controlled by -Xmn flag
    - When full, triggers Minor GC
- **Survivor Spaces (S0/S1):**
    - Hold objects that survive Eden collection
    - Objects are copied between spaces
    - Age counter tracks survival cycles
    - Default threshold controlled by -XX:MaxTenuringThreshold
- **Old Generation:**
    - Houses long-lived objects
    - Triggered by -XX:MaxTenuringThreshold
    - Cleaned by Major GC/Full GC

## 2. Reference Types Deep Dive

```java
// Strong Reference
Object obj = new Object();

// Soft Reference
SoftReference<Object> soft = new SoftReference<>(obj);

// Weak Reference
WeakReference<Object> weak = new WeakReference<>(obj);

// Phantom Reference
ReferenceQueue<Object> refQueue = new ReferenceQueue<>();
PhantomReference<Object> phantom = new PhantomReference<>(obj, refQueue);
```

### Reference Strength Hierarchy

- **Strong References:**
    - Default reference type
    - Prevents garbage collection
    - Only cleared when explicitly set to null
- **Soft References:**
    - Memory-sensitive references
    - Cleared before OutOfMemoryError
    - Ideal for caching
- **Weak References:**
    - Collected in next GC cycle
    - Used in WeakHashMap
    - Good for canonical mapping
- **Phantom References:**
    - Cannot be dereferenced
    - Used for cleanup activities
    - Must be used with ReferenceQueue

## 3. Garbage Collectors In-Depth Analysis

### G1 Garbage Collector (Default in Java 17)

G1 divides heap into regions:

```
Region Types:
├── Eden
├── Survivor
├── Old
└── Humongous
```

- **G1 Collection Phases:**
    - Young-Only Phase
        - Concurrent Mark Phase
        - Mixed Collection Phase
        - Full GC (fallback)

### ZGC (Z Garbage Collector)

- **Key Features:**
    - Concurrent processing
    - Sub-millisecond pause times
    - Scales from 8MB to 16TB heap

### Parallel Collector

- **Characteristics:**
    - Multiple threads for collection
    - Stop-the-world events
    - Focuses on throughput

## 4. GC Tuning Parameters

```
Common JVM Flags:
-XX:+UseG1GC                    // Use G1 collector
-XX:MaxGCPauseMillis=200       // Target pause time
-XX:InitialHeapSize=32g        // Initial heap size
-XX:MaxHeapSize=32g            // Maximum heap size
-XX:ParallelGCThreads=4        // Parallel GC threads
-XX:ConcGCThreads=2            // Concurrent GC threads
```

## 5. Memory Leaks and Prevention

```java
// Common memory leak pattern
public class CacheExample {
    private static final Map<Key, Value> cache = new HashMap<>();
    
    public void addToCache(Key key, Value value) {
        cache.put(key, value);  // Objects never removed!
    }
}

// Fixed version
public class FixedCacheExample {
    private static final Map<Key, Value> cache = 
        new WeakHashMap<>();  // Uses weak references
}
```

## 6. GC Monitoring and Analysis

- **Tools for GC Analysis:**
    - JConsole: Built-in monitoring
    - VisualVM: Detailed heap analysis
    - JMC (Java Mission Control): Advanced monitoring
    - GC logging: Detailed GC events

### GC Log Analysis

```
[2023-01-27T10:37:00.123+0000] GC(36) Pause Young (Normal) (G1 Evacuation Pause)
[2023-01-27T10:37:00.124+0000] GC(36) User=0.01s Sys=0.00s Real=0.01s
```

## 7. Performance Impact Considerations

- **GC Overhead Factors:**
    - Object allocation rate
    - Object lifetime distribution
    - Heap size and fragmentation
    - Application threads vs GC threads

## 8. Best Practices for OCP Exam

- **Memory Management:**
    - Close resources properly (try-with-resources)
    - Clear object references when no longer needed
    - Use appropriate collection types
    - Understand object lifecycle

```java
// Example of proper resource management
try (FileInputStream fis = new FileInputStream("file.txt")) {
    // Use the resource
} catch (IOException e) {
    // Handle exception
} // Resource automatically closed
```

## 9. Advanced Scenarios

- How does GC handle circular references?
    
    ```java
    class Node {
        Node next;
        Node prev;
        
        public static void main(String[] args) {
            Node n1 = new Node();
            Node n2 = new Node();
            n1.next = n2;
            n2.prev = n1;
            n1 = n2 = null; // Both eligible for GC
        }
    }
    ```
    
- What happens during concurrent marking?
    
    Concurrent marking traces object graph while application runs:
    1. Initial Mark (STW)
    2. Concurrent Mark
    3. Remark (STW)
    4. Cleanup
    This minimizes application pause times.
    

<aside>
Remember: For the OCP exam, focus on understanding the principles rather than implementation details. Know how different collectors work, when objects become eligible for collection, and how reference types affect GC behavior.

</aside>