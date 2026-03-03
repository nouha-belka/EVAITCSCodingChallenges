package com.evaitcs.springcore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaitcs.springcore.model.Recipe;
import com.evaitcs.springcore.repository.RecipeRepository;

/**
 * ============================================================================
 * CLASS: RecipeService
 * TOPIC: Dependency Injection — Spring injects the repository automatically
 * ============================================================================
 *
 * THREE TYPES OF DEPENDENCY INJECTION:
 *
 * 1. CONSTRUCTOR INJECTION (recommended ✅):
 *    @Autowired
 *    public RecipeService(RecipeRepository repo) { this.repo = repo; }
 *    - Fields can be final (immutable)
 *    - Required dependencies are obvious
 *    - Easy to test (pass mocks through constructor)
 *
 * 2. SETTER INJECTION:
 *    @Autowired
 *    public void setRepository(RecipeRepository repo) { this.repo = repo; }
 *    - Good for optional dependencies
 *
 * 3. FIELD INJECTION (avoid ❌):
 *    @Autowired
 *    private RecipeRepository repo;
 *    - Hard to test, hides dependencies
 *
 * INTERVIEW TIP:
 * "I always use constructor injection because it makes dependencies explicit,
 *  allows final fields for immutability, and simplifies unit testing."
 * ============================================================================
 */
@Service
public class RecipeService {

    // TODO 1: Declare a private final RecipeRepository field
    private final RecipeRepository recipeRepository;
    @Autowired
    String defaultCategory;

    // TODO 2: Create a constructor with @Autowired that injects RecipeRepository
    //   In Spring 4.3+, @Autowired is optional on single-constructor classes
    //   but we include it for clarity.
    public RecipeService(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }


    /**
     * TODO 3: Add a recipe — validate name is not empty
     */
    public void addRecipe(Recipe recipe) {
        // TODO: Validate, then save to repository
        if(!recipe.getName().isBlank()){
            recipeRepository.save(recipe);
            System.out.println("Recipe Saved");
        }else{

            System.out.println("Recipe not saved : Name is blank!");
        }
    }

    /**
     * TODO 4: Find a recipe by name
     */
    public Optional<Recipe> findRecipe(String name) {
        return recipeRepository.findByName(name); // Replace
    }

    /**
     * TODO 5: Get all recipes
     */
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll(); // Replace
    }

    /**
     * TODO 6: Get recipes by category
     */
    public List<Recipe> getRecipesByCategory(String category) {
        if(category.equals(defaultCategory)){
            return getAllRecipes();
        }else{

            return recipeRepository.findByCategory(category); // Replace
        }
    }

    /**
     * TODO 7: Delete a recipe
     */
    public boolean deleteRecipe(String name) {
        return recipeRepository.deleteByName(name); // Replace
    }

    /**
     * TODO 8: Get recipe count
     */
    public long getRecipeCount() {
        return recipeRepository.count(); // Replace
    }
}

