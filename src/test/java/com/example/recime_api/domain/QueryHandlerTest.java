package com.example.recime_api.domain;

import com.example.recime_api.domain.model.Difficulty;
import com.example.recime_api.domain.model.Recipe;
import com.example.recime_api.domain.service.QueryHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class QueryHandlerTest {

    @Autowired
    private QueryHandler queryHandler;

    @Test
    void givenQuery_whenGetTrendingRecipes_thenReturnAllRecipesAndAssertOrderAndFields() {
        List<Recipe> recipes = queryHandler.getTrendingRecipes();

        assertNotNull(recipes, "The recipes list should not be null");
        assertEquals(3, recipes.size(), "Should return 3 recipes from data.sql");

        Recipe expected1 = new Recipe("Pancakes", "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg", Difficulty.EASY);
        Recipe expected2 = new Recipe("Bacon And Eggs", "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg", Difficulty.MEDIUM);
        Recipe expected3 = new Recipe("Spaghetti", "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg", Difficulty.HARD);

        Recipe actual1 = recipes.getFirst();
        assertEquals(expected1.getTitle(), actual1.getTitle(), "First recipe should be 'Pancakes'");
        assertEquals(expected1.getImageUrl(), actual1.getImageUrl(), "Image URL for first recipe does not match");
        assertEquals(expected1.getDifficulty(), actual1.getDifficulty(), "Difficulty for first recipe does not match");

        Recipe actual2 = recipes.get(1);
        assertEquals(expected2.getTitle(), actual2.getTitle(), "Second recipe should be 'Bacon And Eggs'");
        assertEquals(expected2.getImageUrl(), actual2.getImageUrl(), "Image URL for second recipe does not match");
        assertEquals(expected2.getDifficulty(), actual2.getDifficulty(), "Difficulty for second recipe does not match");

        Recipe actual3 = recipes.get(2);
        assertEquals(expected3.getTitle(), actual3.getTitle(), "Third recipe should be 'Spaghetti'");
        assertEquals(expected3.getImageUrl(), actual3.getImageUrl(), "Image URL for third recipe does not match");
        assertEquals(expected3.getDifficulty(), actual3.getDifficulty(), "Difficulty for third recipe does not match");
    }

    @Test
    void givenQuery_whenGetTrendingRecipesByDifficulty_thenReturnOnlyMatchingRecipes() {
        List<Recipe> easyRecipes = queryHandler.getTrendingRecipesByDifficulty(Difficulty.EASY);

        assertNotNull(easyRecipes, "The easy recipes list should not be null");
        assertEquals(1, easyRecipes.size(), "There should be exactly one easy recipe");

        Recipe expected = new Recipe("Pancakes", "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg", Difficulty.EASY);
        Recipe actual = easyRecipes.getFirst();

        assertEquals(expected.getTitle(), actual.getTitle(), "Title should be 'Pancakes'");
        assertEquals(expected.getImageUrl(), actual.getImageUrl(), "Image URL should be https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg");
        assertEquals(expected.getDifficulty(), actual.getDifficulty(), "Difficulty should be EASY");
    }
}