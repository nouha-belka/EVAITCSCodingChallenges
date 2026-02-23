package com.evaitcs.jobboard.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * ============================================================================
 * ENTITY: Company
 * ============================================================================
 */
public class Company {

    // TODO 1: @Id, @GeneratedValue
    private Long id;

    // @NotBlank
    private String name;

    private String industry;
    private String website;
    private String logoUrl;

    // @Column(columnDefinition = "TEXT")
    private String description;

    private String location;
    private String size; // "1-10", "11-50", "51-200", "201-500", "500+"

    // TODO 2: @OneToMany(mappedBy = "company") for jobs list
    // TODO 3: Constructors, getters, setters
}

