package com.evaitcs.studentdb.repository;

import com.evaitcs.studentdb.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * ============================================================================
 * INTERFACE: StudentRepository
 * TOPIC: Spring Data JPA — ZERO implementation code for full CRUD!
 * ============================================================================
 *
 * JpaRepository<Student, Long> gives you ALL of these for FREE:
 *   save(entity)         — INSERT or UPDATE
 *   findById(id)         — SELECT by primary key
 *   findAll()            — SELECT all rows
 *   deleteById(id)       — DELETE by primary key
 *   count()              — COUNT rows
 *   existsById(id)       — Check existence
 *
 * QUERY DERIVATION:
 * Spring generates SQL from METHOD NAMES! No SQL needed.
 *   findByEmail(email) → SELECT * FROM students WHERE email = ?
 *   findByLastName(name) → SELECT * FROM students WHERE last_name = ?
 *
 * INTERVIEW TIP:
 * "Spring Data JPA derives queries from method names. For complex queries,
 *  I use @Query with JPQL or native SQL. The repository interface needs
 *  ZERO implementation — Spring generates it at runtime."
 * ============================================================================
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // TODO 1: Derived query — find by email
    // Optional<Student> findByEmail(String email);

    // TODO 2: Derived query — find by last name (case-insensitive)
    // List<Student> findByLastNameIgnoreCase(String lastName);

    // TODO 3: Derived query — find students older than a given age
    // List<Student> findByAgeGreaterThan(int age);

    // TODO 4: Derived query — find by first name containing a string
    // List<Student> findByFirstNameContainingIgnoreCase(String name);

    // TODO 5: Custom JPQL query — find students enrolled in a specific course
    // @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.courseCode = :code")
    // List<Student> findByCourseCode(@Param("code") String courseCode);

    // TODO 6: Custom JPQL query — count students per course
    // @Query("SELECT c.courseName, COUNT(s) FROM Student s JOIN s.courses c GROUP BY c.courseName")
    // List<Object[]> countStudentsPerCourse();
}

