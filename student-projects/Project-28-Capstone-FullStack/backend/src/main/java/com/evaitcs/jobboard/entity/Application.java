package com.evaitcs.jobboard.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * ============================================================================
 * ENTITY: Application — Tracks job applications
 * ============================================================================
 *
 * STATUS FLOW:
 *   PENDING → REVIEWED → INTERVIEW → OFFER
 *                     → REJECTED (at any point)
 * ============================================================================
 */
public class Application {

    // TODO 1: @Id, @GeneratedValue
    private Long id;

    // TODO 2: @ManyToOne to Job
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "job_id")
    // private Job job;

    // TODO 3: @ManyToOne to User (the applicant)
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "applicant_id")
    // private User applicant;

    private String status = "PENDING"; // PENDING, REVIEWED, INTERVIEW, OFFER, REJECTED

    private String coverLetter;
    private String resumeUrl;
    private LocalDateTime appliedAt;
    private LocalDateTime updatedAt;

    // TODO 4: @PrePersist, constructors, getters, setters
}

