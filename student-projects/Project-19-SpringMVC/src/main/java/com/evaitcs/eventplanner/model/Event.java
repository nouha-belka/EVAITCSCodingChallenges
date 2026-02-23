package com.evaitcs.eventplanner.model;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * ============================================================================
 * CLASS: Event
 * TOPIC: Model class with Bean Validation annotations
 * ============================================================================
 *
 * Bean Validation annotations tell Spring HOW to validate form input:
 *   @NotBlank  — must not be null, empty, or whitespace
 *   @Size      — string length must be within range
 *   @Future    — date must be in the future
 *   @Min/@Max  — number must be within range
 *
 * When a form is submitted, Spring automatically validates these constraints
 * BEFORE your controller method runs.
 * ============================================================================
 */
public class Event {

    private Long id;

    // TODO 1: Add validation annotations
    // @NotBlank(message = "Event name is required")
    // @Size(min = 2, max = 100, message = "Name must be 2-100 characters")
    private String name;

    // @NotBlank(message = "Description is required")
    private String description;

    // @NotNull(message = "Date is required")
    // @Future(message = "Event date must be in the future")
    private LocalDate eventDate;

    // @NotBlank(message = "Location is required")
    private String location;

    // @Min(value = 1, message = "Must have at least 1 attendee")
    // @Max(value = 10000, message = "Maximum 10,000 attendees")
    private int maxAttendees;

    private String category;

    // TODO 2: Create constructors (no-arg + full)
    // TODO 3: Create getters and setters
    // TODO 4: Override toString()
}

