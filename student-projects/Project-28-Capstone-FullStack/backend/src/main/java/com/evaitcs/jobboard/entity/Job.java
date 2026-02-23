package com.evaitcs.jobboard.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * ============================================================================
 * ENTITY: Job — Core entity for the Job Board
 * ============================================================================
 *
 * RELATIONSHIPS:
 *   Job → Company:     @ManyToOne (many jobs belong to one company)
 *   Job → Application: @OneToMany (one job has many applications)
 *   Job → User:        posted by a user (recruiter)
 * ============================================================================
 */

// TODO 1: Add @Entity, @Table(name = "jobs")
public class Job {

    // TODO 2: Add @Id, @GeneratedValue
    private Long id;

    // TODO 3: Add validation annotations
    // @NotBlank @Size(min = 5, max = 200)
    private String title;

    // @NotBlank @Column(columnDefinition = "TEXT")
    private String description;

    // @NotBlank
    private String location;

    // @NotNull @Positive
    private Double salaryMin;

    private Double salaryMax;

    // @NotBlank
    private String category; // e.g., "Engineering", "Marketing", "Sales"

    private String employmentType; // "FULL_TIME", "PART_TIME", "CONTRACT", "REMOTE"

    private String experienceLevel; // "ENTRY", "MID", "SENIOR", "LEAD"

    private boolean isActive = true;

    private LocalDateTime postedAt;
    private LocalDateTime expiresAt;

    // TODO 4: Add @ManyToOne relationship to Company
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "company_id")
    // private Company company;

    // TODO 5: Add @ManyToOne relationship to User (the recruiter who posted)
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "posted_by")
    // private User postedBy;

    // TODO 6: Add @OneToMany relationship to Application
    // @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    // private List<Application> applications = new ArrayList<>();

    // TODO 7: Add @PrePersist to set postedAt automatically
    // TODO 8: Constructors, getters, setters
}

