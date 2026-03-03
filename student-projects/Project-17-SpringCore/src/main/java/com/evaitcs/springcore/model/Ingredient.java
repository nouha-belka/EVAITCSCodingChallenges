package com.evaitcs.springcore.model;

/**
 * ============================================================================
 * CLASS: Ingredient
 * ============================================================================
 */
public class Ingredient {

    private String name;
    private double quantity;
    private String unit;

    // TODO 1: Create constructors (no-arg + full)
    public Ingredient(){

    }
    public Ingredient(String name, double quantity, String unit){
        this.name = name; 
        this.quantity = quantity; 
        this.unit = unit; 
    }

    // TODO 2: Create getters, setters, toString()

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString(){
        return "Ingredient{" +
                "Name='" + name + '\'' +
                ", Quantity='" + quantity + '\'' +
                ", Unit=" + unit + '\'' +
                '}';
    }
}

