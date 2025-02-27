package com.example.recime_api.data.repository;

import com.example.recime_api.data.entity.RecipeEntity;
import com.example.recime_api.domain.model.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeEntityRepository extends JpaRepository<RecipeEntity, Long> {

    @Query("SELECT r FROM RecipeEntity r JOIN TrendingRecipeEntity tr ON r.id = tr.recipeId ORDER BY tr.position ASC")
    List<RecipeEntity> findTrendingRecipesOrdered();

    @Query("SELECT r FROM RecipeEntity r JOIN TrendingRecipeEntity tr ON r.id = tr.recipeId WHERE r.difficulty = :difficulty ORDER BY tr.position ASC")
    List<RecipeEntity> findTrendingRecipesByDifficulty(@Param("difficulty") Difficulty difficulty);
}
