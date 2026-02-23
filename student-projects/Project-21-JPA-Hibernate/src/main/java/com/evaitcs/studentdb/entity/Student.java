package com.evaitcs.studentdb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * ENTITY: Student
 * TOPIC: JPA Entity Mapping — This class maps to a database TABLE
 * ============================================================================
 *
 * @Entity tells JPA: "This class represents a database table."
 * Each instance = one ROW in the table.
 * Each field = one COLUMN in the table.
 *
 * JPA ANNOTATIONS:
 *   @Entity     — marks as a JPA entity (maps to a table)
 *   @Table      — customize table name
 *   @Id         — marks the primary key
 *   @GeneratedValue — auto-generate IDs
 *   @Column     — customize column name, constraints
 *
 * RELATIONSHIP: One Student can enroll in MANY Courses (ManyToMany)
 *
 * INTERVIEW TIP:
 * "I use JPA entities to map Java objects to database tables. @Entity
 *  with @Id defines the primary key. I use @ManyToMany with a join table
 *  for many-to-many relationships like student enrollments."
 * ============================================================================
 */

// TODO 1: Add @Entity and @Table(name = "students") annotations
// @Entity
// @Table(name = "students")
public class Student {

    // TODO 2: Add @Id and @GeneratedValue for auto-incrementing primary key
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO 3: Add @Column with constraints
    // @Column(nullable = false, length = 50)
    // @NotBlank(message = "First name is required")
    private String firstName;

    // @Column(nullable = false, length = 50)
    // @NotBlank
    private String lastName;

    // @Column(nullable = false, unique = true)
    // @Email(message = "Must be a valid email")
    private String email;

    // @Column(nullable = false)
    // @Min(value = 16, message = "Must be at least 16")
    private int age;

    // TODO 4: Add @ManyToMany relationship with Course
    // A student can enroll in MANY courses, a course can have MANY students.
    // JPA creates a JOIN TABLE (student_courses) automatically.
    //
    // @ManyToMany
    // @JoinTable(
    //     name = "student_courses",
    //     joinColumns = @JoinColumn(name = "student_id"),
    //     inverseJoinColumns = @JoinColumn(name = "course_id")
    // )
    // private List<Course> courses = new ArrayList<>();

    // TODO 5: Create constructors (no-arg required by JPA!), getters, setters
    // TODO 6: Create a helper method: enrollInCourse(Course course)
    // TODO 7: Override toString() — do NOT include the courses list (infinite recursion!)
}

