package com.evaitcs.productapi.controller;

import com.evaitcs.productapi.model.Product;
import com.evaitcs.productapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * ============================================================================
 * CLASS: ProductController
 * TOPIC: REST API with Swagger annotations + @Valid
 * ============================================================================
 *
 * Swagger annotations generate interactive API docs automatically.
 * Visit http://localhost:8080/swagger-ui.html to see them!
 * ============================================================================
 */
@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Product Management REST API")
public class ProductController {

    // TODO 1: Inject ProductService via constructor

    @Operation(summary = "Get all products")
    @GetMapping
    public List<Product> getAll() {
        // TODO 2: Return all products
        return List.of();
    }

    @Operation(summary = "Get product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        // TODO 3: Find by ID, return 200 or 404
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create a new product")
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        // TODO 4: Save and return 201 CREATED
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update a product")
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product product) {
        // TODO 5: Update and return 200, or 404
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete a product")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // TODO 6: Delete and return 204, or 404
        return ResponseEntity.notFound().build();
    }
}

