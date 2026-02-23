# Map Interface

# Understanding the Map Interface in Java

A Map in Java is a collection that stores key-value pairs, where each key must be unique but values can be duplicated. It's not technically a Collection (doesn't extend Collection interface) but is part of the Collections Framework.

## Internal Working Mechanism

The most commonly used implementation, HashMap, works through the following process:

### 1. Hashing Process

- **Key Hashing:** When putting an element, the key's hashCode() method is called
- **Index Calculation:** The hash is transformed using (n-1) & hash, where n is the current capacity
- **Bucket Location:** The resulting index determines which bucket (linked list or tree) will store the entry

### 2. Internal Structure

- **Array of Nodes:** HashMap uses an array of Node objects, each containing hash, key, value, and next references
- **Load Factor:** Default is 0.75, determines when the backing array should be resized
- **Tree Conversion:** Buckets convert to Red-Black trees when containing 8+ elements

## Core Methods and Complexity

```java
// Basic Operations
put(K key, V value)     // O(1) average case
get(Object key)         // O(1) average case
remove(Object key)      // O(1) average case
containsKey(Object key) // O(1) average case
containsValue(Object value) // O(n)

// Bulk Operations
putAll(Map m)           // O(n)
clear()                 // O(n)

// Collection Views
keySet()               // Returns Set of keys
values()               // Returns Collection of values
entrySet()             // Returns Set of key-value pairs
```

## Common Implementation Types

- **HashMap:** Unordered, allows null keys and values, O(1) operations
- **LinkedHashMap:** Maintains insertion order, slightly higher memory overhead
- **TreeMap:** Sorted by natural order or comparator, O(log n) operations
- **ConcurrentHashMap:** Thread-safe version, no null keys/values allowed

## Best Practices

- **Override equals() and hashCode():** Always implement both methods for custom key objects
- **Initial Capacity:** Set initial capacity if size is known to avoid rehashing
- **Immutable Keys:** Use immutable objects as keys to prevent hash collisions
- **Thread Safety:** Use ConcurrentHashMap for multi-threaded applications

## Example Usage

```java
Map<String, Integer> map = new HashMap<>();

// Basic operations
map.put("One", 1);
map.get("One");           // Returns 1
map.getOrDefault("Two", 0); // Returns 0 if key not found

// Java 8+ operations
map.computeIfAbsent("Two", k -> 2);    // Compute if key absent
map.merge("One", 1, Integer::sum);      // Merge values
map.forEach((k, v) -> System.out.println(k + ": " + v));
```

## Performance Considerations

| Operation | Average Case | Worst Case |
| --- | --- | --- |
| get/put/remove | O(1) | O(n) |
| containsKey | O(1) | O(n) |
| containsValue | O(n) | O(n) |
| iteration | O(n) | O(n) |

[Java HashMap and ConcurrentHashMap: A Comprehensive Guide](Map%20Interface/Java%20HashMap%20and%20ConcurrentHashMap%20A%20Comprehensive%20190408cc99df8042b072d6d5c7d0f9f2.md)