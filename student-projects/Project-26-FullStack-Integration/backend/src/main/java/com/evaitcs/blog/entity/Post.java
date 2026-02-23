package com.evaitcs.blog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * ============================================================================
 * ENTITY: Post — Blog post with JPA mapping
 * TOPIC: Full-stack entity — this maps to DB AND is sent as JSON to React
 * ============================================================================
 */
// @Entity
// @Table(name = "posts")
public class Post {

    // @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotBlank @Size(min = 5, max = 200)
    private String title;

    // @NotBlank @Column(columnDefinition = "TEXT")
    private String content;

    // @NotBlank
    private String author;

    private String category;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // TODO 1: Add @PrePersist to auto-set createdAt
    // TODO 2: Add @PreUpdate to auto-set updatedAt
    // TODO 3: Create constructors, getters, setters
}

