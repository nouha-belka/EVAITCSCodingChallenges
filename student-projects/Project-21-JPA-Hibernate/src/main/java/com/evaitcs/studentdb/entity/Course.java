package com.evaitcs.studentdb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * ENTITY: Course
 * TOPIC: JPA Relationships — @ManyToMany inverse side
 * ============================================================================
 *
 * 'mappedBy' tells JPA: "The Student entity OWNS this relationship.
 * Don't create a second join table — use the one defined in Student."
 * ============================================================================
 */

// TODO 1: Add @Entity and @Table(name = "courses")
public class Course {

    // TODO 2: Add @Id and @GeneratedValue
    private Long id;

    // @Column(nullable = false)
    // @NotBlank
    private String courseName;

    // @Column(nullable = false, unique = true)
    private String courseCode;

    private String instructor;

    // @Min(1) @Max(500)
    private int maxCapacity;

    // TODO 3: Add @ManyToMany inverse side
    // @ManyToMany(mappedBy = "courses")
    // private List<Student> students = new ArrayList<>();

    // TODO 4: Constructors, getters, setters, toString (no students in toString!)
}

