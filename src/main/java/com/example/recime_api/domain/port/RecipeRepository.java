package com.example.recime_api.domain.port;

import com.example.recime_api.domain.model.Difficulty;
import com.example.recime_api.domain.model.Recipe;

import java.util.List;

public interface RecipeRepository {
    List<Recipe> findTrendingRecipesOrdered();
    List<Recipe> findTrendingRecipesByDifficulty(Difficulty difficulty);
}
