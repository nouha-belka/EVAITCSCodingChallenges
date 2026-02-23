package com.evaitcs.jobboard.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

/**
 * ============================================================================
 * ENTITY: User — Supports multiple roles (APPLICANT, RECRUITER, ADMIN)
 * ============================================================================
 */
public class User {

    // TODO 1: @Id, @GeneratedValue
    private Long id;

    // @NotBlank @Column(unique = true)
    private String username;

    // @NotBlank @Email @Column(unique = true)
    private String email;

    // @NotBlank
    private String password; // BCrypt-hashed!

    private String firstName;
    private String lastName;

    // TODO 2: Add role field
    // Roles: "ROLE_APPLICANT", "ROLE_RECRUITER", "ROLE_ADMIN"
    private String role = "ROLE_APPLICANT";

    private boolean enabled = true;

    // TODO 3: Constructors, getters, setters
    // TODO 4: Helper method: getFullName()
}

