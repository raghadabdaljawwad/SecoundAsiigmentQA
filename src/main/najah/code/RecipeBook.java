package main.najah.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeBook {
    private List<String> recipes = new ArrayList<>();

    public void addRecipe(String recipe) {
        if (recipe != null && !recipe.trim().isEmpty()) { 
            recipes.add(recipe);
        }
    }

    public boolean removeRecipe(String recipe) {
        return recipes.remove(recipe); 
    }

    public List<String> getRecipes() {
        return Collections.unmodifiableList(recipes); 
    }
}

