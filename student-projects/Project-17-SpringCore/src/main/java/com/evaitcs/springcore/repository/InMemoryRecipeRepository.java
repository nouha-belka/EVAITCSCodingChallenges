package com.evaitcs.springcore.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.evaitcs.springcore.model.Recipe;

/**
 * ============================================================================
 * CLASS: InMemoryRecipeRepository
 * TOPIC: Spring Bean — This class is managed by the Spring IoC Container
 * ============================================================================
 *
 * @Repository tells Spring: "This is a data-access bean. Create an instance
 * and manage its lifecycle for me."
 *
 * Spring will:
 * 1. Detect this class during component scanning
 * 2. Create ONE instance (singleton scope by default)
 * 3. Inject it wherever RecipeRepository is needed
 *
 * INTERVIEW TIP:
 * "@Repository is a specialization of @Component. It marks the class as a
 *  DAO and enables Spring's exception translation for database errors."
 * ============================================================================
 */
@Repository
public class InMemoryRecipeRepository implements RecipeRepository {

    // TODO 1: Declare a private Map<String, Recipe> field for storage
    Map<String, Recipe> recipes;
    //   Initialize in constructor: new HashMap<>()
    public InMemoryRecipeRepository(){
        this.recipes = new HashMap<>();
    }

    // TODO 2: Implement all RecipeRepository methods
    //   save()        → recipes.put(recipe.getName(), recipe)
    public void save(Recipe recipe){
        recipes.put(recipe.getName(), recipe);
    }
    //   findByName()  → Optional.ofNullable(recipes.get(name))
    public Optional<Recipe> findByName(String name){
        return Optional.ofNullable(recipes.get(name));
    }
    //   findAll()     → new ArrayList<>(recipes.values())
    public List<Recipe> findAll(){
        return new ArrayList<>(recipes.values());
    }
    //   findByCategory() → filter recipes by category
    public List<Recipe> findByCategory(String category){
        return recipes.values()
                        .stream()
                        .filter(x -> x.getCategory().equals(category))
                        .toList();
    }
    //   deleteByName() → recipes.remove(name) != null
    public boolean deleteByName(String name){
        return recipes.remove(name) != null;
    }
    //   count()       → recipes.size()
    public long count(){
        return recipes.size();
    }
}

