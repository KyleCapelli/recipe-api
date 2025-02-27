package com.example.recime_api.data.repository;

import com.example.recime_api.data.entity.RecipeEntity;
import com.example.recime_api.domain.model.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeEntityRepository extends JpaRepository<RecipeEntity, Long> {
    @Query("SELECT r FROM RecipeEntity r JOIN TrendingRecipeEntity tr ON r.id = tr.recipeId ORDER BY tr.position ASC")
    List<RecipeEntity> findTrendingRecipesOrdered();

    List<RecipeEntity> findByDifficulty(Difficulty difficulty);
}
