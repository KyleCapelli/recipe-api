package com.example.recime_api.domain.service;

import com.example.recime_api.domain.model.Difficulty;
import com.example.recime_api.domain.model.Recipe;
import com.example.recime_api.domain.port.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryHandler {

    private final RecipeRepository recipeRepository;

    public QueryHandler(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getTrendingRecipes() {
        return recipeRepository.findTrendingRecipes();
    }

    public List<Recipe> getTrendingRecipesByDifficulty(Difficulty difficulty) {
        return recipeRepository.findTrendingRecipesByDifficulty(difficulty);
    }
}