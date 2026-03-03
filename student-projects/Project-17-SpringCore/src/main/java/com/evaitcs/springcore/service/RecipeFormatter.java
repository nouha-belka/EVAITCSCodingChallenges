package com.evaitcs.springcore.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * ============================================================================
 * CLASS: RecipeFormatter
 * TOPIC: Bean Lifecycle & Scopes
 * ============================================================================
 *
 * BEAN LIFECYCLE:
 * 1. Spring instantiates the bean (constructor)
 * 2. Dependencies are injected
 * 3. @PostConstruct method is called (initialization)
 * 4. Bean is ready to use
 * 5. When container shuts down: @PreDestroy is called (cleanup)
 *
 * BEAN SCOPES:
 * - singleton (default): ONE instance shared across entire app
 * - prototype: NEW instance every time it's requested
 * - request: ONE instance per HTTP request (web only)
 * - session: ONE instance per HTTP session (web only)
 *
 * INTERVIEW TIP:
 * "Singleton scope is the default — one instance per Spring container.
 *  I use prototype scope when I need a fresh instance each time, like
 *  for stateful objects that shouldn't be shared between threads."
 * ============================================================================
 */
@Component
@Scope("singleton") // TODO: Try changing to "prototype" and observe the difference!
public class RecipeFormatter {

    /**
     * TODO 1: Add a @PostConstruct method
     * This runs AFTER the bean is created and dependencies are injected.
     * Print: "RecipeFormatter initialized — ready to format recipes!"
     *
     * @PostConstruct
     * public void init() { ... }
    */
    @PostConstruct
    public void init() { System.out.println("RecipeFormatter initialized — ready to format recipes!"); }
   
    /**
     * TODO 2: Add a @PreDestroy method
     * This runs BEFORE the bean is destroyed (when app shuts down).
     * Print: "RecipeFormatter shutting down — cleanup complete."
     *
     * @PreDestroy
     * public void cleanup() { ... }
    */
    @PreDestroy
    public void cleanup() { System.out.println("RecipeFormatter shutting down — cleanup complete."); }

    /**
     * TODO 3: Create a format method that returns a nicely formatted recipe string
     * @param name the recipe name
     * @param category the recipe category
     * @param servings the number of servings
     * @param instructions the recipe instructions
     * @return formatted string
     */
    public String format(String name, String category, int servings, String instructions) {
        // TODO: Return a formatted recipe card
        // Example:

        // ╔═══════════════════════════╗
        // ║  Chocolate Cake           ║
        // ║  Category: Dessert        ║
        // ║  Servings: 8              ║
        // ╠═══════════════════════════╣
        // ║  Mix ingredients...       ║
        // ╚═══════════════════════════╝

        // Determine the width of the card based on the longest line
        int maxLength = Math.max(
            Math.max(name.length(), ("Category: " + category).length()),
            Math.max(("Servings: " + servings).length(), instructions.length())
        );
        //added some space for the borders
        int width = maxLength + 4; 

        StringBuilder sb = new StringBuilder();

        // Top border
        sb.append("╔").append("═".repeat(width)).append("╗\n");

        // Recipe name
        sb.append("║  ").append(String.format("%-" + (width - 2) + "s", name)).append("║\n");

        // Category
        sb.append("║  ").append(String.format("%-" + (width - 2) + "s", "Category: " + category)).append("║\n");

        // Servings
        sb.append("║  ").append(String.format("%-" + (width - 2) + "s", "Servings: " + servings)).append("║\n");

        // Separator
        sb.append("╠").append("═".repeat(width)).append("╣\n");

        // Instructions
        sb.append("║  ").append(String.format("%-" + (width - 2) + "s", instructions)).append("║\n");

        // Bottom border
        sb.append("╚").append("═".repeat(width)).append("╝\n");

        return sb.toString();
    }
}

