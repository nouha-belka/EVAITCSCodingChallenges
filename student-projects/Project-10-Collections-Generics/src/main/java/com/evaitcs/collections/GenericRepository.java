package com.evaitcs.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ============================================================================
 * GENERIC CLASS: GenericRepository<T>
 * TOPIC: Generics — Writing type-safe, reusable code
 * ============================================================================
 *
 * WHAT ARE GENERICS?
 * Generics let you write ONE class that works with ANY type, while keeping
 * compile-time type safety. No more casting or ClassCastExceptions!
 *
 * Without generics: List list = new ArrayList(); // What type? Who knows!
 * With generics:    List<String> list = new ArrayList<>(); // String only!
 *
 * TYPE PARAMETERS:
 *   T - Type (general)
 *   E - Element (collections)
 *   K - Key (maps)
 *   V - Value (maps)
 *   N - Number
 *
 * INTERVIEW TIP:
 * "Generics provide compile-time type safety and code reusability.
 *  I use them to create flexible APIs that work with any type while
 *  preventing ClassCastExceptions at runtime."
 * ============================================================================
 */

// =========================================================================
// TODO 1: Declare the GenericRepository class with a type parameter <T>
// The T means "any type" — you can create GenericRepository<Product>,
// GenericRepository<String>, GenericRepository<Employee>, etc.
//
// Syntax: public class GenericRepository<T> { ... }
// =========================================================================
public class GenericRepository<T> {

    // TODO 2: Declare a private List<T> field called 'items'
    // This list will store objects of whatever type T is.


    // TODO 3: Initialize 'items' in a constructor
    // items = new ArrayList<>();


    // =========================================================================
    // CRUD METHODS — All work with type T
    // =========================================================================

    /**
     * TODO 4: Add an item to the repository
     *
     * @param item the item of type T to add
     */
    public void add(T item) {
        // TODO: Add the item to the list
        // Print: "Item added: " + item
    }

    /**
     * TODO 5: Get an item by index
     *
     * @param index the index to retrieve
     * @return the item at that index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public T getByIndex(int index) {
        // TODO: Check bounds, return items.get(index)

        return null; // Replace this line
    }

    /**
     * TODO 6: Remove an item
     *
     * @param item the item to remove
     * @return true if removed
     */
    public boolean remove(T item) {
        // TODO: Remove the item from the list

        return false; // Replace this line
    }

    /**
     * TODO 7: Get all items as an unmodifiable list
     *
     * @return all items
     */
    public List<T> getAll() {
        // TODO: Return Collections.unmodifiableList(items) or new ArrayList<>(items)

        return new ArrayList<>(); // Replace this line
    }

    /**
     * TODO 8: Get the count of items
     */
    public int size() {
        return 0; // Replace this line
    }

    /**
     * TODO 9: Check if the repository contains an item
     */
    public boolean contains(T item) {
        return false; // Replace this line
    }

    // =========================================================================
    // GENERIC METHOD — A static method with its OWN type parameter
    // =========================================================================

    /**
     * TODO 10: Create a static generic method that finds the max element
     * in a list, where the elements must be Comparable.
     *
     * This uses a BOUNDED TYPE PARAMETER: <E extends Comparable<E>>
     * The 'extends' here means E must implement Comparable.
     *
     * Syntax: public static <E extends Comparable<E>> E findMax(List<E> list)
     *
     * @param list the list to search (elements must be Comparable)
     * @return the maximum element, or null if list is empty
     */
    public static <E extends Comparable<E>> E findMax(List<E> list) {
        // TODO: Loop through the list and find the maximum element
        // Use compareTo(): if (item.compareTo(max) > 0) max = item;

        return null; // Replace this line
    }

    /**
     * TODO 11: Create a static generic method that filters a list
     * This method should accept a list and a predicate (condition),
     * and return only items that match.
     *
     * Since we can't use java.util.function.Predicate without explaining
     * functional interfaces yet, let's use a simple approach:
     *
     * Create a method that filters by class type using wildcards:
     *
     * public static <T> List<T> filterByType(List<?> list, Class<T> type)
     *
     * This uses a WILDCARD (?): List<?> means "list of unknown type"
     * Class<T> is used for type checking with instanceof
     *
     * @param list the list to filter
     * @param type the class type to filter by
     * @return a new list containing only elements of the specified type
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> filterByType(List<?> list, Class<T> type) {
        // TODO: Create a result list
        // Loop through the input list
        // If type.isInstance(item), cast and add to result
        // Return result

        return new ArrayList<>(); // Replace this line
    }

    // =========================================================================
    // MAIN METHOD — Test generics!
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=== Generics Demo ===\n");

        // TODO 12: Create a GenericRepository<String> and add some strings
        // GenericRepository<String> stringRepo = new GenericRepository<>();
        // stringRepo.add("Hello");
        // stringRepo.add("World");
        // System.out.println("String repo size: " + stringRepo.size());

        // TODO 13: Create a GenericRepository<Integer> and add some numbers
        // GenericRepository<Integer> intRepo = new GenericRepository<>();
        // intRepo.add(42);
        // intRepo.add(17);
        // intRepo.add(99);

        // TODO 14: Create a GenericRepository<Product> and add products
        // GenericRepository<Product> productRepo = new GenericRepository<>();
        // productRepo.add(new Product("P001", "Laptop", "Electronics", 999.99, 10));
        // productRepo.add(new Product("P002", "Mouse", "Electronics", 29.99, 50));

        // TODO 15: Test the findMax generic method
        // List<Integer> numbers = List.of(3, 7, 1, 9, 4);
        // System.out.println("Max: " + GenericRepository.findMax(numbers));  // 9

        // TODO 16: Notice: the compiler prevents type errors!
        // GenericRepository<String> repo = new GenericRepository<>();
        // repo.add("Hello");
        // repo.add(42);  // COMPILER ERROR! Can't add Integer to String repo!
        // This is the power of generics — errors caught at compile time!
    }
}

