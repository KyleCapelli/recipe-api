package com.example.recime_api.api.controller;

import com.example.recime_api.api.dto.RecipeDto;
import com.example.recime_api.domain.model.Difficulty;
import com.example.recime_api.domain.model.Recipe;
import com.example.recime_api.domain.service.QueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    private final QueryHandler queryHandler;

    public RecipeController(QueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping("/trending")
    public ResponseEntity<?> getTrendingRecipes(@RequestParam(value = "difficulty", required = false) String difficultyStr) {
        if (difficultyStr != null) {
            if (difficultyStr.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("A difficulty is required for filtering trending recipes");
            }
            Difficulty difficulty;
            try {
                difficulty = Difficulty.valueOf(difficultyStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Invalid difficulty value provided");
            }
            List<Recipe> recipes = queryHandler.getTrendingRecipesByDifficulty(difficulty);
            List<RecipeDto> recipeDtos = recipes.stream().map(this::toDto).collect(Collectors.toList());
            return ResponseEntity.ok(recipeDtos);
        }

        List<Recipe> recipes = queryHandler.getTrendingRecipes();
        List<RecipeDto> recipeDtos = recipes.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(recipeDtos);
    }

    private RecipeDto toDto(Recipe recipe) {
        return new RecipeDto(recipe.getId(), recipe.getTitle(), recipe.getImageUrl(), recipe.getDifficulty());
    }
}
