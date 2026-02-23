# List Interface

# Understanding Java Lists

A List in Java is an ordered collection that allows duplicate elements. It maintains insertion order and provides positional access to elements.

## List Implementations

### ArrayList

ArrayList is a resizable array implementation of the List interface.

- **Advantages:**
    - Fast random access operations - O(1)
    - Memory-efficient for storing and accessing elements
    - Good for scenarios where you need frequent access by index
- **Disadvantages:**
    - Slow insertions/deletions in the middle - O(n)
    - Resizing operations can be costly

### LinkedList

LinkedList is a doubly-linked list implementation of the List interface.

- **Advantages:**
    - Fast insertions/deletions at any position - O(1)
    - No resizing overhead
    - Efficient for adding/removing elements frequently
- **Disadvantages:**
    - Slow random access - O(n)
    - Uses more memory due to storage of node references

## Performance Comparison

| Operation | ArrayList | LinkedList |
| --- | --- | --- |
| Add/Remove at end | O(1)* | O(1) |
| Add/Remove at middle | O(n) | O(1)** |
| Get by index | O(1) | O(n) |
| Search element | O(n) | O(n) |
- Amortized O(1) for ArrayList when no resizing is needed

** O(1) for LinkedList after finding the position (which takes O(n))

## When to Use Each

- **Use ArrayList when:**
    - You need frequent random access to elements
    - Size of the list doesn't change much
    - You mainly add/remove elements at the end
- **Use LinkedList when:**
    - You frequently add/remove elements from the middle
    - You don't need random access
    - Memory overhead isn't a concern

## Example Usage

```java
// ArrayList example
List<String> arrayList = new ArrayList<>();
arrayList.add("First");          // O(1)
arrayList.add(1, "Middle");      // O(n)
String element = arrayList.get(1); // O(1)

// LinkedList example
List<String> linkedList = new LinkedList<>();
linkedList.add("First");         // O(1)
linkedList.add(1, "Middle");     // O(1) after finding position
String first = linkedList.getFirst(); // O(1)
```