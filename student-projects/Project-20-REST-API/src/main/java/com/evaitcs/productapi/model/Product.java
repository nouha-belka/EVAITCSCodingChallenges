package com.evaitcs.productapi.model;

import jakarta.validation.constraints.*;

/**
 * ============================================================================
 * CLASS: Product — Model with Bean Validation
 * TOPIC: @Valid triggers these constraints automatically on API input
 * ============================================================================
 */
public class Product {

    private Long id;

    // TODO 1: Add validation annotations to all fields
    // @NotBlank(message = "Product name is required")
    // @Size(min = 2, max = 100)
    private String name;

    // @NotBlank
    private String description;

    // @NotNull @Positive(message = "Price must be positive")
    private Double price;

    // @NotBlank
    private String category;

    // @Min(0)
    private Integer stockQuantity;

    // TODO 2: Create constructors, getters, setters, toString
}

