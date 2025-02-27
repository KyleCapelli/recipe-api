package com.example.recime_api.data.repository;

import com.example.recime_api.data.entity.RecipeEntity;
import com.example.recime_api.data.mapper.RecipeMapper;
import com.example.recime_api.domain.model.Difficulty;
import com.example.recime_api.domain.model.Recipe;
import com.example.recime_api.domain.port.RecipeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RecipeRepositoryAdapter implements RecipeRepository {

    private final RecipeEntityRepository recipeEntityRepository;

    public RecipeRepositoryAdapter(RecipeEntityRepository recipeEntityRepository) {
        this.recipeEntityRepository = recipeEntityRepository;
    }

    @Override
    public List<Recipe> findTrendingRecipesOrdered() {
        List<RecipeEntity> entities = recipeEntityRepository.findTrendingRecipesOrdered();
        return entities.stream()
                .map(RecipeMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Recipe> findTrendingRecipesByDifficulty(Difficulty difficulty) {
        List<RecipeEntity> entities = recipeEntityRepository.findByDifficulty(difficulty);
        return entities.stream()
                .map(RecipeMapper::toDomain)
                .collect(Collectors.toList());
    }
}
