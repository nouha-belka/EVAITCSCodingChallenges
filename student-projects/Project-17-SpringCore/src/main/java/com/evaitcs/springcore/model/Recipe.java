package com.evaitcs.springcore.model;

/**
 * ============================================================================
 * CLASS: Recipe
 * TOPIC: Simple POJO — Spring will manage instances of this as beans
 * ============================================================================
 */
public class Recipe {

    private String name;
    private String category;
    private int servings;
    private String instructions;

    // TODO 1: Create a no-arg constructor (required for Spring XML config)
    public Recipe(){
    }
    // TODO 2: Create a full constructor
    public Recipe(String name, String category, int servings, String instructions) {
        this.name = name;
        this.category = category;
        this.servings = servings;
        this.instructions = instructions;
    }
    // TODO 3: Create getters and setters for all fields
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public int getServings() {
        return servings;
    }
    
    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getInstructions() {
        return instructions;
    }
    
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    
    // TODO 4: Override toString()
    @Override
    public String toString(){
        return "Recipe{" +
                "Name='" + name + '\'' +
                ", Category='" + category + '\'' +
                ", Servings=" + servings + '\'' +
                ", Instructions=" + instructions +
                '}';
    }
}

