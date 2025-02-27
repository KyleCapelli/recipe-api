package com.example.recime_api.data.mapper;

import com.example.recime_api.data.entity.RecipeEntity;
import com.example.recime_api.domain.model.Recipe;

public class RecipeMapper {

    public static Recipe toDomain(RecipeEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Recipe(entity.getId(), entity.getTitle(), entity.getImageUrl(), entity.getDifficulty());
    }

    public static RecipeEntity toEntity(Recipe recipe) {
        if (recipe == null) {
            return null;
        }
        return new RecipeEntity(recipe.getTitle(), recipe.getImageUrl(), recipe.getDifficulty());
    }
}
