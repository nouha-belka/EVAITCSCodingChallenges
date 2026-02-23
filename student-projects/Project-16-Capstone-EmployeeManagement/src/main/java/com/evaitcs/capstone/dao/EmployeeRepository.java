package com.evaitcs.capstone.dao;

import java.util.List;
import java.util.Optional;

/**
 * ============================================================================
 * INTERFACE: EmployeeRepository<T>
 * TOPICS: Generics, Abstraction (interface), Dependency Inversion (SOLID)
 * ============================================================================
 *
 * This is a GENERIC REPOSITORY interface. The <T> type parameter means
 * it can work with ANY entity type (Employee, Product, etc.).
 *
 * WHY AN INTERFACE?
 * - Today we use File I/O to store data (FileEmployeeRepository)
 * - Tomorrow we could switch to a database (JdbcEmployeeRepository)
 * - The service layer depends on THIS INTERFACE, not the implementation
 * - That's the Dependency Inversion Principle (DIP) in action!
 *
 * INTERVIEW TIP:
 * "I use a generic repository interface to decouple data access from
 *  business logic. This makes it easy to swap implementations — from
 *  file-based to database-based — without changing any service code."
 * ============================================================================
 */
public interface EmployeeRepository<T> {

    /**
     * TODO 1: Declare CRUD methods:
     *
     * void save(T entity);
     * Optional<T> findById(String id);
     * List<T> findAll();
     * void update(T entity);
     * boolean deleteById(String id);
     * boolean existsById(String id);
     * long count();
     */

    // YOUR METHOD DECLARATIONS HERE
}

