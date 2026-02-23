# Collection Framework

# Java Collections Framework Overview

The Java Collections Framework is a unified architecture for representing and manipulating collections of objects. It contains interfaces, implementations, and algorithms to process data efficiently.

## Core Interfaces

The framework is built around several key interfaces:

### 1. List Interface

# List Interface

Lists maintain insertion order and allow duplicate elements.

## Key Implementations

- **ArrayList:** Dynamic array implementation, best for random access
- **LinkedList:** Doubly-linked list implementation, efficient for insertions/deletions

## Common Operations

```java
List<String> list = new ArrayList<>();
list.add("Element");        // Add element
list.get(0);               // Get element at index
list.remove(0);            // Remove element at index
list.contains("Element");  // Check if element exists
```

[List Interface](Collection%20Framework/List%20Interface%20184408cc99df80fabbddd3c6362a038b.md)

### 2. Set Interface

# Set Interface

Sets ensure unique elements and typically don't maintain insertion order.

## Key Implementations

- **HashSet:** Uses hash table, offers constant-time performance
- **TreeSet:** Maintains sorted order using Red-Black tree
- **LinkedHashSet:** Hash table with linked list, maintains insertion order

## Common Operations

```java
Set<Integer> set = new HashSet<>();
set.add(1);              // Add element
set.remove(1);           // Remove element
set.contains(1);         // Check if element exists
```

[Set Interface](Collection%20Framework/Set%20Interface%20184408cc99df804f83aac3ad99859785.md)

### 3. Queue Interface

# Queue Interface

Queues typically order elements in FIFO (first-in-first-out) manner.

## Key Implementations

- **LinkedList:** Also implements Queue interface
- **PriorityQueue:** Elements ordered by natural order or custom Comparator

## Common Operations

```java
Queue<String> queue = new LinkedList<>();
queue.offer("Element");  // Add element
queue.poll();           // Remove and return head
queue.peek();           // View head without removing
```

### 4. Map Interface

# Map Interface

Maps store key-value pairs, where each key must be unique.

## Key Implementations

- **HashMap:** General-purpose implementation using hash table
- **TreeMap:** Sorted map implementation using Red-Black tree
- **LinkedHashMap:** Hash table with linked list, maintains insertion order

## Common Operations

```java
Map<String, Integer> map = new HashMap<>();
map.put("Key", 1);      // Add key-value pair
map.get("Key");         // Get value for key
map.remove("Key");      // Remove entry
map.containsKey("Key"); // Check if key exists
```

[Map Interface](Collection%20Framework/Map%20Interface%20188408cc99df80e2b42af2b736bf188b.md)

## Performance Considerations

| Implementation | Access | Insert/Delete |
| --- | --- | --- |
| ArrayList | O(1) | O(n) |
| LinkedList | O(n) | O(1) |
| HashSet/HashMap | O(1) | O(1) |
| TreeSet/TreeMap | O(log n) | O(log n) |

## Common Utility Methods

```java
// Sorting
Collections.sort(list);

// Finding elements
Collections.max(collection);
Collections.min(collection);

// Modifying collections
Collections.reverse(list);
Collections.shuffle(list);

// Creating unmodifiable collections
Collections.unmodifiableList(list);
```